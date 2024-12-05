package cn.xiaqileyu.blog.service;

import cn.xiaqileyu.blog.domain.request.IdRequest;
import cn.xiaqileyu.blog.domain.request.TypeAddRequest;
import cn.xiaqileyu.blog.domain.request.TypeModifyRequest;
import cn.xiaqileyu.blog.domain.request.TypeQueryRequest;
import cn.xiaqileyu.blog.domain.vo.PageResultVO;
import cn.xiaqileyu.blog.domain.vo.ResultVO;
import cn.xiaqileyu.blog.domain.vo.TypeVO;

import java.util.List;

/**
 * 分类信息接口定义
 *
 * @author swt
 * @date 2020/7/7
 */
public interface ITypeService {

    /**
     * 保存分类信息
     *
     * @param request 分类信息请求对象
     * @return 新增结果
     */
    ResultVO<Object> saveType(TypeAddRequest request);

    /**
     * 修改分类信息
     *
     * @param request 分类信息请求对象
     * @return 修改结果
     */
    ResultVO<Object> modifyType(TypeModifyRequest request);

    /**
     * 删除分类信息
     *
     * @param request Id通用请求对象
     * @return 删除结果
     */
    ResultVO<Object> deleteType(IdRequest request);

    /**
     * 查询分类信息列表
     *
     * @param request 分类信息请求对象
     * @return 分类信息列表
     */
    ResultVO<PageResultVO> queryTypeList(TypeQueryRequest request);

    /**
     * 查询所有分类信息
     *
     * @return 分类信息列表
     */
    ResultVO<List<TypeVO>> queryTypeAll();
}
