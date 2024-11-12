package com.worthtech.interview.spring.test.service;

import com.worthtech.interview.spring.test.dto.Post;
import com.worthtech.interview.spring.test.dto.User;
import com.worthtech.interview.spring.test.dto.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class UserService {

    String baseUrl = "https://jsonplaceholder.typicode.com/";


    public UserDetails[] getUserData() {

        WebClient webClient = WebClient.builder().baseUrl(baseUrl).build();
        User[] userResponse = webClient.get().uri("users").retrieve()
                .bodyToMono(User[].class).block();
        System.out.println(userResponse);
        Post[] postResponse = webClient.get().uri("posts").retrieve()
                .bodyToMono(Post[].class).block();
        System.out.println(postResponse);

        return getUserDetails(userResponse, postResponse);
    }

    private UserDetails[] getUserDetails(User[] userResponse, Post[] postResponse) {
        UserDetails[] userDetails = new UserDetails[10];
        int k = 0;
        for (int i = 0; i < userResponse.length; i++) {
            for (int j = 0; j < postResponse.length; j++) {
                if (userResponse[i].getId() == postResponse[j].getId()) {
                    UserDetails userDetails1 = new UserDetails();
                    userDetails1.setId(userResponse[i].getId());
                    userDetails1.setCompanyName(userResponse[i].getCompany().getName());
                    userDetails1.setLat(userResponse[i].getAddress().getGeo().getLat());
                    userDetails1.setLng(userResponse[i].getAddress().getGeo().getLng());
                    userDetails1.setBody(postResponse[j].getBody());
                    userDetails1.setTitle(postResponse[j].getTitle());
                    userDetails[k] = userDetails1;
                    k++;
                }
            }
        }
        return userDetails;
    }
}
