package org.example;

import org.example.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class Communication {

    private String sessionID;

    @Autowired
    private RestTemplate restTemplate;
    private final String URL = "http://94.198.50.185:7081/api/users";



    public List<User> getAllUsers() {
        ResponseEntity<List<User>> responseEntity =
                restTemplate.exchange(URL, HttpMethod.GET, null, new ParameterizedTypeReference<List<User>>() {
                });
        List<User> allUsers = responseEntity.getBody();
        String cookie = responseEntity.getHeaders().get("set-cookie").get(0);
        sessionID = cookie;
        System.out.println(sessionID);
        return allUsers;
    }

    public void saveUser (User user){
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Cookie", sessionID);

        ResponseEntity<String> responseEntity = restTemplate.exchange(URL, HttpMethod.POST, new HttpEntity<>(user, httpHeaders), String.class);
        System.out.println(responseEntity.getBody());
        System.out.println("user add");

    }
    public void updateUser (User user){
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Cookie", sessionID);

        ResponseEntity<String> responseEntity = restTemplate.exchange(URL, HttpMethod.PUT, new HttpEntity<>(user, httpHeaders), String.class);

        System.out.println(responseEntity.getBody());
        System.out.println("user update");

    }
    public void deleteUser (int id) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Cookie", sessionID);
        String URLDelete = URL + "/" + id;

        ResponseEntity<String> responseEntity = restTemplate.exchange(URLDelete, HttpMethod.DELETE, new HttpEntity<>(null, httpHeaders), String.class);
        System.out.println(responseEntity.getBody());

    }

}
