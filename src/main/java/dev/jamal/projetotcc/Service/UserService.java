package dev.jamal.projetotcc.Service;

import dev.jamal.projetotcc.DTO.User.UserCreateRequestDTO;
import dev.jamal.projetotcc.DTO.User.UserResponseDTO;
import dev.jamal.projetotcc.Entities.User;
import dev.jamal.projetotcc.Mapper.UserMapper;
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
    private final UserMapper userMapper;

    public UserResponseDTO cadastrar(UserCreateRequestDTO dto){

        if (userRepository.existsByEmail(dto.getEmail())){
            throw new RuntimeException("Email já cadastrado.");
        }

        User user = userMapper.toEntity(dto);
        User salvo= userRepository.save(user);

        return userMapper.toResponseDTO(salvo);
    }

    @Transactional
    public List<UserResponseDTO> listarTodos(){
        return userRepository.findAll()
                .stream()
                .map(userMapper::toResponseDTO)
                .toList();
    }

    // Esse método pode existir para uso interno de outros services.
    //  Mas controller não deve chamar ele diretamente.
    @Transactional
    public User buscarEntidadePorId(Long id){
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
    }
}
