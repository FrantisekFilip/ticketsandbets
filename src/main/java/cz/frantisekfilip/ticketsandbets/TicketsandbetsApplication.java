package cz.frantisekfilip.ticketsandbets;

import lombok.extern.java.Log;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Log
public class TicketsandbetsApplication {

	public static void main(String[] args) {
		SpringApplication.run(TicketsandbetsApplication.class, args);
		log.info(("Tickets and bets app started"));
	}

}
