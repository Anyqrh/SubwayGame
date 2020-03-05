package com.aircode.modules.sheng.rest;

import com.aircode.aop.log.Log;
import com.aircode.modules.sheng.domain.GameSheng;
import com.aircode.modules.sheng.service.GameShengService;
import com.aircode.modules.sheng.service.dto.GameShengQueryCriteria;
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
@Api(tags = "GameSheng管理")
@RestController
@RequestMapping("/api/gameSheng")
public class GameShengController {

    private final GameShengService gameShengService;

    public GameShengController(GameShengService gameShengService) {
        this.gameShengService = gameShengService;
    }

    @Log("导出数据")
    @ApiOperation("导出数据")
    @GetMapping(value = "/download")
    @PreAuthorize("@el.check('gameSheng:list')")
    public void download(HttpServletResponse response, GameShengQueryCriteria criteria) throws IOException {
        gameShengService.download(gameShengService.queryAll(criteria), response);
    }

    @GetMapping
    @Log("查询GameSheng")
    @ApiOperation("查询GameSheng")
    @PreAuthorize("@el.check('gameSheng:list')")
    public ResponseEntity getGameShengs(GameShengQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity<>(gameShengService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @PostMapping
    @Log("新增GameSheng")
    @ApiOperation("新增GameSheng")
    @PreAuthorize("@el.check('gameSheng:add')")
    public ResponseEntity create(@Validated @RequestBody GameSheng resources){
        return new ResponseEntity<>(gameShengService.create(resources),HttpStatus.CREATED);
    }

    @PutMapping
    @Log("修改GameSheng")
    @ApiOperation("修改GameSheng")
    @PreAuthorize("@el.check('gameSheng:edit')")
    public ResponseEntity update(@Validated @RequestBody GameSheng resources){
        gameShengService.update(resources);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping(value = "/{shengId}")
    @Log("删除GameSheng")
    @ApiOperation("删除GameSheng")
    @PreAuthorize("@el.check('gameSheng:del')")
    public ResponseEntity delete(@PathVariable Integer shengId){
        gameShengService.delete(shengId);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping(path="/selectAll")
    @Log("查询所有省份GameSheng")
    @ApiOperation("查询所有省份GameSheng")
    public ResponseEntity selectAll(HttpServletResponse httpServletResponse){
        // 设置请求头
        try {
            UploadFileTool.doPost(httpServletResponse);
        }catch(Exception e){
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(gameShengService.findAll(),HttpStatus.OK);
    }

    @GetMapping("/findExistByShengName")
    @Log("根据省份名称查询是否存在该名称的省份")
    @ApiOperation("根据省份名称查询是否存在该名称的省份")
    public ResponseEntity findExistByShengName(String shengName,HttpServletResponse httpServletResponse){
        // 设置请求头
        try {
            UploadFileTool.doPost(httpServletResponse);
        }catch(Exception e){
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        boolean flag = true;  // true 表示不存在改名字的省份
        int count = gameShengService.findExitsByShengName(shengName);
        if(count > 0)
            flag = false;
        return new ResponseEntity<>(flag, HttpStatus.OK);
    }
}