package com.mpx90.training_app.controllers.admin;


import com.mpx90.training_app.dto.core.Round;
import com.mpx90.training_app.dto.requests.DeleteRoundRequest;
import com.mpx90.training_app.dto.requests.RoundRequest;
import com.mpx90.training_app.dto.requests.UpdateRoundListRequest;
import com.mpx90.training_app.services.admin.RoundAdminService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/round")
@AllArgsConstructor
@CrossOrigin
public class RoundAdminController {


    private final RoundAdminService roundService;

    @PostMapping
    public ResponseEntity<Round> createRound(@RequestBody Round round) {
        return ResponseEntity.ok(roundService.create(round));
    }

    @GetMapping
    public ResponseEntity<List<Round>> getAllRounds() {
        return ResponseEntity.ok(roundService.findAll());
    }


    @PostMapping("/batch")
    public ResponseEntity<String> createRounds(@RequestBody RoundRequest roundRequests) {
        roundService.createRoundsWithExercises(roundRequests);
        return ResponseEntity.ok("Rondas y ejercicios creados exitosamente.");
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<List<Round>> deleteRound(@PathVariable Long id, @RequestBody DeleteRoundRequest deleteRoundRequest) {
        try {
            return ResponseEntity.ok(roundService.deleteRoundByIdAndUpdateRoundsPosition(deleteRoundRequest.getRoutineId(), id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/list")
    public void updateListRoundPosition(@RequestBody UpdateRoundListRequest rounds) {
        roundService.updateListRoundPosition(rounds.getRounds());
    }


}
