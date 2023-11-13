package fr.iocean.bestioles.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ExceptionLoggerAspect {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @AfterThrowing(
        value = "execution(* fr.iocean.bestioles.repository.*.save(..))",
        throwing = "ex"
    )
    public void logException(JoinPoint joinPoint, Exception ex) {
        logger.info(">>> ASPECT <<< Exception lors de la méthode {}", joinPoint.getSignature());
        logger.info(">>> ASPECT <<< {}", ex.getMessage());
    }
}
