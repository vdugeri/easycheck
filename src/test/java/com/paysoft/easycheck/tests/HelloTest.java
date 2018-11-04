package com.paysoft.easycheck.tests;


import com.paysoft.easycheck.controllers.HelloWorldController;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.testng.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.testng.Assert;
import org.testng.annotations.Test;


public class HelloTest extends Arquillian {



    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addClass(HelloWorldController.class);
    }

    @Test()
    public void sayHelloTest() {
        HelloWorldController controller = new HelloWorldController();
        Assert.assertEquals(controller.sayHello(), "Hello, World!");
    }
}
