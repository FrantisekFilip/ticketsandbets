package cz.frantisekfilip.ticketsandbets.domain.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.Id;

import java.util.List;
import java.time.LocalDateTime;

@Entity
@Table(name = "matches")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MatchEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String sport;
    private String team1;
    private String team2;
    private LocalDateTime matchDate;

    @OneToMany(mappedBy = "match_id", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BetEntity> bets;

    public MatchEntity(Long id, String sport, String team1, String team2, LocalDateTime matchDate) {
        this.id = id;
        this.sport = sport;
        this.team1 = team1;
        this.team2 = team2;
        this.matchDate = matchDate;
    }
}

