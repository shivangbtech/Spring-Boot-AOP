package com.example.aoppoc.service;

import com.example.aoppoc.annotation.TrackTime;
import org.springframework.stereotype.Service;

@Service
public class HelloService {

  @TrackTime
  public String sayHello(String name) {
    simulateDelay();
    if ("error".equalsIgnoreCase(name)) {
      throw new RuntimeException("Simulated exception");
    }
    return "Hello, " + name + "!";
  }

  private void simulateDelay() {
    try {
      Thread.sleep(1000); // Simulate some delay
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
    }
  }
}
