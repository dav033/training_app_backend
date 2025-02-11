package com.mpx90.training_app.services.admin;

import com.mpx90.training_app.dto.RoundData;
import com.mpx90.training_app.dto.RoundExerciseData;
import com.mpx90.training_app.dto.core.Round;
import com.mpx90.training_app.dto.core.Routine;
import com.mpx90.training_app.dto.responses.RoutineAllDataResponse;
import com.mpx90.training_app.mappers.RoutineMapper;
import com.mpx90.training_app.models.training.RoutineEntity;
import com.mpx90.training_app.repositories.RoutineRepository;
import com.mpx90.training_app.services.base.BaseService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class RoutineAdminService extends BaseService<Routine, Long, RoutineEntity, RoutineRepository> {


    private final RoundAdminService roundAdminService;
    private final RoundExerciseAdminService roundExerciseAdminService;
    private final ExerciseAdminService exerciseAdminService;

    public RoutineAdminService(RoutineRepository repository, @Qualifier("routineMapperImpl") RoutineMapper mapper, RoundAdminService roundAdminService, RoundExerciseAdminService roundExerciseAdminService, ExerciseAdminService exerciseAdminService) {
        super(repository, mapper);
        this.roundAdminService = roundAdminService;
        this.roundExerciseAdminService = roundExerciseAdminService;
        this.exerciseAdminService = exerciseAdminService;
    }


    public RoutineAllDataResponse getAllRoutineData(Long id) {
        Routine routine = findById(id);
        List<Round> rounds = roundAdminService.findAllByRoutineId(id);
        Map<Long, List<RoundExerciseData>> roundExerciseDataMap = roundExerciseAdminService.getRoundExerciseData(rounds);
        List<RoundData> roundData = buildRoundData(rounds, roundExerciseDataMap);

        return RoutineAllDataResponse.builder().routine(routine).roundData(roundData).build();
    }


    private List<RoundData> buildRoundData(List<Round> rounds, Map<Long, List<RoundExerciseData>> roundExerciseDataMap) {
        return rounds.stream().map(round -> RoundData.builder().round(round).roundExerciseData(roundExerciseDataMap.getOrDefault(round.getId(), Collections.emptyList())).build()).collect(Collectors.toList());
    }
}
