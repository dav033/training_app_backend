package com.mpx90.training_app.services.admin;

import com.mpx90.training_app.dto.RoundExerciseData;
import com.mpx90.training_app.dto.core.Exercise;
import com.mpx90.training_app.dto.core.Round;
import com.mpx90.training_app.dto.core.RoundExercise;
import com.mpx90.training_app.dto.requests.RoundExerciseDto;
import com.mpx90.training_app.mappers.RoundExerciseMapper;
import com.mpx90.training_app.models.training.RoundExerciseEntity;
import com.mpx90.training_app.repositories.RoundExerciseRepository;
import com.mpx90.training_app.services.base.BaseService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class RoundExerciseAdminService extends BaseService<RoundExercise, Long, RoundExerciseEntity, RoundExerciseRepository> {

    private final ExerciseAdminService exerciseAdminService;

    public RoundExerciseAdminService(RoundExerciseRepository repository, @Qualifier("roundExerciseMapperImpl") RoundExerciseMapper mapper, ExerciseAdminService exerciseAdminService) {
        super(repository, mapper);
        this.exerciseAdminService = exerciseAdminService;
    }

//    public void createRoundExerciseByRequestList(List<RoundExerciseDto> roundExerciseDtos, Long roundId) {
//
//        roundExerciseDtos.forEach(exerciseDto -> {
//            RoundExercise roundExercise = RoundExercise.builder().roundId(roundId).exerciseId(exerciseDto.getExerciseId()).repetitions(Integer.valueOf(exerciseDto.getRepetitions())).exercisePosition(exerciseDto.getExercisePosition()).build();
//
//            create(roundExercise);
//        });
//    }


    public List<RoundExercise> findAllByRoundId(Long roundId) {
        return repository.findAllByRoundId(roundId).stream().map(mapper::toDto).toList();
    }

    public List<RoundExercise> findAllByRoundIds(List<Long> roundIds) {
        return repository.findAllByRoundIdIn(roundIds).stream().map(mapper::toDto).toList();
    }


    public Map<Long, List<RoundExerciseData>> getRoundExerciseData(List<Round> rounds) {
        Set<Long> roundIds = extractRoundIds(rounds);

        List<RoundExercise> roundExercises = findAllByRoundIds(new ArrayList<>(roundIds));

        Set<Long> exerciseIds = extractExerciseIds(roundExercises);

        Map<Long, Exercise> exerciseMap = buildExerciseMap(exerciseIds);

        return groupRoundExercisesByRoundId(roundExercises, exerciseMap);
    }

    public void updateListRoundExercisePosition(List<RoundExercise> roundExercises) {
        roundExercises.forEach(roundExercise -> {
            update(roundExercise.getId(), roundExercise);
        });
    }


    private Set<Long> extractRoundIds(List<Round> rounds) {
        return rounds.stream().map(Round::getId).collect(Collectors.toSet());
    }

    private Set<Long> extractExerciseIds(List<RoundExercise> roundExercises) {
        return roundExercises.stream().map(RoundExercise::getExerciseId).collect(Collectors.toSet());
    }

    private Map<Long, Exercise> buildExerciseMap(Set<Long> exerciseIds) {
        List<Exercise> exercises = exerciseAdminService.findAllByIds(new ArrayList<>(exerciseIds));
        return exercises.stream().collect(Collectors.toMap(Exercise::getId, Function.identity()));
    }

    private Map<Long, List<RoundExerciseData>> groupRoundExercisesByRoundId(List<RoundExercise> roundExercises, Map<Long, Exercise> exerciseMap) {
        return roundExercises.stream().collect(Collectors.groupingBy(RoundExercise::getRoundId, Collectors.mapping(roundExercise -> RoundExerciseData.builder().roundExercise(roundExercise).exercise(exerciseMap.get(roundExercise.getExerciseId())).build(), Collectors.toList())));
    }

}

