package dev.jamal.projetotcc.Service;

import dev.jamal.projetotcc.DTO.Recommendation.HobbyRecommendationDTO;
import dev.jamal.projetotcc.Entities.Hobby;
import dev.jamal.projetotcc.Entities.UserHobbyFeedback;
import dev.jamal.projetotcc.Entities.UserInterest;
import dev.jamal.projetotcc.Entities.UserProfile;
import dev.jamal.projetotcc.Mapper.RecommendationMapper;
import dev.jamal.projetotcc.Repository.HobbyRepository;
import dev.jamal.projetotcc.Repository.UserHobbyFeedbackRepository;
import dev.jamal.projetotcc.Repository.UserInterestRepository;
import dev.jamal.projetotcc.Repository.UserProfileRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class RecommendationService {

    private final HobbyRepository hobbyRepository;
    private final UserProfileRepository userProfileRepository;
    private final UserInterestRepository userInterestRepository;
    private final UserHobbyFeedbackRepository feedbackRepository;
    private final RecommendationMapper recommendationMapper;

    public List<HobbyRecommendationDTO> recomendar (Long userId){

        UserProfile profile = userProfileRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Perfil do usuário não encontrado"));

        List<UserInterest> interesses = userInterestRepository.findByUserIdWithInterest(userId);

        List<UserHobbyFeedback> feedbacks = feedbackRepository.buscarComHobbyEUsuario(userId);

        List<Hobby> hobbies = hobbyRepository.findAllWithCategory();

        return hobbies.stream()
                .map(hobby -> {
                    double score = calcularScore(hobby, profile, interesses, feedbacks);
                    String motivo = gerarMotivo(hobby, profile, interesses, score);

                    return recommendationMapper.toDTO(hobby, score, motivo);
                })
                .sorted(Comparator.comparing(HobbyRecommendationDTO::getScore).reversed())
                .limit(5)
                .toList();
    }

    private double calcularScore(
            Hobby hobby,
            UserProfile profile,
            List<UserInterest> interesses,
            List<UserHobbyFeedback> feedbacks
    ) {
        double score = 0.0;

        score += calcularScoreInteresse(hobby, interesses);
        score += calcularScoreTempo(hobby, profile);
        score += calcularScoreOrcamento(hobby, profile);
        score += calcularScoreSocial(hobby, profile);
        score += calcularScoreFeedback(hobby, feedbacks);

        return score;
    }

    private double calcularScoreInteresse(Hobby hobby, List<UserInterest> interesses) {
        return interesses.stream()
                .filter(ui -> ui.getInterest().getNome()
                        .equalsIgnoreCase(hobby.getCategory().getNome()))
                .mapToDouble(ui -> ui.getPeso() * 8.0)
                .sum();
    }

    private double calcularScoreTempo(Hobby hobby, UserProfile profile) {
        if (hobby.getTempoNecessario() == null || profile.getTempoDisponivel() == null) {
            return 0.0;
        }

        if (hobby.getTempoNecessario() <= profile.getTempoDisponivel()) {
            return 20.0;
        }

        return 5.0;
    }

    private double calcularScoreOrcamento(Hobby hobby, UserProfile profile) {
        if (hobby.getCustoEstimado() == null || profile.getOrcamento() == null) {
            return 0.0;
        }

        if (hobby.getCustoEstimado() <= profile.getOrcamento()) {
            return 20.0;
        }

        return 0.0;
    }

    private double calcularScoreSocial(Hobby hobby, UserProfile profile) {
        if (hobby.getTipoSocializacao() == null || profile.getNivelSocial() == null) {
            return 0.0;
        }

        boolean usuarioIntrovertido = profile.getNivelSocial() == UserProfile.NivelSocial.INTROVERTIDO;

        boolean hobbyIndividual = hobby.getTipoSocializacao() == Hobby.TipoSocial.INDIVIDUAL;

        boolean usuarioExtrovertido = profile.getNivelSocial() == UserProfile.NivelSocial.EXTROVERTIDO;

        boolean hobbySocial = hobby.getTipoSocializacao() == Hobby.TipoSocial.SOCIAL;

        if ((usuarioIntrovertido && hobbyIndividual) || (usuarioExtrovertido && hobbySocial)) {
            return 15.0;
        }

        return 5.0;
    }

    private double calcularScoreFeedback(Hobby hobby, List<UserHobbyFeedback> feedbacks) {
        return feedbacks.stream()
                .filter(f -> f.getHobby().getCategory().getNome()
                        .equalsIgnoreCase(hobby.getCategory().getNome()))
                .mapToDouble(f -> {
                    if (f.getRating() >= 4) return 10.0;
                    if (f.getRating() == 3) return 3.0;
                    return -10.0;
                })
                .sum();
    }

    private String gerarMotivo(
            Hobby hobby,
            UserProfile profile,
            List<UserInterest> interesses,
            double score
    ) {
        List<String> motivos = new ArrayList<>();

        boolean possuiInteresse = interesses.stream()
                .anyMatch(ui -> ui.getInterest().getNome()
                        .equalsIgnoreCase(hobby.getCategory().getNome()));

        if (possuiInteresse) {
            motivos.add("combina com seus interesses em " + hobby.getCategory().getNome());
        }

        if (hobby.getCustoEstimado() <= profile.getOrcamento()) {
            motivos.add("está dentro do seu orçamento");
        }

        if (hobby.getTempoNecessario() <= profile.getTempoDisponivel()) {
            motivos.add("é compatível com seu tempo disponível");
        }

        if (hobby.getTipoSocializacao() == Hobby.TipoSocial.INDIVIDUAL && profile.getNivelSocial() == UserProfile.NivelSocial.INTROVERTIDO) {
            motivos.add("Combina com atividades mais individuais");
        }

        if (hobby.getTipoSocializacao() == Hobby.TipoSocial.SOCIAL && profile.getNivelSocial() == UserProfile.NivelSocial.EXTROVERTIDO) {
            motivos.add("combina com atividades sociais");
        }

        if (motivos.isEmpty()) {
            return "Recomendado com base na compatibilidade geral do seu perfil.";
        }

        return "Recomendado porque " + String.join(", ", motivos) + ".";
    }
}
