package cn.xiaqileyu.blog.service.impl;

import cn.xiaqileyu.blog.constant.CommonConstants;
import cn.xiaqileyu.blog.constant.ResultCode;
import cn.xiaqileyu.blog.domain.dao.TypeDO;
import cn.xiaqileyu.blog.domain.request.IdRequest;
import cn.xiaqileyu.blog.domain.request.TypeAddRequest;
import cn.xiaqileyu.blog.domain.request.TypeModifyRequest;
import cn.xiaqileyu.blog.domain.request.TypeQueryRequest;
import cn.xiaqileyu.blog.domain.vo.PageResultVO;
import cn.xiaqileyu.blog.domain.vo.ResultVO;
import cn.xiaqileyu.blog.domain.vo.TypeVO;
import cn.xiaqileyu.blog.exception.CustomException;
import cn.xiaqileyu.blog.mapper.TypeMapper;
import cn.xiaqileyu.blog.service.ITypeService;
import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 分类信息接口实现类
 *
 * @author swt
 * @date 2020/7/9
 */
@Slf4j
@Service
public class TypeServiceImpl implements ITypeService {

    @Resource
    private TypeMapper typeMapper;

    @Override
    public ResultVO<Object> saveType(TypeAddRequest request) {
        //验证分类名称属否存在
        TypeDO typeDO = typeMapper.selectTypeByName(request.getTypeName());
        if (typeDO != null) {
            log.info("分类信息已经存在，tagName : {}", request.getTypeName());
            throw new CustomException(ResultCode.TYPE_NAME_EXIST.getCode(), ResultCode.TYPE_NAME_EXIST.getMsg());
        }
        TypeDO typeAddDO = new TypeDO();
        BeanUtils.copyProperties(request, typeAddDO);
        typeMapper.insertType(typeAddDO);
        return ResultVO.successResult(CommonConstants.EMPTY_DATA);
    }

    @Override
    public ResultVO<Object> modifyType(TypeModifyRequest request) {
        TypeDO typeDO = new TypeDO();
        BeanUtils.copyProperties(request, typeDO);
        typeMapper.updateType(typeDO);
        return ResultVO.successResult(CommonConstants.EMPTY_DATA);
    }

    @Override
    public ResultVO<Object> deleteType(IdRequest request) {
        typeMapper.deleteType(request.getId());
        return ResultVO.successResult(CommonConstants.EMPTY_DATA);
    }

    @Override
    public ResultVO<PageResultVO> queryTypeList(TypeQueryRequest request) {
        PageHelper.startPage(request.getPageNum(), request.getPageSize());
        List<TypeDO> typeInfoList = typeMapper.selectTypeList(request);
        List<TypeVO> typeVOList = typeInfoList.stream().map(typeDO -> JSON.parseObject(JSON.toJSONString(typeDO), TypeVO.class))
                .collect(Collectors.toList());
        PageInfo pageInfo = new PageInfo(typeVOList);
        return ResultVO.successResult(new PageResultVO(pageInfo.getTotal(), typeVOList));
    }

    @Override
    public ResultVO<List<TypeVO>> queryTypeAll() {
        TypeQueryRequest request = new TypeQueryRequest();
        List<TypeDO> typeInfoList = typeMapper.selectTypeList(request);
        List<TypeVO> typeVOList = typeInfoList.stream().map(typeDO -> JSON.parseObject(JSON.toJSONString(typeDO), TypeVO.class))
                .collect(Collectors.toList());
        return ResultVO.successResult(typeVOList);
    }
}
