package fr.iocean.bestioles.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MethodNameLoggerAspect {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

//    @Before("within(fr.iocean.bestioles.service.PersonService)")
//    public void logService(JoinPoint joinPoint) {
//        logger.info(">>> ASPECT <<< Executing PersonService method {}", joinPoint.getSignature().getName());
//    }

    @Before("within(@org.springframework.web.bind.annotation.RestController *)")
    // autres pointcuts qui font la même chose
    // @Before("@within(org.springframework.web.bind.annotation.RestController)")
    // @Before("within(fr.iocean.bestioles.controller..*)")
    public void logService(JoinPoint joinPoint) {
        logger.info(">>> ASPECT <<< Executing Controller method {}",
                joinPoint.getSignature().getName());
    }

}
