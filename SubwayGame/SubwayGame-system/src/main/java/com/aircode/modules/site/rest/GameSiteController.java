package com.aircode.modules.site.rest;

import com.aircode.aop.log.Log;
import com.aircode.modules.site.domain.GameSite;
import com.aircode.modules.site.service.GameSiteService;
import com.aircode.modules.site.service.dto.GameSiteQueryCriteria;
import com.aircode.modules.util.UploadFileTool;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.*;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

/**
* @author Zeta
* @date 2020-02-28
*/
@Api(tags = "GameSite管理")
@RestController
@RequestMapping("/api/gameSite")
public class GameSiteController {

    private final GameSiteService gameSiteService;

    public GameSiteController(GameSiteService gameSiteService) {
        this.gameSiteService = gameSiteService;
    }

    @Log("导出数据")
    @ApiOperation("导出数据")
    @GetMapping(value = "/download")
    @PreAuthorize("@el.check('gameSite:list')")
    public void download(HttpServletResponse response, GameSiteQueryCriteria criteria) throws IOException {
        gameSiteService.download(gameSiteService.queryAll(criteria), response);
    }

    @GetMapping
    @Log("查询GameSite")
    @ApiOperation("查询GameSite")
    @PreAuthorize("@el.check('gameSite:list')")
    public ResponseEntity getGameSites(GameSiteQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity<>(gameSiteService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @PostMapping
    @Log("新增GameSite")
    @ApiOperation("新增GameSite")
    @PreAuthorize("@el.check('gameSite:add')")
    public ResponseEntity create(@Validated @RequestBody GameSite resources){
        return new ResponseEntity<>(gameSiteService.create(resources),HttpStatus.CREATED);
    }

    @PutMapping
    @Log("修改GameSite")
    @ApiOperation("修改GameSite")
    @PreAuthorize("@el.check('gameSite:edit')")
    public ResponseEntity update(@Validated @RequestBody GameSite resources){
        gameSiteService.update(resources);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping(value = "/{siteId}")
    @Log("删除GameSite")
    @ApiOperation("删除GameSite")
    @PreAuthorize("@el.check('gameSite:del')")
    public ResponseEntity delete(@PathVariable Integer siteId){
        gameSiteService.delete(siteId);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping(path="/selectAll")
    @Log("查询所有站点GameSite")
    @ApiOperation("查询所有站点GameSite")
    public ResponseEntity selectAll(HttpServletResponse httpServletResponse){
        // 设置请求头
        try {
            UploadFileTool.doPost(httpServletResponse);
        }catch(Exception e){
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(gameSiteService.findAll(),HttpStatus.OK);
    }
}