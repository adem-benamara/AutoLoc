package tn.esprit.autoloc.config;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class ExceptionHandlingAspect {

    @AfterThrowing(pointcut = "execution(* tn.esprit.autoloc.service..*.*(..))", throwing = "exception")
    public void logException(JoinPoint joinPoint, Throwable exception) {
        String methode = joinPoint.getSignature().getDeclaringType().getSimpleName()
                + "." + joinPoint.getSignature().getName();
        log.error("[EXCEPTION] Méthode : {}() | Type : {} | Message : {}",
                methode, exception.getClass().getName(), exception.getMessage());
    }
}
