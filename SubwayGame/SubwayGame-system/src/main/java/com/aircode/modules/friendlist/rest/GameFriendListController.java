package com.aircode.modules.friendlist.rest;

import com.aircode.aop.log.Log;
import com.aircode.modules.friendlist.domain.GameFriendList;
import com.aircode.modules.friendlist.service.GameFriendListService;
import com.aircode.modules.friendlist.service.dto.GameFriendListQueryCriteria;
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
@Api(tags = "GameFriendList管理")
@RestController
@RequestMapping("/api/gameFriendList")
public class GameFriendListController {

    private final GameFriendListService gameFriendListService;

    public GameFriendListController(GameFriendListService gameFriendListService) {
        this.gameFriendListService = gameFriendListService;
    }

    @Log("导出数据")
    @ApiOperation("导出数据")
    @GetMapping(value = "/download")
    @PreAuthorize("@el.check('gameFriendList:list')")
    public void download(HttpServletResponse response, GameFriendListQueryCriteria criteria) throws IOException {
        gameFriendListService.download(gameFriendListService.queryAll(criteria), response);
    }

    @GetMapping
    @Log("查询GameFriendList")
    @ApiOperation("查询GameFriendList")
    @PreAuthorize("@el.check('gameFriendList:list')")
    public ResponseEntity getGameFriendLists(GameFriendListQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity<>(gameFriendListService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @PostMapping
    @Log("新增GameFriendList")
    @ApiOperation("新增GameFriendList")
    @PreAuthorize("@el.check('gameFriendList:add')")
    public ResponseEntity create(@Validated @RequestBody GameFriendList resources){
        return new ResponseEntity<>(gameFriendListService.create(resources),HttpStatus.CREATED);
    }

    @PutMapping
    @Log("修改GameFriendList")
    @ApiOperation("修改GameFriendList")
    @PreAuthorize("@el.check('gameFriendList:edit')")
    public ResponseEntity update(@Validated @RequestBody GameFriendList resources){
        gameFriendListService.update(resources);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping(value = "/{friendlistId}")
    @Log("删除GameFriendList")
    @ApiOperation("删除GameFriendList")
    @PreAuthorize("@el.check('gameFriendList:del')")
    public ResponseEntity delete(@PathVariable Integer friendlistId){
        gameFriendListService.delete(friendlistId);
        return new ResponseEntity(HttpStatus.OK);
    }
}