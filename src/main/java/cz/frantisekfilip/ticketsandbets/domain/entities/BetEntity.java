package cz.frantisekfilip.ticketsandbets.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.Id;

import java.math.BigDecimal;

@Entity
@Table(name = "bets")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BetEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long match_id;//MatchEntity nelze použít...rekurze

    @Transient //bude ignorován při načítání z db, přidáme ho až bede potřeba a to be Listu betů (kvůli nekonečné rekurzi)
    private MatchEntity match = null;

    private String betType;
    private BigDecimal odds;

}

