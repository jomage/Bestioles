package fr.iocean.bestioles.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogOnGetAspect {

//    @Before("execution(public * get*(..))")
//    @Before("execution(* get*(..))")
    @Before("execution(* fr.iocean.bestioles..get*(..))")
    public void logGetters(JoinPoint joinPoint) {
        System.out.println("Coucou je suis un get " + joinPoint.getSignature());
    }
}
