package com.chatbot.Inteligente.DTO;

import com.chatbot.Inteligente.entities.Conversation;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class ConversationResponseDTO {
     private Long id;
     private String Pergunta;
     private String respostaIA;
     private LocalDateTime dataTime;

     public ConversationResponseDTO(Conversation entity) {
          id = entity.getId();
          Pergunta = entity.getPergunta();
          respostaIA = entity.getRespostaIA();
          dataTime = entity.getDataTime();
     }
}
