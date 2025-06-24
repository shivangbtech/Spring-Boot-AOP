package com.example.aoppoc.controller;

import com.example.aoppoc.service.HelloService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/hello")
public class HelloController {

  private final HelloService helloService;

  public HelloController(HelloService helloService) {
    this.helloService = helloService;
  }

  @GetMapping("/{name}")
  public String greet(@PathVariable String name) {
    return helloService.sayHello(name);
  }
}
