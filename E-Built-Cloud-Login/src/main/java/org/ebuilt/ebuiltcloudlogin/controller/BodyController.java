package org.ebuilt.ebuiltcloudlogin.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/body")
@Tag(name = "body参数")
@RequiredArgsConstructor
public class BodyController {

    @Operation(summary = "普通body请求")
    @PostMapping("/body")
    public ResponseEntity body(){
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "普通body请求+Param+Header+Path")
    @Parameters({
            @Parameter(name = "id",description = "文件id",in = ParameterIn.PATH),
            @Parameter(name = "token",description = "请求token",required = true,in = ParameterIn.HEADER),
            @Parameter(name = "name",description = "文件名称",required = true,in=ParameterIn.QUERY)
    })
    @PostMapping("/bodyParamHeaderPath/{id}")
    public ResponseEntity bodyParamHeaderPath(@PathVariable("id") String id, @RequestHeader("token") String token, @RequestParam("name")String name){
        return ResponseEntity.ok().build();
    }
}
