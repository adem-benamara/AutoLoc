package tn.esprit.autoloc.config;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class PerformanceAspect {

    private static final long SEUIL_MS = 1000;

    @Around("execution(* tn.esprit.autoloc.service..*.*(..))")
    public Object mesurerTempsExecution(ProceedingJoinPoint joinPoint) throws Throwable {
        long debut = System.nanoTime();
        try {
            return joinPoint.proceed();
        } finally {
            long dureeMs = (System.nanoTime() - debut) / 1_000_000;
            String methode = joinPoint.getSignature().getDeclaringType().getSimpleName()
                    + "." + joinPoint.getSignature().getName();
            log.info("[PERF] {}() exécutée en {} ms", methode, dureeMs);
            if (dureeMs > SEUIL_MS) {
                log.warn("[PERF] Méthode lente : {}() a dépassé {} ms ({} ms)", methode, SEUIL_MS, dureeMs);
            }
        }
    }
}
