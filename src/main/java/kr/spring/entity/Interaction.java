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
public class Interaction {
	
	private String from_to; // from, to만 인자로 받아야 함
	private Instant interaction_regdate;
	private UUID interaction_uuid;
	private Instant last_checked; // 마지막으로 채팅을 읽을 때
	private String my_username;
	private String opponent_username;
	private String type;
	private UUID type_uuid;
	
}