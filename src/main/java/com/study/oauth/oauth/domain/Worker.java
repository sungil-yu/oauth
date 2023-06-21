package com.study.oauth.oauth.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
public class Worker extends BaseTimeEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "video_url")
    private String videoUrl;


    public Worker(String name, String email, String imageUrl, String videoUrl) {
        this.name = name;
        this.email = email;
        this.imageUrl = imageUrl;
        this.videoUrl = videoUrl;
    }
}
