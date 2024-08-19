package cz.frantisekfilip.ticketsandbets.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {
    private Long id;

    private String username;
    private String password;
    private String email;
    private List<TicketDto> tickets;
}
