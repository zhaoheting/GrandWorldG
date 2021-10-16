package com.example.GrandWorldG;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * TODO
 *
 * @author HeTing.Zhao
 * @since 2021/10/16
 **/
@RestController
public class HelloWorld {
    @GetMapping("/hello")
    public String hello() {
        return "Hello World.";
    }
}
