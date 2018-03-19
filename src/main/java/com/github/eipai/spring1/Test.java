package com.github.eipai.spring1;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("com.github.eipai.spring1.xml");
        Animal dog = (Animal) context.getBean("AnimalDog");
        dog.sayHello();

    }

}