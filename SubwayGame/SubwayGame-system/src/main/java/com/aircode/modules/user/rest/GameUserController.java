package com.aircode.modules.user.rest;

import com.aircode.aop.log.Log;
import com.aircode.modules.user.domain.GameUser;
import com.aircode.modules.user.service.GameUserService;
import com.aircode.modules.user.service.dto.GameUserQueryCriteria;
import com.aircode.modules.util.UploadFileTool;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

/**
* @author Zeta
* @date 2020-02-27
*/
@Api(tags = "GameUser管理")
@RestController
@RequestMapping("/api/gameUser")
public class GameUserController {
    @Value("${fileServer.avatar}")
    private String path;

    private final GameUserService gameUserService;

    public GameUserController(GameUserService gameUserService) {
        this.gameUserService = gameUserService;
    }

    @Log("导出数据")
    @ApiOperation("导出数据")
    @GetMapping(value = "/download")
    @PreAuthorize("@el.check('gameUser:list')")
    public void download(HttpServletResponse response, GameUserQueryCriteria criteria) throws IOException {
        gameUserService.download(gameUserService.queryAll(criteria), response);
    }

    @GetMapping
    @Log("查询GameUser")
    @ApiOperation("查询GameUser")
    @PreAuthorize("@el.check('gameUser:list')")
    public ResponseEntity getGameUsers(GameUserQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity<>(gameUserService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @PostMapping
    @Log("新增GameUser")
    @ApiOperation("新增GameUser")
    @PreAuthorize("@el.check('gameUser:add')")
    public ResponseEntity create(@Validated @RequestBody GameUser resources){
        return new ResponseEntity<>(gameUserService.create(resources),HttpStatus.CREATED);
    }

    @PutMapping
    @Log("修改GameUser")
    @ApiOperation("修改GameUser")
    @PreAuthorize("@el.check('gameUser:edit')")
    public ResponseEntity update(@Validated @RequestBody GameUser resources){
        gameUserService.update(resources);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping(value = "/{gameId}")
    @Log("删除GameUser")
    @ApiOperation("删除GameUser")
    @PreAuthorize("@el.check('gameUser:del')")
    public ResponseEntity delete(@PathVariable Integer gameId){
        gameUserService.delete(gameId);
        return new ResponseEntity(HttpStatus.OK);
    }

    @ApiOperation("导入数据测试")
    @Log("图片上传MusicResource")
    @PostMapping(value = "/avatarUpload")
    public ResponseEntity avatarUpload(HttpServletResponse httpServletResponse,
                                     MultipartHttpServletRequest multipartHttpServletRequest) throws  IOException {
        // 设置请求头
        try {
            UploadFileTool.doPost(httpServletResponse);
        }catch(Exception e){
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        String uploadPath;
        MultipartFile file = multipartHttpServletRequest.getFile("file");
        try {
            uploadPath = UploadFileTool.upload(file,path);
            if ("".equals(uploadPath))
                return new ResponseEntity(HttpStatus.NO_CONTENT);
        }catch(IOException e){
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity(uploadPath,HttpStatus.OK);
    }
}