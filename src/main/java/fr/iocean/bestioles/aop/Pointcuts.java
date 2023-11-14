package fr.iocean.bestioles.aop;


import org.aspectj.lang.annotation.Pointcut;

public class Pointcuts {

    @Pointcut("execution(public * *(..))")
    public void publicMethod() {}

    @Pointcut("within(fr.iocean.bestioles.service..*)")
    public void inServices() {}

    @Pointcut("publicMethod() && inServices()")
    public void tradingOperation() {}
}
