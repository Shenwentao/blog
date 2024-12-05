package cn.xiaqileyu.blog.service;

import cn.xiaqileyu.blog.domain.request.IdRequest;
import cn.xiaqileyu.blog.domain.request.UserAddRequest;
import cn.xiaqileyu.blog.domain.request.UserModifyRequest;
import cn.xiaqileyu.blog.domain.request.UserQueryRequest;
import cn.xiaqileyu.blog.domain.vo.PageResultVO;
import cn.xiaqileyu.blog.domain.vo.ResultVO;
import cn.xiaqileyu.blog.domain.vo.UserVO;

/**
 * 用户管理接口定义
 *
 * @author swt
 * @date 2020/6/29
 */
public interface IUserService {

    /**
     * 根据用户名查询用户信息
     *
     * @param userName 用户名称
     * @return 用户信息
     */
    ResultVO<UserVO> queryUserByName(String userName);

    /**
     * 用户注册
     *
     * @param request 用户信息请求对象
     * @return 新增结果
     */
    ResultVO<Object> registerUser(UserAddRequest request);

    /**
     * 修改用户信息
     *
     * @param request 用户信息请求对象
     * @return 修改结果
     */
    ResultVO<Object> modifyUser(UserModifyRequest request);

    /**
     * 删除用户信息
     *
     * @param request Id通用请求对象
     * @return 删除结果
     */
    ResultVO<Object> deleteUser(IdRequest request);

    /**
     * 查询用户列表信息
     *
     * @param request 用户查询信息请求对象
     * @return 用户列表
     */
    ResultVO<PageResultVO> queryUserList(UserQueryRequest request);
}
