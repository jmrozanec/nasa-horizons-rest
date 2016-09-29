package com.neo.ui;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
    private static final Logger log = LoggerFactory.getLogger(HomeController.class);
    @RequestMapping("/home")
    public String home() {
        return "forward:/index.html";
    }

    @RequestMapping("/pager/documents/{documentid}")
    public String pages(@PathVariable(value="documentid") int documentid) {
        log.info(String.format("[pager] documentid: %s", documentid));
        return "forward:/html/pager.html";
    }

    @RequestMapping("/documentation")
    public String documentation() {
        return "forward:/swagger-ui.html";
    }

    @RequestMapping("/statuscheck")
    public String statuscheck() {
        return "up";
    }

    @RequestMapping("/pageview")
    public String pageview() {
        return "forward:/html/pageview.html";
    }
}
