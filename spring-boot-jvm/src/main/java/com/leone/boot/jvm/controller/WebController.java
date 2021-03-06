package com.leone.boot.jvm.controller;

import com.leone.boot.jvm.service.DataService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author leone
 * @since 2018-07-14
 **/

@Slf4j
@RestController
public class WebController {

    @Autowired
    private DataService dataService;

    @GetMapping("/bytes")
    public String add(@RequestParam Integer count) throws Exception {
        dataService.addObjects(count);
        return "count:" + count;
    }

    @GetMapping("/stop")
    public String stop() {
        dataService.stop();
        return "stop";
    }


}
