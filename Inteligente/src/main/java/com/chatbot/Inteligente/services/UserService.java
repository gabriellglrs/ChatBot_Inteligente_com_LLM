package com.chatbot.Inteligente.services;

import com.chatbot.Inteligente.DTO.UserRequestDTO;
import com.chatbot.Inteligente.DTO.UserResponseDTO;
import com.chatbot.Inteligente.config.mapper.ModelMapperConfig;
import com.chatbot.Inteligente.entities.User;
import com.chatbot.Inteligente.exceptions.RecursoNaoEncontradoException;
import com.chatbot.Inteligente.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

     private final UserRepository userRepository;
     private final PasswordEncoder passwordEncoder;
     private final ModelMapperConfig modelMapper;

     public UserResponseDTO UserRegister(UserRequestDTO requestDTO) {
          User user = modelMapper.modelMapper().map(requestDTO, User.class);
          user.setPassword(passwordEncoder.encode(requestDTO.getPassword()));
          return modelMapper.modelMapper().map(userRepository.save(user), UserResponseDTO.class);
     }

     public UserResponseDTO UserFindById(Long id) {
          User user = userRepository.findById(id)
                  .orElseThrow(() -> new RecursoNaoEncontradoException("Usuário não encontrado na base de dados: " + id));
          return modelMapper.modelMapper().map(user, UserResponseDTO.class);
     }

     @Override
     public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
          User user = userRepository.findByEmail(username)
                  .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado na base de dados: " + username));
          return org.springframework.security.core.userdetails.User.builder()
                  .username(user.getEmail())
                  .password(user.getPassword())
                  .authorities("USER")
                  .build();
     }

     public boolean existsByEmail(String email) {
          return userRepository.findByEmail(email).isPresent();
     }
}
