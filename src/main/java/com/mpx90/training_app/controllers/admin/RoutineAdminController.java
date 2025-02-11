package com.mpx90.training_app.controllers.admin;

import com.mpx90.training_app.dto.core.Routine;
import com.mpx90.training_app.dto.responses.RoutineAllDataResponse;
import com.mpx90.training_app.services.admin.RoutineAdminService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/routine")
@AllArgsConstructor
@CrossOrigin
public class RoutineAdminController {

    private final RoutineAdminService routineService;

    @PostMapping
    public ResponseEntity<Routine> createRoutine(@RequestBody Routine routine) {
        return ResponseEntity.ok(routineService.create(routine));
    }

    @GetMapping
    public ResponseEntity<List<Routine>> getAllRoutines() {
        return ResponseEntity.ok(routineService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoutineAllDataResponse> getAllRoutineData(@PathVariable Long id) {
        return ResponseEntity.ok(routineService.getAllRoutineData(id));
    }


}
