package cz.frantisekfilip.ticketsandbets.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BetDto {

    private Long id;
    private String betType;
    private BigDecimal odds;
    private  MatchDto match;
}
