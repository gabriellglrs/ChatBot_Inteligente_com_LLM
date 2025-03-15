package com.chatbot.Inteligente.controllers;

import com.chatbot.Inteligente.DTO.ConversationResponseDTO;
import com.chatbot.Inteligente.services.ChatbotService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ConversationController {

     private final ChatbotService chatbotService;

     @GetMapping("/home")
     public String index(Model model) {
          // Carregar o histórico de conversas aqui
          List<ConversationResponseDTO> conversationHistory = chatbotService.findAllConversation();
          model.addAttribute("conversationHistory", conversationHistory);
          return "index"; // Retorna a página inicial (index.html)
     }

     @GetMapping("/obter-resposta")
     public String obterResposta(@RequestParam(required = false) String pergunta, Model model) {
          if (pergunta != null && !pergunta.isEmpty()) {
               String resposta = chatbotService.obterResposta(pergunta);
               model.addAttribute("resposta", resposta);
               model.addAttribute("pergunta", pergunta);

          }

          // Adicionar o histórico de conversas ao model para manter na página após o redirecionamento
          List<ConversationResponseDTO> conversationHistory = chatbotService.findAllConversation();
          model.addAttribute("conversationHistory", conversationHistory);

          return "index"; // Retorna para a mesma página com a resposta
     }

     // Adicionar um endpoint para obter uma conversa específica por ID
     @GetMapping("/api/conversation/{id}")
     @ResponseBody
     public ResponseEntity<ConversationResponseDTO> getConversationById(@PathVariable Long id) {
          try {
               ConversationResponseDTO conversation = chatbotService.findConversationById(id);
               return ResponseEntity.ok(conversation);
          } catch (Exception e) {
               return ResponseEntity.notFound().build();
          }
     }

     @DeleteMapping("/api/conversation/{id}")
     @ResponseBody
     public ResponseEntity<Void> deleteConversation(@PathVariable Long id) {
          try {
               chatbotService.deleteConversationById(id);
               return ResponseEntity.ok().build();
          } catch (Exception e) {
               return ResponseEntity.notFound().build();
          }
     }
}
