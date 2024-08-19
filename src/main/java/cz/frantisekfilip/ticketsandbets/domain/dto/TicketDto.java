package cz.frantisekfilip.ticketsandbets.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TicketDto {

    private Long id;

    private Long userId;

    private BigDecimal totalOdds;

    private String status;

    private List<BetDto> bets;
}
