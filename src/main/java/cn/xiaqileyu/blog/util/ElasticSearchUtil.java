package cn.xiaqileyu.blog.util;

import cn.xiaqileyu.blog.domain.entity.ElasticEntity;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpHost;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.reindex.DeleteByQueryRequest;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * ES操作工具类
 *
 * @author swt
 * @date 2020/7/6
 */
@Slf4j
@Component
public class ElasticSearchUtil {

    @Value("10.211.55.7")
    public String host;

    @Value("9200")
    public int port;

    @Value("http")
    public String scheme;

    public static final String INDEX_NAME = "blog-index";

    public static String CREATE_INDEX;

    public static RestHighLevelClient restHighLevelClient = null;

    @PostConstruct
    public void init() {
        CREATE_INDEX = readFileToString("src/main/resources/es.txt");
        restHighLevelClient = new RestHighLevelClient(RestClient.builder(new HttpHost(host, port, scheme)));
        createIndex(INDEX_NAME, CREATE_INDEX);
    }

    /**
     * 创建索引
     *
     * @param idxName 索引名称
     * @param idxSQL  索引描述
     */
    public void createIndex(String idxName, String idxSQL) {
        try {
            if (this.indexExist(idxName)) {
                log.error(" idxName={} 已经存在", idxName);
                return;
            }
            CreateIndexRequest request = new CreateIndexRequest(idxName);
            buildSetting(request);
            request.mapping(idxSQL, XContentType.JSON);
            CreateIndexResponse res = restHighLevelClient.indices().create(request, RequestOptions.DEFAULT);
            if (!res.isAcknowledged()) {
                throw new RuntimeException("初始化失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(0);
        }
    }

    /**
     * 断某个index是否存在
     *
     * @param idxName 索引名称
     */
    public boolean indexExist(String idxName) throws Exception {
        GetIndexRequest request = new GetIndexRequest(idxName);
        return restHighLevelClient.indices().exists(request, RequestOptions.DEFAULT);
    }

    /**
     * 删除index
     *
     * @param idxName 索引名称
     */
    public void deleteIndex(String idxName) {
        try {
            restHighLevelClient.indices().delete(new DeleteIndexRequest(idxName), RequestOptions.DEFAULT);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 设置分片
     *
     * @param request
     */
    public void buildSetting(CreateIndexRequest request) {
        request.settings(Settings.builder().put("index.number_of_shards", 3)
                .put("index.number_of_replicas", 2));
    }

    /**
     * 插入或者修改一条数据
     *
     * @param idxName 索引名称
     * @param entity  对象
     */
    public void insertOrUpdateOne(String idxName, ElasticEntity entity) {
        IndexRequest request = new IndexRequest(idxName);
        log.info("Data : id={},entity={}", entity.getId(), JSON.toJSONString(entity.getData()));
        request.id(entity.getId());
        request.source(JSON.toJSONString(entity.getData()), XContentType.JSON);
        try {
            restHighLevelClient.index(request, RequestOptions.DEFAULT);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 批量插入数据
     *
     * @param idxName index
     * @param list    待插入列表数据
     */
    public void insertBatch(String idxName, List<ElasticEntity> list) {
        BulkRequest request = new BulkRequest();
        list.forEach(item -> request.add(new IndexRequest(idxName).id(item.getId())
                .source(JSON.toJSONString(item.getData()), XContentType.JSON)));
        try {
            restHighLevelClient.bulk(request, RequestOptions.DEFAULT);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 批量删除
     *
     * @param idxName index
     * @param idList  待删除列表数据
     */
    public <T> void deleteBatch(String idxName, Collection<T> idList) {
        BulkRequest request = new BulkRequest();
        idList.forEach(item -> request.add(new DeleteRequest(idxName, item.toString())));
        try {
            restHighLevelClient.bulk(request, RequestOptions.DEFAULT);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 根据条件删除
     *
     * @param idxName 索引名
     * @param builder 条件
     */
    public void deleteByQuery(String idxName, QueryBuilder builder) {
        DeleteByQueryRequest request = new DeleteByQueryRequest(idxName);
        request.setQuery(builder);
        //设置批量操作数量,最大为10000
        request.setBatchSize(10000);
        request.setConflicts("proceed");
        try {
            restHighLevelClient.deleteByQuery(request, RequestOptions.DEFAULT);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 查询
     *
     * @param idxName index
     * @param builder 查询参数
     * @param c       结果类对象
     */
    public <T> List<T> search(String idxName, SearchSourceBuilder builder, Class<T> c) {
        SearchRequest request = new SearchRequest(idxName);
        request.source(builder);
        try {
            SearchResponse response = restHighLevelClient.search(request, RequestOptions.DEFAULT);
            SearchHit[] hits = response.getHits().getHits();
            List<T> res = new ArrayList<>(hits.length);
            for (SearchHit hit : hits) {
                res.add(JSON.parseObject(hit.getSourceAsString(), c));
            }
            return res;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static String readFileToString(String filePath) {
        File file = new File(filePath);
        System.out.println(file.getAbsolutePath());
        try (FileReader reader = new FileReader(file)) {
            BufferedReader bReader = new BufferedReader(reader);
            StringBuilder sb = new StringBuilder();
            String s = "";
            while ((s = bReader.readLine()) != null) {
                sb.append(s + "\n");
            }
            return sb.toString();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        return "";
    }
}
