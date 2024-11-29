package com.picpay.project.services;

import com.picpay.project.domain.user.User;
import com.picpay.project.domain.user.UserType;
import com.picpay.project.dtos.UserDTO;
import com.picpay.project.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public void validateTransaction(User sender, BigDecimal amount) throws Exception{
        if(sender.getUserType() == UserType.MERCHANT){
            throw new Exception("User type merchant not allowed to send transactions");
        }

        if(sender.getBalance().compareTo(amount) < 0){
            throw new Exception("User don't have balance for transaction");
        }
    }

    public User findUserById(UUID id) throws Exception{
        return this.userRepository.findById(id).orElseThrow(() -> new Exception("User not found"));
    }

    public void saveUser(User user){
        this.userRepository.save(user);
    }

    public User createUser(UserDTO userDTO) {
        User user = new User(userDTO);

        this.userRepository.save(user);
        return user;
    }

    public List<User> getAllUsers() {
        return this.userRepository.findAll();
    }
}
