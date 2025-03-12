package com.chatbot.Inteligente.controllers;

import com.chatbot.Inteligente.DTO.ConversationDTO;
import com.chatbot.Inteligente.services.ChatbotService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ConversationController {

     private final ChatbotService chatbotService;

     @GetMapping("/")
     public String index(Model model) {
          // Carregar o histórico de conversas aqui
          List<ConversationDTO> conversationHistory = chatbotService.findAllConversation();
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
          List<ConversationDTO> conversationHistory = chatbotService.findAllConversation();
          model.addAttribute("conversationHistory", conversationHistory);

          return "index"; // Retorna para a mesma página com a resposta
     }

     // Adicionar um endpoint para obter uma conversa específica por ID
     @GetMapping("/api/conversation/{id}")
     @ResponseBody
     public ResponseEntity<ConversationDTO> getConversationById(@PathVariable Long id) {
          try {
               ConversationDTO conversation = chatbotService.findConversationById(id);
               return ResponseEntity.ok(conversation);
          } catch (Exception e) {
               return ResponseEntity.notFound().build();
          }
     }
}
