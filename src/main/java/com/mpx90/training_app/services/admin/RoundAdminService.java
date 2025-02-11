package com.mpx90.training_app.services.admin;

import com.mpx90.training_app.dto.core.Round;
import com.mpx90.training_app.dto.requests.RoundDto;
import com.mpx90.training_app.dto.requests.RoundExerciseDto;
import com.mpx90.training_app.dto.requests.RoundRequest;
import com.mpx90.training_app.mappers.RoundMapper;
import com.mpx90.training_app.models.training.RoundEntity;
import com.mpx90.training_app.repositories.RoundRepository;
import com.mpx90.training_app.services.base.BaseService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class RoundAdminService extends BaseService<Round, Long, RoundEntity, RoundRepository> {

    private final RoundExerciseAdminService roundExerciseAdminService;

    public RoundAdminService(RoundRepository repository, RoundMapper roundMapper, RoundExerciseAdminService roundExerciseAdminService) {
        super(repository, roundMapper);
        this.roundExerciseAdminService = roundExerciseAdminService;
    }

    @Transactional
    public List<Round> createRoundsWithExercises(RoundRequest roundRequest) {
        return createRoundsByList(roundRequest.getRoutineId(), roundRequest.getRounds());
    }

    public List<Round> findAllByRoutineId(Long routineId) {
        return repository.findByRoutineIdIn(Collections.singleton(routineId)).stream().map(mapper::toDto).toList(); // Replaces collect(Collectors.toList()) with toList()
    }


    private List<Round> createRoundsByList(Long routineId, List<RoundDto> rounds) {
        return rounds.stream().map(roundDto -> {
            Round round = Round.builder().routineId(routineId).rest(roundDto.getRest()).roundPosition(roundDto.getRoundPosition()).build();

            Round savedRound = create(round);

            List<RoundExerciseDto> roundExerciseDtos = roundDto.getExercises();

            roundExerciseAdminService.createRoundExerciseByRequestList(roundExerciseDtos, savedRound.getId());


            return savedRound;

        }).toList();
    }
}
