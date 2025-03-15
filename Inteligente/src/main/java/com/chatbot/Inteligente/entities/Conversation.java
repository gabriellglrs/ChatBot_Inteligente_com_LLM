package com.chatbot.Inteligente.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "tb_conversation")
@Getter
@Setter
@NoArgsConstructor
public class Conversation {

     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Long id;

     @Column(length = 500)
     private String Pergunta;

     @Column(columnDefinition = "TEXT", name = "resposta_ia")
     private String respostaIA;

     @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
     @Column(name = "data_hora")
     private LocalDateTime dataTime = LocalDateTime.now();

     @ManyToOne
     @JoinColumn(name = "user_id")
     private User user;

     public Conversation(String pergunta, String respostaIA) {
          Pergunta = pergunta;
          this.respostaIA = respostaIA;
     }

     public Conversation(Long id, String pergunta, String respostaIA) {
          this.id = id;
          Pergunta = pergunta;
          this.respostaIA = respostaIA;
     }

}
