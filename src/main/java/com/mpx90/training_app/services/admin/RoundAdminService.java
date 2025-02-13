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
import org.springframework.web.bind.annotation.PutMapping;

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

    public List<Round> deleteRoundByIdAndUpdateRoundsPosition(Long routineId, long RoundId) {

        List<Round> rounds = findAllByRoutineId(routineId);

        Number deletedRoundPosition = rounds.stream().filter(round -> round.getId().equals(RoundId)).findFirst().get().getRoundPosition();

        delete(RoundId);

        rounds.stream().filter(round -> round.getRoundPosition().intValue() > deletedRoundPosition.intValue()).forEach(round -> {
            round.setRoundPosition(round.getRoundPosition() - 1);
            update(round.getId(), round);
        });

        return findAllByRoutineId(routineId);

    }

    public void updateListRoundPosition(List<Round> rounds) {
        rounds.forEach(round -> update(round.getId(), round));
    }
}
