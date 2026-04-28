package dev.jamal.projetotcc;

import dev.jamal.projetotcc.DTO.Feedback.FeedbackCreateRequestDTO;
import dev.jamal.projetotcc.DTO.Hobby.HobbyCreateRequestDTO;
import dev.jamal.projetotcc.DTO.Hobby.HobbyResponseDTO;
import dev.jamal.projetotcc.DTO.User.UserCreateRequestDTO;
import dev.jamal.projetotcc.DTO.User.UserResponseDTO;
import dev.jamal.projetotcc.Entities.*;
import dev.jamal.projetotcc.Repository.*;
import dev.jamal.projetotcc.Service.FeedbackService;
import dev.jamal.projetotcc.Service.HobbyService;
import dev.jamal.projetotcc.Service.RecommendationService;
import dev.jamal.projetotcc.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("dev")
@RequiredArgsConstructor
public class TestServiceRunner {

    private final UserService userService;
    private final HobbyService hobbyService;
    private final FeedbackService feedbackService;

    private final UserRepository userRepository;
    private final HobbyRepository hobbyRepository;
    private final HobbyCategoryRepository categoryRepository;
    private final InterestRepository interestRepository;

    private final RecommendationService recommendationService;
    private final UserProfileRepository userProfileRepository;
    private final UserInterestRepository userInterestRepository;

    @Bean
    CommandLineRunner run() {
        return args -> {

            System.out.println("\n==============================");
            System.out.println("   INICIANDO TESTE SERVICES");
            System.out.println("==============================");

            // ==========================
            // CATEGORY
            // ==========================
            HobbyCategory esporte = categoryRepository
                    .findAll()
                    .stream()
                    .filter(c -> c.getNome().equalsIgnoreCase("Esporte"))
                    .findFirst()
                    .orElseGet(() -> {
                        HobbyCategory nova = new HobbyCategory();
                        nova.setNome("Esporte");
                        return categoryRepository.save(nova);
                    });

            System.out.println("Categoria OK: " + esporte.getNome());

            // ==========================
            // USER
            // ==========================
            UserResponseDTO user = userRepository
                    .findByEmail("joao@email.com")
                    .map(u -> new UserResponseDTO(
                            u.getId(),
                            u.getNome(),
                            u.getEmail(),
                            u.getDataCadastro()
                    ))
                            .orElseGet(() ->{
                                UserCreateRequestDTO dto= new UserCreateRequestDTO();
                                dto.setNome("João");
                                dto.setEmail("joao@email.com");
                                dto.setSenha("123");

                                return userService.cadastrar(dto);
                            });

            System.out.println("Usuário OK: " + user.getNome());

            // ==========================
            // HOBBY
            // ==========================
            HobbyResponseDTO hobby = hobbyRepository
                    .findAll()
                    .stream()
                    .filter(h -> h.getNome().equalsIgnoreCase("Corrida"))
                    .findFirst()
                    .map(h -> new HobbyResponseDTO(
                            h.getId(),
                            h.getNome(),
                            h.getDescricao(),
                            h.getCustoEstimado(),
                            h.getNivelDificuldade(),
                            h.getTempoNecessario(),
                            h.getTipoSocializacao(),
                            null
                    ))
                    .orElseGet(() -> {
                        HobbyCreateRequestDTO dto = new HobbyCreateRequestDTO();
                        dto.setNome("Corrida");
                        dto.setDescricao("Atividade física ao ar livre");
                        dto.setCustoEstimado(0.0);
                        dto.setNivelDificuldade(2);
                        dto.setTempoNecessario(1.0);
                        dto.setTipoSocializacao(Hobby.TipoSocial.INDIVIDUAL);
                        dto.setCategoryId(esporte.getId());

                        return hobbyService.cadastrar(dto);
                    });


            System.out.println("Hobby OK: " + hobby.getNome());

            // ==========================
            // INTEREST
            // ==========================
            Interest interest = interestRepository
                    .findAll()
                    .stream()
                    .filter(i -> i.getNome().equalsIgnoreCase("Esportes"))
                    .findFirst()
                    .orElseGet(() -> {

                        Interest novo = new Interest();
                        novo.setNome("Esportes");

                        return interestRepository.save(novo);
                    });

            System.out.println("Interesse OK: " + interest.getNome());

            // ==========================
            // FEEDBACK
            // ==========================
            boolean feedbackExiste = feedbackService
                    .listarPorUsuario(user.getId())
                    .stream()
                    .anyMatch(f ->
                            f.getHobby().getId().equals(hobby.getId())
                    );

            if (!feedbackExiste) {

                FeedbackCreateRequestDTO dto= new FeedbackCreateRequestDTO();
                dto.setUserId(user.getId());
                dto.setHobbyId(hobby.getId());
                dto.setRating(5);

                feedbackService.avaliar(dto);

                System.out.println("Feedback criado.");
            } else {
                System.out.println("Feedback já existente.");
            }

            // ==========================
            // USER PROFILE
            // ==========================
            UserProfile profile = userProfileRepository
                    .findByUserId(user.getId())
                    .orElseGet(() -> {
                        User usuarioEntidade = userRepository.findById(user.getId())
                                .orElseThrow();

                        UserProfile novo = new UserProfile();
                        novo.setUser(usuarioEntidade);
                        novo.setTempoDisponivel(2.0);
                        novo.setOrcamento(50.0);
                        novo.setNivelSocial(UserProfile.NivelSocial.INTROVERTIDO);

                        return userProfileRepository.save(novo);
                    });

            System.out.println("Perfil OK: " + profile.getNivelSocial());

            // ==========================
            // USER INTEREST
            // ==========================
            User usuarioEntidade = userRepository.findById(user.getId())
                    .orElseThrow();

            UserInterestId userInterestId = new UserInterestId();
            userInterestId.setUserId(usuarioEntidade.getId());
            userInterestId.setInterestId(interest.getId());

            if (!userInterestRepository.existsById(userInterestId)) {
                UserInterest userInterest = new UserInterest();
                userInterest.setId(userInterestId);
                userInterest.setUser(usuarioEntidade);
                userInterest.setInterest(interest);
                userInterest.setPeso(5);

                userInterestRepository.save(userInterest);

                System.out.println("Interesse do usuário criado.");
            } else {
                System.out.println("Interesse do usuário já existente.");
            }

            // ==========================
            // RECOMMENDATION SERVICE
            // ==========================
            System.out.println("\n===== RECOMENDAÇÕES =====");

            recommendationService.recomendar(user.getId())
                    .forEach(r -> System.out.println(
                            r.getNome()
                                    + " | Score: "
                                    + r.getScore()
                                    + " | "
                                    + r.getMotivo()
                    ));

            // ==========================
            // RELATÓRIO FINAL
            // ==========================
            System.out.println("\n===== RESUMO =====");

            System.out.println("Usuários: " +
                    userRepository.count());

            System.out.println("Categorias: " +
                    categoryRepository.count());

            System.out.println("Hobbies: " +
                    hobbyRepository.count());

            System.out.println("Interesses: " +
                    interestRepository.count());

            System.out.println("Feedbacks usuário:");

            feedbackService.listarPorUsuario(user.getId())
                    .forEach(f ->
                            System.out.println(
                                    f.getHobby().getNome()
                                            + " -> Nota "
                                            + f.getRating()
                            )
                    );

            System.out.println("==============================");
            System.out.println(" TESTE FINALIZADO COM SUCESSO");
            System.out.println("==============================\n");
        };
    }
}