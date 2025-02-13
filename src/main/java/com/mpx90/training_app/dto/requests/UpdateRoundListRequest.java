package com.mpx90.training_app.dto.requests;

import com.mpx90.training_app.dto.core.Round;
import lombok.*;

import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateRoundListRequest {
    List<Round> rounds;
}
