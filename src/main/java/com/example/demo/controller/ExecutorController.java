package com.example.demo.controller;

import com.example.demo.service.AsyncService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExecutorController {
    private static final Logger logger = LoggerFactory.getLogger(ExecutorController.class);

    @Autowired
    private AsyncService asyncService;


    @RequestMapping("/executor")
    public String submit() {

        logger.info("start submit");


        //调用service层的任务

        asyncService.executeAsync();


        logger.info("end submit");
        return "success";
    }
    @RequestMapping("/get")
    public String get() {

        return "com.appril.sample runs fine";
    }


}
