package cz.frantisekfilip.ticketsandbets.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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

    @JsonIgnoreProperties({"bets"}) //aktuálně zbytečné, ošetřeno již v BetEntity
    private  MatchDto match;
}
