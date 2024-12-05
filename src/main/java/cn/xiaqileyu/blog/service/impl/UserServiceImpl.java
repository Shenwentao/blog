package cn.xiaqileyu.blog.service.impl;

import cn.xiaqileyu.blog.constant.CommonConstants;
import cn.xiaqileyu.blog.constant.ResultCode;
import cn.xiaqileyu.blog.domain.dao.UserDO;
import cn.xiaqileyu.blog.domain.request.IdRequest;
import cn.xiaqileyu.blog.domain.request.UserAddRequest;
import cn.xiaqileyu.blog.domain.request.UserModifyRequest;
import cn.xiaqileyu.blog.domain.request.UserQueryRequest;
import cn.xiaqileyu.blog.domain.vo.PageResultVO;
import cn.xiaqileyu.blog.domain.vo.ResultVO;
import cn.xiaqileyu.blog.domain.vo.UserVO;
import cn.xiaqileyu.blog.exception.CustomException;
import cn.xiaqileyu.blog.mapper.UserMapper;
import cn.xiaqileyu.blog.service.IUserService;
import cn.xiaqileyu.blog.util.CommonUtil;
import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 后台管理接口实现类
 *
 * @author swt
 * @date 2020/6/29
 */
@Slf4j
@Service
public class UserServiceImpl implements IUserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public ResultVO<UserVO> queryUserByName(String userName) {
        UserDO userDO = userMapper.selectUserByName(userName);
        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(userDO, userVO);
        return ResultVO.successResult(userVO);
    }

    @Override
    public ResultVO<Object> registerUser(UserAddRequest request) {
        //判断用户名称是否已经存在
        UserDO userDO = userMapper.selectUserByName(request.getUserName());
        if (null != userDO) {
            log.info("用户名称已经存在，userName : {}", request.getUserName());
            throw new CustomException(ResultCode.USER_NAME_EXIST.getCode(), ResultCode.USER_NAME_EXIST.getMsg());
        }

        //注册用户
        UserDO userAddDO = new UserDO();
        BeanUtils.copyProperties(request, userAddDO);
        userAddDO.setPassword(CommonUtil.encrypt(request.getPassword()));
        userMapper.insertUserInfo(userAddDO);
        return ResultVO.successResult(CommonConstants.EMPTY_DATA);
    }

    @Override
    public ResultVO<Object> modifyUser(UserModifyRequest request) {
        UserDO userModifyDO = new UserDO();
        BeanUtils.copyProperties(request, userModifyDO);
        if (StringUtils.isNotBlank(request.getPassword())) {
            userModifyDO.setPassword(CommonUtil.encrypt(request.getPassword()));
        }
        userMapper.updateUserInfo(userModifyDO);
        return ResultVO.successResult(CommonConstants.EMPTY_DATA);
    }

    @Override
    public ResultVO<Object> deleteUser(IdRequest request) {
        userMapper.deleteUserInfo(request.getId());
        return ResultVO.successResult(CommonConstants.EMPTY_DATA);
    }

    @Override
    public ResultVO<PageResultVO> queryUserList(UserQueryRequest request) {
        PageHelper.startPage(request.getPageNum(), request.getPageSize());
        List<UserDO> userInfoList = userMapper.selectUserList(request);
        List<UserVO> userVOList = userInfoList.stream().map(userDO -> JSON.parseObject(JSON.toJSONString(userDO), UserVO.class))
                .collect(Collectors.toList());
        PageInfo pageInfo = new PageInfo(userVOList);
        return ResultVO.successResult(new PageResultVO(pageInfo.getTotal(), userVOList));
    }
}
