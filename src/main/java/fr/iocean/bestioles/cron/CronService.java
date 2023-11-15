package fr.iocean.bestioles.cron;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CronService {

    @Value("${utilisateur.prenom}")
    private String value;

    @Scheduled(cron = "${cron.value}")
    public void testCron() {
        System.out.println(">>> CRON " + value + " (" + LocalDateTime.now() + ") <<<");
    }

}
