package cn.wldraa.ddz.controller;

import cn.wldraa.ddz.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zhangqian
 */

@RestController
@RequestMapping("/api")
public class TestController extends BaseController {

    @Autowired
    private GameService gameService;

    @RequestMapping("/test")
    public Map<String, Object> test() {
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("success", "true");

        logger.error("2333");
        logger.info("info log!!");

        return result;
    }




}
