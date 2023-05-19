package com.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import sun.misc.Signal;
import sun.misc.SignalHandler;

@SpringBootApplication
public class CourseTrackerApplication {

	public static void main(String[] args) {
		
        Signal.handle(new Signal("INT"), new SignalHandler() {
            @Override
            public void handle(Signal signal) {
                System.out.println("Signal SIGINT reçu. Arrêt du serveur...");
                // Fermer les ressources utilisées par le serveur
                // ...
                System.exit(0);
            }
        });
        
		SpringApplication.run(CourseTrackerApplication.class, args);
	}

}
