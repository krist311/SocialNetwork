package ru.hse.kw.controller;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import ru.hse.kw.model.Task;
import ru.hse.kw.model.User;
import ru.hse.kw.service.FollowService;
import ru.hse.kw.service.TaskService;
import ru.hse.kw.service.UserService;

import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class Controller {

    @Autowired
    UserService userService;

    @Autowired
    TaskService taskService;

    @Autowired
    FollowService followService;


    @RequestMapping(value = "/getfollowing/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<User>> getFollowing(@PathVariable("id") int id) {
        System.out.println("Fetching User with login " + id);
        List<User> users = userService.findUsersByIds( followService.getFollowingList(id));
        if (users == null) {
            System.out.println("User with id " + id + " not found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        List<User> list = new ArrayList<>();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @RequestMapping(value = "/getfollowers/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> getFollowers(@PathVariable("id") int id) {
        System.out.println("Fetching User with login " + id);
        User user = userService.findById(id);
        if (user == null) {
            System.out.println("User with id " + id + " not found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
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
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> taskMap = new HashMap<String, Object>();

        try {
            // convert JSON string to Map
            taskMap = mapper.readValue(value, new TypeReference<Map<String, String>>(){});
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

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
            task.setProgress((Long) taskMap.get("progress"));
            task.setGoal((Long)taskMap.get("goal"));
        }
        else {
            task = new Task((Long) taskMap.get("user_id"),(String)taskMap.get("name"),date,
                    (String)taskMap.get("description"),(String)taskMap.get("tags"), (Long) taskMap.get("progress"),
                    (Long)taskMap.get("goal"));

            taskService.save(task);
        }

        return HttpStatus.OK;
    }
    @RequestMapping(value = "/registeruser", method = RequestMethod.POST)
    public HttpStatus registerUser(@RequestBody String value) {
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> taskMap = new HashMap<String, Object>();

        try {
            // convert JSON string to Map
            taskMap = mapper.readValue(value, new TypeReference<Map<String, String>>(){});
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        User task = new User((String)taskMap.get("login"),(String)taskMap.get("password"));
        return HttpStatus.OK;
    }

    @RequestMapping(value = "/updateuserinfo", method = RequestMethod.PUT)
    public HttpStatus updateUserInfo(@RequestBody String value) {
        System.out.println("Update User with  " + value);
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> userInfo = new HashMap<String, Object>();

        try {
            // convert JSON string to Map
            userInfo = mapper.readValue(value, new TypeReference<Map<String, String>>(){});
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Integer id = Integer.valueOf((String) userInfo.get("id"));
        User user = userService.findById(id);
        if (user != null) {
            user.setLogin((String)userInfo.get("login"));
            user.setInfo((String)userInfo.get("info"));
            user.setEmail((String)userInfo.get("email"));
            user.setPassword((String)userInfo.get("password"));
            userService.update(user);
        }
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

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage() {
        return "sing-in-sign-up";
    }
}