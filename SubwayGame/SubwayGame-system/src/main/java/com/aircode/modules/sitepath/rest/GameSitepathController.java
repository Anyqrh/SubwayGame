package com.aircode.modules.sitepath.rest;

import com.aircode.aop.log.Log;
import com.aircode.modules.sitepath.domain.GameSitepath;
import com.aircode.modules.sitepath.service.GameSitepathService;
import com.aircode.modules.sitepath.service.dto.GameSitepathQueryCriteria;
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
@Api(tags = "GameSitepath管理")
@RestController
@RequestMapping("/api/gameSitepath")
public class GameSitepathController {

    private final GameSitepathService gameSitepathService;

    public GameSitepathController(GameSitepathService gameSitepathService) {
        this.gameSitepathService = gameSitepathService;
    }

    @Log("导出数据")
    @ApiOperation("导出数据")
    @GetMapping(value = "/download")
    @PreAuthorize("@el.check('gameSitepath:list')")
    public void download(HttpServletResponse response, GameSitepathQueryCriteria criteria) throws IOException {
        gameSitepathService.download(gameSitepathService.queryAll(criteria), response);
    }

    @GetMapping
    @Log("查询GameSitepath")
    @ApiOperation("查询GameSitepath")
    @PreAuthorize("@el.check('gameSitepath:list')")
    public ResponseEntity getGameSitepaths(GameSitepathQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity<>(gameSitepathService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @PostMapping
    @Log("新增GameSitepath")
    @ApiOperation("新增GameSitepath")
    @PreAuthorize("@el.check('gameSitepath:add')")
    public ResponseEntity create(@Validated @RequestBody GameSitepath resources){
        return new ResponseEntity<>(gameSitepathService.create(resources),HttpStatus.CREATED);
    }

    @PutMapping
    @Log("修改GameSitepath")
    @ApiOperation("修改GameSitepath")
    @PreAuthorize("@el.check('gameSitepath:edit')")
    public ResponseEntity update(@Validated @RequestBody GameSitepath resources){
        gameSitepathService.update(resources);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping(value = "/{sitepathId}")
    @Log("删除GameSitepath")
    @ApiOperation("删除GameSitepath")
    @PreAuthorize("@el.check('gameSitepath:del')")
    public ResponseEntity delete(@PathVariable Integer sitepathId){
        gameSitepathService.delete(sitepathId);
        return new ResponseEntity(HttpStatus.OK);
    }
}