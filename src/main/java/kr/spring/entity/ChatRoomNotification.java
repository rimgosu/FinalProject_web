package kr.spring.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@EqualsAndHashCode(callSuper=false)
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ChatRoomNotification extends Interaction {

    private int notification_count;

 // Interaction 객체를 인자로 받는 생성자
    public ChatRoomNotification(Interaction interaction) {
        super(interaction.getFrom_to(), interaction.getInteraction_regdate(), interaction.getInteraction_uuid(), 
              interaction.getLast_checked(), interaction.getMy_username(), interaction.getOpponent_username(), 
              interaction.getType(), interaction.getType_uuid());
        // notification_count는 기본값으로 설정하거나, setter를 통해 나중에 설정
        this.notification_count = 0;
    }
}