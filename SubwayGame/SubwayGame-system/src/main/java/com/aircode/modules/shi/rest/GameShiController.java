package com.aircode.modules.shi.rest;

import com.aircode.aop.log.Log;
import com.aircode.modules.shi.domain.GameShi;
import com.aircode.modules.shi.service.GameShiService;
import com.aircode.modules.shi.service.dto.GameShiQueryCriteria;
import com.aircode.modules.util.UploadFileTool;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.*;
import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServletResponse;


/**
* @author Zeta
* @date 2020-02-28
*/
@Api(tags = "GameShi管理")
@RestController
@RequestMapping("/api/gameShi")
public class GameShiController {

    private final GameShiService gameShiService;

    public GameShiController(GameShiService gameShiService) {
        this.gameShiService = gameShiService;
    }

    @Log("导出数据")
    @ApiOperation("导出数据")
    @GetMapping(value = "/download")
    @PreAuthorize("@el.check('gameShi:list')")
    public void download(HttpServletResponse response, GameShiQueryCriteria criteria) throws IOException {
        gameShiService.download(gameShiService.queryAll(criteria), response);
    }

    @GetMapping
    @Log("查询GameShi")
    @ApiOperation("查询GameShi")
    @PreAuthorize("@el.check('gameShi:list')")
    public ResponseEntity getGameShis(GameShiQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity<>(gameShiService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @PostMapping
    @Log("新增GameShi")
    @ApiOperation("新增GameShi")
    @PreAuthorize("@el.check('gameShi:add')")
    public ResponseEntity create(@Validated @RequestBody GameShi resources){
        return new ResponseEntity<>(gameShiService.create(resources),HttpStatus.CREATED);
    }

    @PutMapping
    @Log("修改GameShi")
    @ApiOperation("修改GameShi")
    @PreAuthorize("@el.check('gameShi:edit')")
    public ResponseEntity update(@Validated @RequestBody GameShi resources){
        gameShiService.update(resources);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping(value = "/{shiId}")
    @Log("删除GameShi")
    @ApiOperation("删除GameShi")
    @PreAuthorize("@el.check('gameShi:del')")
    public ResponseEntity delete(@PathVariable Integer shiId){
        gameShiService.delete(shiId);
        return new ResponseEntity(HttpStatus.OK);
    }


    @GetMapping(value="/findShiNameByShengName")
    @Log("根据省份查询市级")
    @ApiOperation("根据省份查询市级")
    public ResponseEntity findAllByShengName(String shengName, HttpServletResponse httpServletResponse){
        // 设置请求头
        try {
            UploadFileTool.doPost(httpServletResponse);
        }catch(Exception e){
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        List<String> list = gameShiService.findAllByShengName(shengName);
        if(list.size() == 0) list = null;
        return new ResponseEntity<>(list,HttpStatus.OK);
    }

    @GetMapping("/findExistByShiNameAndShengName")
    @Log("根据市级名称查询该市级名称是否存在GameShi")
    @ApiOperation("根据市级名称查询该市级名称是否存在GameShi")
    public ResponseEntity findExistByShiName(String shengName, String shiName, HttpServletResponse httpServletResponse){
        // 设置请求头
        try {
            UploadFileTool.doPost(httpServletResponse);
        }catch(Exception e){
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        boolean flag = true;
        int count = gameShiService.findExistByShiName(shiName,shengName);
        if(count > 0)
            flag = false;
        return new ResponseEntity<>(flag,HttpStatus.OK);
    }
}