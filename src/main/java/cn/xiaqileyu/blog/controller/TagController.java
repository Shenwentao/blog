package cn.xiaqileyu.blog.controller;

import cn.xiaqileyu.blog.domain.request.IdRequest;
import cn.xiaqileyu.blog.domain.request.TagAddRequest;
import cn.xiaqileyu.blog.domain.request.TagModifyRequest;
import cn.xiaqileyu.blog.domain.request.TagQueryRequest;
import cn.xiaqileyu.blog.domain.vo.PageResultVO;
import cn.xiaqileyu.blog.domain.vo.ResultVO;
import cn.xiaqileyu.blog.domain.vo.TagVO;
import cn.xiaqileyu.blog.service.ITagService;
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
 * 标签信息服务接口
 *
 * @author swt
 * @date 2020/7/7
 */
@RequestMapping("tag")
@RestController
@Api(tags = "标签信息服务接口")
public class TagController {

    @Autowired
    private ITagService tagService;

    @ApiOperation(value = "新增标签")
    @PostMapping("/saveTag")
    public ResultVO<Object> saveTag(@RequestBody @Valid TagAddRequest request) {
        return tagService.saveTag(request);
    }

    @ApiOperation(value = "修改标签")
    @PutMapping("/modifyTag")
    public ResultVO<Object> modifyTag(@RequestBody @Valid TagModifyRequest request) {
        return tagService.modifyTag(request);
    }

    @ApiOperation(value = "删除标签")
    @DeleteMapping("/deleteTag")
    public ResultVO<Object> deleteTag(@RequestBody @Valid IdRequest request) {
        return tagService.deleteTag(request);
    }

    @ApiOperation(value = "查询标签列表")
    @PostMapping("/queryTagList")
    public ResultVO<PageResultVO> queryTagList(@RequestBody @Valid TagQueryRequest request) {
        return tagService.queryTagList(request);
    }

    @ApiOperation(value = "查询所有标签")
    @GetMapping("/queryTagAll")
    public ResultVO<List<TagVO>> queryTagAll() {
        return tagService.queryTagAll();
    }
}
