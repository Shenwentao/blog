package cn.xiaqileyu.blog.mapper;

import cn.xiaqileyu.blog.domain.dao.TypeDO;
import cn.xiaqileyu.blog.domain.request.TypeQueryRequest;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 分类信息数据库操作类
 *
 * @author swt
 * @date 2020/7/9
 */
@Mapper
public interface TypeMapper {

    /**
     * 通过分类名查询分类信息
     *
     * @param typeName 分类名称
     * @return 分类信息
     */
    TypeDO selectTypeByName(@Param(value = "typeName") String typeName);

    /**
     * 新增分类信息
     *
     * @param typeDO 分类信息
     */
    void insertType(TypeDO typeDO);

    /**
     * 修改分类信息
     *
     * @param typeDO 分类信息
     */
    void updateType(TypeDO typeDO);

    /**
     * 删除分类信息
     *
     * @param id 分类ID
     */
    void deleteType(@Param(value = "id") Long id);

    /**
     * 查询分类信息列表
     *
     * @param request 分类信息查询对象
     * @return 分类信息列表
     */
    List<TypeDO> selectTypeList(TypeQueryRequest request);
}
