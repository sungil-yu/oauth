package com.study.oauth.oauth.domain;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class Worker {

    private String name;
    private String email;
    private String imageUrl;
    private String videoUrl;

    private LocalDateTime createdAt;


    public Worker(String name, String email, String imageUrl, String videoUrl, LocalDateTime createdAt) {
        this.name = name;
        this.email = email;
        this.imageUrl = imageUrl;
        this.videoUrl = videoUrl;
        this.createdAt = createdAt;
    }
}
