package com.project.course.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SignUpRequest {
    private String login;
    private String password;
    private String name;
    private int cardNumber;
}
