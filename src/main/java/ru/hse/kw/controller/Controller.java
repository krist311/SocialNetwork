package ru.hse.kw.controller;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.util.JSONPObject;
import jdk.nashorn.internal.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.BasicJsonParser;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.hse.kw.model.Task;
import ru.hse.kw.model.User;
import ru.hse.kw.service.UserService;

import java.util.List;
import java.util.Map;

@RestController
public class Controller {

    @Autowired
    UserService userService;  //Service which will do all data retrieval/manipulation work


    //-------------------Retrieve All Users--------------------------------------------------------

    @RequestMapping(value = "/user1234", method = RequestMethod.GET)
    public ResponseEntity<List<User>> listAllUsers() {
        List<User> users = userService.findAllUsers();
        if (users.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @RequestMapping(value = "/savetask", method = RequestMethod.POST)
    public HttpStatus saveTask(@RequestBody String valueOne) {
        BasicJsonParser jsonParser = new BasicJsonParser();
        Map taskMap = jsonParser.parseMap(valueOne);
        Task task = new Task((String)taskMap.get("name"),(String)taskMap.get("date"),(String)taskMap.get("description"),(String)taskMap.get("tags"));
        System.out.print(jsonParser.parseMap(valueOne));
        return HttpStatus.OK;
    }

    //-------------------Retrieve Single User--------------------------------------------------------

    @RequestMapping(value = "/getuser/{login}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> getUser(@PathVariable("login") String login) {
        System.out.println("Fetching User with login " + login);
        User user = userService.findByLogin(login);
        if (user == null) {
            System.out.println("User with id " + login + " not found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @RequestMapping(value = "/getinfo/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> getinfo(@PathVariable("id") int id) {
        System.out.println("Fetching User with id " + id);
        User user = userService.findById(id);
        if (user == null) {
            System.out.println("User with id " + id + " not found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }


}