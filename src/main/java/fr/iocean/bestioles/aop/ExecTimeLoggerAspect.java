package fr.iocean.bestioles.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ExecTimeLoggerAspect {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Around("within(fr.iocean.bestioles.service..*)")
    public Object logExecTime(ProceedingJoinPoint pjp) {

        // avant
        logger.info(">>> ASPECT <<< Calcul du temps d'exécution de {}", pjp.getSignature());
        long start = System.currentTimeMillis();

        try {
            // On récupère la valeur de proceed pour la return
            Object proceed = pjp.proceed();

            // après
            logger.info(">>> ASPECT <<< Temps d'exécution : {} ms", (System.currentTimeMillis() - start));

            // on retourne la valeur après avoir fini d'exécuter l'advice
            return proceed;
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }
}
