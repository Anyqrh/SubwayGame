package com.aircode.modules.subwaypath.rest;

import com.aircode.aop.log.Log;
import com.aircode.modules.subwaypath.domain.GameSubwaypath;
import com.aircode.modules.subwaypath.service.GameSubwaypathService;
import com.aircode.modules.subwaypath.service.dto.GameSubwaypathQueryCriteria;
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
@Api(tags = "GameSubwaypath管理")
@RestController
@RequestMapping("/api/gameSubwaypath")
public class GameSubwaypathController {

    private final GameSubwaypathService gameSubwaypathService;

    public GameSubwaypathController(GameSubwaypathService gameSubwaypathService) {
        this.gameSubwaypathService = gameSubwaypathService;
    }

    @Log("导出数据")
    @ApiOperation("导出数据")
    @GetMapping(value = "/download")
    @PreAuthorize("@el.check('gameSubwaypath:list')")
    public void download(HttpServletResponse response, GameSubwaypathQueryCriteria criteria) throws IOException {
        gameSubwaypathService.download(gameSubwaypathService.queryAll(criteria), response);
    }

    @GetMapping
    @Log("查询GameSubwaypath")
    @ApiOperation("查询GameSubwaypath")
    @PreAuthorize("@el.check('gameSubwaypath:list')")
    public ResponseEntity getGameSubwaypaths(GameSubwaypathQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity<>(gameSubwaypathService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @PostMapping
    @Log("新增GameSubwaypath")
    @ApiOperation("新增GameSubwaypath")
    @PreAuthorize("@el.check('gameSubwaypath:add')")
    public ResponseEntity create(@Validated @RequestBody GameSubwaypath resources){
        return new ResponseEntity<>(gameSubwaypathService.create(resources),HttpStatus.CREATED);
    }

    @PutMapping
    @Log("修改GameSubwaypath")
    @ApiOperation("修改GameSubwaypath")
    @PreAuthorize("@el.check('gameSubwaypath:edit')")
    public ResponseEntity update(@Validated @RequestBody GameSubwaypath resources){
        gameSubwaypathService.update(resources);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping(value = "/{subwaypathId}")
    @Log("删除GameSubwaypath")
    @ApiOperation("删除GameSubwaypath")
    @PreAuthorize("@el.check('gameSubwaypath:del')")
    public ResponseEntity delete(@PathVariable Integer subwaypathId){
        gameSubwaypathService.delete(subwaypathId);
        return new ResponseEntity(HttpStatus.OK);
    }
}