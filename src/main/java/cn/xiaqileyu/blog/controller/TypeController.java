package cn.xiaqileyu.blog.controller;

import cn.xiaqileyu.blog.domain.request.IdRequest;
import cn.xiaqileyu.blog.domain.request.TypeAddRequest;
import cn.xiaqileyu.blog.domain.request.TypeModifyRequest;
import cn.xiaqileyu.blog.domain.request.TypeQueryRequest;
import cn.xiaqileyu.blog.domain.vo.PageResultVO;
import cn.xiaqileyu.blog.domain.vo.ResultVO;
import cn.xiaqileyu.blog.domain.vo.TypeVO;
import cn.xiaqileyu.blog.service.ITypeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

/**
 * 分类信息服务接口
 *
 * @author swt
 * @date 2020/7/7
 */
@RequestMapping("type")
@RestController
@Api(tags = "分类信息服务接口")
public class TypeController {

    @Autowired
    private ITypeService typeService;

    @ApiOperation(value = "新增分类")
    @PostMapping("/saveType")
    public ResultVO<Object> saveType(@RequestBody @Valid TypeAddRequest request) {
        return typeService.saveType(request);
    }

    @ApiOperation(value = "修改分类")
    @PutMapping("/modifyType")
    public ResultVO<Object> modifyType(@RequestBody @Valid TypeModifyRequest request) {
        return typeService.modifyType(request);
    }

    @ApiOperation(value = "删除分类")
    @DeleteMapping("/deleteType")
    public ResultVO<Object> deleteType(@RequestBody @Valid IdRequest request) {
        return typeService.deleteType(request);
    }

    @ApiOperation(value = "查询分类列表")
    @PostMapping("/queryTypeList")
    public ResultVO<PageResultVO> queryTypeList(@RequestBody @Valid TypeQueryRequest request) {
        return typeService.queryTypeList(request);
    }

    @ApiOperation(value = "查询所有分类信息")
    @GetMapping("/queryTypeList")
    public ResultVO<List<TypeVO>> queryTypeAll() {
        return typeService.queryTypeAll();
    }
}
