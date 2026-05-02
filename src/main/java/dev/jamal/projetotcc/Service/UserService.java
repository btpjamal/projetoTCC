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


    // CREATE
    public UserResponseDTO cadastrar(UserCreateRequestDTO dto){

        if (userRepository.existsByEmail(dto.getEmail())){
            throw new RuntimeException("Email já cadastrado.");
        }

        User user = userMapper.toEntity(dto);
        User salvo= userRepository.save(user);

        return userMapper.toResponseDTO(salvo);
    }

    // READ ALL
    @Transactional
    public List<UserResponseDTO> listarTodos(){
        return userRepository.findAll()
                .stream()
                .map(userMapper::toResponseDTO)
                .toList();
    }

    // READ BY ID
    @Transactional
    public UserResponseDTO buscarPorId(Long id) {

        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        return userMapper.toResponseDTO(user);
    }

    // UPDATE
    public UserResponseDTO atualizar(
        Long id,
        UserCreateRequestDTO dto
    ) {

        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        // valida email duplicado
        userRepository.findByEmail(dto.getEmail())
                .ifPresent(outro -> {
                    if (!outro.getId().equals(id)) {
                        throw new RuntimeException("Email já está em uso.");
                    }
                });

        user.setNome(dto.getNome());
        user.setEmail(dto.getEmail());
        user.setSenha(dto.getSenha());

        User atualizado = userRepository.save(user);

        return userMapper.toResponseDTO(atualizado);
    }

    // DELETE
    public void deletar(Long id) {
        if (!userRepository.existsById(id)) {
            throw new RuntimeException("Usuário não encontrado.");
        }

        userRepository.deleteById(id);
    }

    // Esse método pode existir para uso interno de outros services.
    //  Mas controller não deve chamar ele diretamente.
    @Transactional
    public User buscarEntidadePorId(Long id){
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
    }
}
