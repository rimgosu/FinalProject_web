package kr.spring.entity;

import java.util.List;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.Instant;
import java.time.LocalDateTime;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class MemberInfo {
    private String username;
    private String password;
    private String nickname;
    private String phone;
    private int age;
    private String interest;
    private String mbti;
    private String sport;
    private String drinking;
    private String smoking;
    private String job;
    private String school;
    private String aboutme;
    private Instant registerDate;
    private List<String> role;
    private List<String> address;
    private Map<Integer, String> photo;


    // Add getters and setters for all fields here

    // You can also add constructors as needed
}
