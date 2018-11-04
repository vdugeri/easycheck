package com.paysoft.easycheck.services;

import com.paysoft.easycheck.dtos.HelloDto;
import javax.ejb.Stateless;

@Stateless
public class HelloService {

    public HelloDto sayHello() {
        return new HelloDto("Hello, World");
    }
}
