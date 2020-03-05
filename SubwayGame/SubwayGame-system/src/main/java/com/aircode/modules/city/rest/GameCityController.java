package com.aircode.modules.city.rest;

import com.aircode.aop.log.Log;
import com.aircode.modules.city.domain.GameCity;
import com.aircode.modules.city.service.GameCityService;
import com.aircode.modules.city.service.dto.GameCityQueryCriteria;
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
@Api(tags = "GameCity管理")
@RestController
@RequestMapping("/api/gameCity")
public class GameCityController {

    private final GameCityService gameCityService;

    public GameCityController(GameCityService gameCityService) {
        this.gameCityService = gameCityService;
    }

    @Log("导出数据")
    @ApiOperation("导出数据")
    @GetMapping(value = "/download")
    @PreAuthorize("@el.check('gameCity:list')")
    public void download(HttpServletResponse response, GameCityQueryCriteria criteria) throws IOException {
        gameCityService.download(gameCityService.queryAll(criteria), response);
    }

    @GetMapping
    @Log("查询GameCity")
    @ApiOperation("查询GameCity")
    @PreAuthorize("@el.check('gameCity:list')")
    public ResponseEntity getGameCitys(GameCityQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity<>(gameCityService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @PostMapping
    @Log("新增GameCity")
    @ApiOperation("新增GameCity")
    @PreAuthorize("@el.check('gameCity:add')")
    public ResponseEntity create(@Validated @RequestBody GameCity resources){
        return new ResponseEntity<>(gameCityService.create(resources),HttpStatus.CREATED);
    }

    @PutMapping
    @Log("修改GameCity")
    @ApiOperation("修改GameCity")
    @PreAuthorize("@el.check('gameCity:edit')")
    public ResponseEntity update(@Validated @RequestBody GameCity resources){
        gameCityService.update(resources);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping(value = "/{cityId}")
    @Log("删除GameCity")
    @ApiOperation("删除GameCity")
    @PreAuthorize("@el.check('gameCity:del')")
    public ResponseEntity delete(@PathVariable Integer cityId){
        gameCityService.delete(cityId);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping(path="/selectAll")
    @Log("查询所有城市GameCity")
    @ApiOperation("查询所有城市GameCity")
    public ResponseEntity selectAll(HttpServletResponse httpServletResponse){
        // 设置请求头
        try {
            UploadFileTool.doPost(httpServletResponse);
        }catch(Exception e){
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(gameCityService.findAll(),HttpStatus.OK);
    }
}