package com.chatbot.Inteligente.services;

import com.chatbot.Inteligente.DTO.ConversationResponseDTO;
import com.chatbot.Inteligente.entities.Conversation;
import com.chatbot.Inteligente.repositories.ConversationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class ChatbotService {

     @Value("${spring.ai.ollama.model}")
     private String model;

     @Value("${spring.ai.ollama.base-url}")
     private String url;

     private final RestTemplate restTemplate;
     private final ConversationRepository repository;

     public String obterResposta(String pergunta) {
          log.info("Iniciando processamento da pergunta: '{}'", pergunta);

          try {
               // Criando o corpo da requisição
               Map<String, Object> requestBody = new HashMap<>();
               requestBody.put("model", model);
               requestBody.put("prompt", pergunta);
               requestBody.put("stream", false); // Desativando o streaming para obter a resposta completa

               log.debug("Enviando requisição para modelo: {}", model);

               HttpHeaders headers = new HttpHeaders();
               headers.setContentType(MediaType.APPLICATION_JSON);

               HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<>(requestBody, headers);

               // Enviando a requisição POST
               log.debug("Enviando requisição para URL: {}", url + "/api/generate");
               ResponseEntity<Map> response = restTemplate.postForEntity(url + "/api/generate", requestEntity, Map.class);

               log.debug("Resposta recebida com status HTTP: {}", response.getStatusCode());

               // Pegando a resposta do chatbot
               if (response.getBody() != null && response.getBody().containsKey("response")) {
                    String resposta = response.getBody().get("response").toString();
                    log.info("Resposta obtida com sucesso para a pergunta: '{}'", pergunta);
                    repository.save(new Conversation(pergunta, resposta));
                    return resposta;
               }

               log.warn("Resposta obtida sem o campo 'response': {}", response.getBody());
               return "Erro ao obter resposta do chatbot.";
          } catch (HttpClientErrorException e) {
               log.error("Erro de cliente HTTP: {} - {}", e.getStatusCode(), e.getMessage());
               return "Erro ao comunicar com o modelo: " + e.getMessage();
          } catch (HttpServerErrorException e) {
               log.error("Erro do servidor Ollama: {} - {}", e.getStatusCode(), e.getMessage());
               return "Serviço do chatbot indisponível no momento. Tente novamente mais tarde.";
          } catch (ResourceAccessException e) {
               log.error("Erro de conexão ou timeout: {}", e.getMessage());
               return "Não foi possível conectar ao serviço do chatbot. Verifique a conexão.";
          } catch (Exception e) {
               log.error("Erro inesperado ao processar a requisição: ", e);
               return "Erro ao obter resposta do chatbot.";
          }
     }

     public List<ConversationResponseDTO> findAllConversation() {
          var conversation = repository.findAll();
         return conversation.stream().map(ConversationResponseDTO::new).toList();
     }

     public ConversationResponseDTO findConversationById(Long id) {
          return repository.findById(id)
                  .map(ConversationResponseDTO::new)
                  .orElseThrow(() -> new RuntimeException("Conversa não encontrada"));
     }

     public void deleteConversationById(Long id) {
          repository.deleteById(id);
     }
}