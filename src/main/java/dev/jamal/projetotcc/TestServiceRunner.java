package dev.jamal.projetotcc;

import dev.jamal.projetotcc.Entities.*;
import dev.jamal.projetotcc.Repository.HobbyCategoryRepository;
import dev.jamal.projetotcc.Repository.HobbyRepository;
import dev.jamal.projetotcc.Repository.InterestRepository;
import dev.jamal.projetotcc.Repository.UserRepository;
import dev.jamal.projetotcc.Service.FeedbackService;
import dev.jamal.projetotcc.Service.HobbyService;
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
            User user = userRepository
                    .findByEmail("joao@email.com")
                    .orElseGet(() -> {

                        User novo = new User();
                        novo.setNome("João");
                        novo.setEmail("joao@email.com");
                        novo.setSenha("123");

                        return userService.cadastrar(novo);
                    });

            System.out.println("Usuário OK: " + user.getNome());

            // ==========================
            // HOBBY
            // ==========================
            Hobby hobby = hobbyRepository
                    .findAll()
                    .stream()
                    .filter(h -> h.getNome().equalsIgnoreCase("Corrida"))
                    .findFirst()
                    .orElseGet(() -> {

                        Hobby novo = new Hobby();
                        novo.setNome("Corrida");
                        novo.setDescricao("Atividade física ao ar livre");
                        novo.setCustoEstimado(0.0);
                        novo.setNivelDificuldade(2);
                        novo.setTempoNecessario(1.0);
                        novo.setTipoSocializacao(Hobby.TipoSocial.INDIVIDUAL);
                        novo.setCategory(esporte);

                        return hobbyService.salvar(novo);
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

                UserHobbyFeedback feedback =
                        new UserHobbyFeedback();

                feedback.setUser(user);
                feedback.setHobby(hobby);
                feedback.setRating(5);

                feedbackService.salvar(feedback);

                System.out.println("Feedback criado.");
            } else {
                System.out.println("Feedback já existente.");
            }

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