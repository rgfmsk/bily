package com.bilyoner.emre.assignment;

import com.bilyoner.emre.assignment.controller.NumbersController;
import com.bilyoner.emre.assignment.repository.NumbersRepository;
import com.mongodb.DuplicateKeyException;
import com.netflix.discovery.converters.Auto;
import org.bson.Document;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.bilyoner.emre.assignment.model.Number;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@ContextConfiguration(classes = {AppConfig.class})
public class AssignmentApplicationTests {

    @Autowired
    private MongoTemplate repository;

    @Autowired
    private NumbersRepository numbersRepository;


    @Before
    public void before() {
        numbersRepository.deleteAll();
    }

    @After
    public void after() {

    }

    @Test
    public void insert() {
        boolean exceptionThrown = false;

        Number num = new Number();
        num.setNumber(100);
        num.setDate(new Date());
        numbersRepository.insert(num);

        num = new Number();
        num.setNumber(101);
        num.setDate(new Date());
        numbersRepository.insert(num);

        num = new Number();
        num.setNumber(102);
        num.setDate(new Date());
        numbersRepository.insert(num);

        try {
            numbersRepository.insert(num);
        } catch (Exception e) {
            exceptionThrown = true;
            System.out.println("excccc");
        }

        num = new Number();
        num.setNumber(101);
        num.setDate(new Date());
        numbersRepository.delete(num);


        Assert.assertTrue(numbersRepository.findByNumber(100).getDate()
                .before(numbersRepository.findByNumber(102).getDate())
        );
        Assert.assertEquals(2, numbersRepository.findAll().size());
        Assert.assertEquals(true, exceptionThrown);
    }

}
