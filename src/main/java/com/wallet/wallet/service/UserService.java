package com.wallet.wallet.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wallet.wallet.configuration.SecurityConfiguration;
import com.wallet.wallet.dto.CreateUserDto;
import com.wallet.wallet.model.Role;
import com.wallet.wallet.model.User;
import com.wallet.wallet.repository.UserRepository;

@Service
public class UserService {
    @Autowired
    private SecurityConfiguration securityConfiguration;

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getById(Long id) {
      return userRepository.findById(id);
    }

    public Optional<User> getByEmail(String email) {
      return userRepository.findByEmail(email);
    }

    public User createUser(CreateUserDto createUserDto) {
      User user = new User();
      Role role = new Role();
      role.setName(createUserDto.role());
      user.setEmail(createUserDto.email());
      user.setName(createUserDto.name());
      user.setPassword(securityConfiguration.passwordEncoder().encode(createUserDto.password()));
      user.setRoles(List.of(role));
      return userRepository.save(user);
    }

    public User updateUser(Long id, User userDetails) {
      return userRepository.findById(id)
              .map(user -> {
                  user.setName(userDetails.getName());
                  user.setEmail(userDetails.getEmail());
                  return userRepository.save(user);
              })
              .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
  }

  public void deleteUser(Long id) {
      userRepository.deleteById(id);
  }
}