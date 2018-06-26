package org.open.source.controller;

import org.open.source.biz.DummyBiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.extern.slf4j.Slf4j;

/**
 * Created by Paul on 2018/6/25.
 */
@Controller
@RequestMapping("/dummy")
@Slf4j
public class DummyController {

    @Autowired
    DummyBiz dummyBiz;

    @ResponseBody
    @RequestMapping(value = "/welcome", method = RequestMethod.GET)
    public Object welcome() {
        log.info("welcome you!");
        return dummyBiz.list();
    }
}
