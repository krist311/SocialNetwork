package ru.hse.kw.controller;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.util.JSONPObject;
import jdk.nashorn.internal.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.BasicJsonParser;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hse.kw.model.Task;
import ru.hse.kw.model.User;
import ru.hse.kw.service.TaskService;
import ru.hse.kw.service.UserService;
import static java.lang.Math.toIntExact;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

@RestController
public class Controller {

    @Autowired
    UserService userService;  //Service which will do all data retrieval/manipulation work

    @Autowired
    TaskService taskService;


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
     public HttpStatus saveTask(@RequestBody String value) {
        BasicJsonParser jsonParser = new BasicJsonParser();
        Map taskMap = jsonParser.parseMap(value);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-dd-mm");
        Date date=null;
        try {

            java.util.Date jDate = sdf.parse((String) taskMap.get("date"));
            date = new Date(jDate.getTime());
        } catch (ParseException ex){
            System.out.println(ex);
        }

        Long lid = (Long) taskMap.get("id");
        int id = lid.intValue();
        Task task = taskService.findById(id);
        if (task != null) {
            task.setName((String)taskMap.get("name"));
            task.setDate(date);
            task.setDescription((String)taskMap.get("description"));
            task.setTags((String)taskMap.get("tags"));
            task.setProgress((long) taskMap.get("progress"));
            task.setGoal((long)taskMap.get("goal"));
        }
        else {
            task = new Task((long) taskMap.get("user_id"),(String)taskMap.get("name"),date,
                    (String)taskMap.get("description"),(String)taskMap.get("tags"), (long) taskMap.get("progress"),
                    (long)taskMap.get("goal"));

            taskService.save(task);
        }

        return HttpStatus.OK;
    }
    @RequestMapping(value = "/registeruser", method = RequestMethod.POST)
    public HttpStatus registerUser(@RequestBody String value) {
        BasicJsonParser jsonParser = new BasicJsonParser();
        Map taskMap = jsonParser.parseMap(value);
        User task = new User((String)taskMap.get("login"),(String)taskMap.get("password"));
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

    @RequestMapping(value = "/gettasks/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Task>> getUser(@PathVariable("id") int user_id) {
        System.out.println("Fetching tasks for user " + user_id);
        List <Task> tasks = taskService.findByUserId(user_id);
        if (tasks == null) {
            System.out.println("Tasks with user id " + user_id + " not found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(tasks, HttpStatus.OK);
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