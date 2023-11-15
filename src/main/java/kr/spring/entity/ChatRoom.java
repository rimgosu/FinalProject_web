package kr.spring.entity;

import java.util.UUID;
import java.time.Instant;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ChatRoom {

    private String room_describe;
    private String room_joined;
    private String room_maker;
    private UUID room_uuid;
    private Instant room_regdate;
    private String room_status;
    private String room_title;
    
}