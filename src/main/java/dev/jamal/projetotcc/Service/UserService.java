package dev.jamal.projetotcc.Service;

import dev.jamal.projetotcc.Entities.User;
import dev.jamal.projetotcc.Repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {

    private final UserRepository userRepository;

    public User cadastrar(User user){
        if (userRepository.existsByEmail(user.getEmail())){
            throw new RuntimeException("Email já cadastrado.");
        }
        return userRepository.save(user);
    }

    public List<User> listarTodos(){
        return userRepository.findAll();
    }

    public User buscarPorId(Long id){
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
    }
}
