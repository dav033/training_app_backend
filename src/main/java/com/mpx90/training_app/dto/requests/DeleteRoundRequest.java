package com.mpx90.training_app.dto.requests;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DeleteRoundRequest {
    private Long routineId;
}
