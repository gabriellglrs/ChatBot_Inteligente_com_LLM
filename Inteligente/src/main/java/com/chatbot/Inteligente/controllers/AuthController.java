package com.chatbot.Inteligente.controllers;

import com.chatbot.Inteligente.DTO.UserRequestDTO;
import com.chatbot.Inteligente.DTO.UserResponseDTO;
import com.chatbot.Inteligente.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
public class AuthController {

     private final UserService userService;

     @GetMapping("/login")
     public String loginPage() {
          // Os FlashAttributes serão automaticamente adicionados ao modelo
          return "login";
     }

     @GetMapping("/register")
     public String registerPage(Model model) {
          model.addAttribute("userRequest", new UserRequestDTO());
          return "register";
     }

     @PostMapping("/user/register")
     public String registerUser(@ModelAttribute("userRequest") UserRequestDTO userRequest,
                                RedirectAttributes redirectAttributes) {
          try {
               // Verificar se o usuário já existe
               if (userService.existsByEmail(userRequest.getEmail())) {
                    redirectAttributes.addFlashAttribute("error", "Este email já está em uso.");
                    return "redirect:/register";
               }

               // Registrar o novo usuário
               UserResponseDTO registeredUser = userService.UserRegister(userRequest);

               // Adiciona mensagem de sucesso e redireciona para o login
               redirectAttributes.addFlashAttribute("success", "Registro realizado com sucesso! Faça login para continuar.");
               return "redirect:/login";
          } catch (Exception e) {
               redirectAttributes.addFlashAttribute("error", "Erro ao registrar: " + e.getMessage());
               return "redirect:/register";
          }
     }
}
