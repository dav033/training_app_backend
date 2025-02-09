package com.mpx90.training_app.controllers.admin;

import com.mpx90.training_app.dto.core.Exercise;
import com.mpx90.training_app.services.crud.ExerciseService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/exercise")
@AllArgsConstructor
@CrossOrigin
public class ExerciseAdminController {

    private ExerciseService exerciseService;

    @PostMapping
    public ResponseEntity<Exercise> createExercise(@RequestBody Exercise exercise) {
        Exercise createdExercise = exerciseService.create(exercise);
        return ResponseEntity.ok(createdExercise);
    }

    @GetMapping
    public ResponseEntity<List<Exercise>> getAllExercises() {
        List<Exercise> exercises = exerciseService.findAll();
        return ResponseEntity.ok(exercises);
    }
}