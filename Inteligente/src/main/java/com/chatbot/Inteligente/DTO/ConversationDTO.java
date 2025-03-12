package com.chatbot.Inteligente.DTO;

import com.chatbot.Inteligente.models.Conversation;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class ConversationDTO {
     private Long id;
     private String Pergunta;
     private String respostaIA;
     private LocalDateTime dataTime;

     public ConversationDTO(Conversation entity) {
          id = entity.getId();
          Pergunta = entity.getPergunta();
          respostaIA = entity.getRespostaIA();
          dataTime = entity.getDataTime();
     }
}
