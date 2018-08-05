package com.bilyoner.emre.assignment.controller;

import com.bilyoner.emre.assignment.model.Number;
import com.bilyoner.emre.assignment.repository.NumbersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/numbers")
public class NumbersController {
    @Autowired
    private NumbersRepository repository;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<Number> getAllNumbers(@Valid @RequestHeader(required = false) String sort) {
        Comparator<Number> numberComparator = Comparator.comparingInt(Number::getNumber); // prepare comparator for sorting
        if ("descending".equals(sort)) {// sort reverse if descending
            numberComparator = numberComparator.reversed();
        }
        List<Number> all = repository.findAll().stream().collect(Collectors.toList());
        all.sort(numberComparator);
        return all;
    }

    @RequestMapping(value = "/{number}", method = RequestMethod.GET)
    public Number getNumberById(@PathVariable("number") Integer number) throws Exception {
        Number byNumber = repository.findByNumber(number);
        if (byNumber == null) { // throw exception if not found
            throw new Exception("101 - Resource Not Found");
        }
        return byNumber;
    }

    @RequestMapping(value = "/max", method = RequestMethod.GET)
    public Number getMax() {  // return max using java 1.8
        return repository.findAll().stream().max(Comparator.comparingInt(Number::getNumber)).get();
    }

    @RequestMapping(value = "/min", method = RequestMethod.GET)
    public Number getMin() {// return min using java 1.8
        return repository.findAll().stream().min(Comparator.comparingInt(Number::getNumber)).get();
    }

    @RequestMapping(value = "/{number}", method = RequestMethod.POST)
    public void createNumber(@PathVariable("number") Integer num) throws Exception {
        Number byNumber = repository.findByNumber(num);
        if (byNumber == null) { // if number not exists
            Number number = new Number();
            number.setNumber(num);
            number.setDate(new Date());
            repository.save(number);
        } else { // if exists
            throw new Exception("100 - Resource already exists");
        }
    }

    @RequestMapping(value = "/{number}", method = RequestMethod.DELETE)
    public void deletePet(@PathVariable Integer number) throws Exception {
        Number byNumber = repository.findByNumber(number);
        if (byNumber == null) {
            throw new Exception("102 - Resource Not Found for Deletion");
        } else {
            repository.delete(byNumber);
        }
    }
}

