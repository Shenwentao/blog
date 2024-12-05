package cn.xiaqileyu.blog.controller;

import cn.xiaqileyu.blog.domain.request.IdRequest;
import cn.xiaqileyu.blog.domain.request.UserAddRequest;
import cn.xiaqileyu.blog.domain.request.UserModifyRequest;
import cn.xiaqileyu.blog.domain.request.UserQueryRequest;
import cn.xiaqileyu.blog.domain.vo.PageResultVO;
import cn.xiaqileyu.blog.domain.vo.ResultVO;
import cn.xiaqileyu.blog.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * 用户管理服务接口
 *
 * @author swt
 * @date 2020/7/1
 */
@RequestMapping("user")
@RestController
@Api(tags = "用户管理服务接口")
public class UserController {

    @Autowired
    private IUserService userService;

    @ApiOperation(value = "注册用户")
    @PostMapping(value = "/registerUser")
    public ResultVO<Object> registerUser(@RequestBody @Valid UserAddRequest request) {
        return userService.registerUser(request);
    }

    @ApiOperation(value = "修改用户")
    @PutMapping("/modifyUser")
    public ResultVO<Object> modifyUser(@RequestBody @Valid UserModifyRequest request) {
        return userService.modifyUser(request);
    }

    @ApiOperation(value = "删除用户")
    @DeleteMapping("/deleteUser")
    public ResultVO<Object> deleteUser(@RequestBody @Valid IdRequest request) {
        return userService.deleteUser(request);
    }
    @ApiOperation(value = "查询用户列表")
    @PostMapping("/queryUserList")
    public ResultVO<PageResultVO> queryUserList(@RequestBody UserQueryRequest request) {
        return userService.queryUserList(request);
    }
}
