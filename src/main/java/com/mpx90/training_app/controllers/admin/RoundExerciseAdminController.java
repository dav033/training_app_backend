package com.mpx90.training_app.controllers.admin;


import com.mpx90.training_app.dto.core.RoundExercise;
import com.mpx90.training_app.dto.requests.UpdateRoundExerciseListRequest;
import com.mpx90.training_app.services.admin.RoundExerciseAdminService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/round-exercise")
@AllArgsConstructor
@CrossOrigin
public class RoundExerciseAdminController {

    private final RoundExerciseAdminService roundExerciseAdminService;

    @PostMapping
    public ResponseEntity<RoundExercise> createRoundExercise(@RequestBody RoundExercise roundExercise) {
        return ResponseEntity.ok(roundExerciseAdminService.create(roundExercise));
    }


    @DeleteMapping("/{id}")
    public void deleteRoundExercise(@PathVariable Long id) {
        roundExerciseAdminService.delete(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RoundExercise> updateRoundExercise(@PathVariable Long id, @RequestBody RoundExercise roundExercise) {
        return ResponseEntity.ok(roundExerciseAdminService.update(id, roundExercise));
    }

    @PutMapping("/list")
    public void updateListRoundExercisePosition(@RequestBody UpdateRoundExerciseListRequest roundExercises) {
        roundExerciseAdminService.updateListRoundExercisePosition(roundExercises.getRoundExercises());
    }

}
