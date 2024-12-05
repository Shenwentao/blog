package cn.xiaqileyu.blog.mapper;

import cn.xiaqileyu.blog.domain.dao.UserDO;
import cn.xiaqileyu.blog.domain.request.UserQueryRequest;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 后台管理数据库操作类
 *
 * @author swt
 * @date 2020/6/30
 */
@Mapper
public interface UserMapper {

    /**
     * 根据用户名查询用户信息
     *
     * @param userName 用户名
     * @return 用户信息
     */
    UserDO selectUserByName(@Param(value = "userName") String userName);

    /**
     * 新增用户信息
     *
     * @param userDO 用户信息
     */
    void insertUserInfo(UserDO userDO);

    /**
     * 更新用户信息
     *
     * @param userDO 用户信息
     */
    void updateUserInfo(UserDO userDO);

    /**
     * 删除用户信息
     *
     * @param id 用户id
     */
    void deleteUserInfo(@Param(value = "id") Long id);

    /**
     * 查询用户信息列表
     *
     * @param userQueryRequest 用户信息查询对象
     * @return 用户信息列表
     */
    List<UserDO> selectUserList(UserQueryRequest userQueryRequest);
}
