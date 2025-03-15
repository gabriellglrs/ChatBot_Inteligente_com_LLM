package com.chatbot.Inteligente.DTO;

import com.chatbot.Inteligente.entities.Conversation;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class UserResponseDTO {
     private Long id;
     private String name;
     private String email;
     private String password;
     private List<Conversation> conversations = new ArrayList<>();
}
