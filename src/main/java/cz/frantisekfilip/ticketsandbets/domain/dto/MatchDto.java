package cz.frantisekfilip.ticketsandbets.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MatchDto {
    private Long id;

    private String sport;
    private String team1;
    private String team2;
    private LocalDateTime matchDate;
    private List<BetDto> bets;
}
