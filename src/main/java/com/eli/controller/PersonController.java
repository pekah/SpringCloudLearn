package com.eli.controller;


import com.eli.bean.Person;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.awt.*;

@RestController
@RequestMapping(value="/api", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class PersonController {

    @Value("${person.name}")
    private String name;

    @Value("${person.age}")
    private Integer age;

    @RequestMapping(value="/person", method = RequestMethod.GET)
    public Person getPerson() {
        Person p = new Person();
        p.setName(name);
        p.setAge(age);

        System.out.println("name is " + name);
        System.out.println("age is " + age);

        return p;
    }

}
