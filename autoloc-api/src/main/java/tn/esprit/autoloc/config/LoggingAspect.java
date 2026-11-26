package tn.esprit.autoloc.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Aspect
@Component
@RequiredArgsConstructor
public class LoggingAspect {

    private final ObjectMapper objectMapper;

    @Pointcut("execution(* tn.esprit.autoloc.service..*.*(..))")
    public void servicePointcut() {
    }

    // Avant chaque méthode de la couche service : nom de la méthode + valeurs des paramètres
    @Before("servicePointcut()")
    public void logAvant(JoinPoint joinPoint) {
        log.info("[LOG] Appel de {}() avec les paramètres : {}", nomMethode(joinPoint), formaterParametres(joinPoint));
    }

    // Après chaque méthode de la couche service : nom de la méthode terminée
    @After("servicePointcut()")
    public void logApres(JoinPoint joinPoint) {
        log.info("[LOG] Fin de l'exécution de {}()", nomMethode(joinPoint));
    }

    private String nomMethode(JoinPoint joinPoint) {
        return joinPoint.getSignature().getDeclaringType().getSimpleName() + "." + joinPoint.getSignature().getName();
    }

    private String formaterParametres(JoinPoint joinPoint) {
        Object[] valeurs = joinPoint.getArgs();
        if (valeurs.length == 0) {
            return "aucun";
        }
        String[] noms = ((MethodSignature) joinPoint.getSignature()).getParameterNames();
        List<String> resultat = new ArrayList<>();
        for (int i = 0; i < valeurs.length; i++) {
            String nom = (noms != null && i < noms.length) ? noms[i] : "arg" + i;
            resultat.add(nom + " = " + enTexte(valeurs[i]));
        }
        return String.join(", ", resultat);
    }

    // Les entités n'ont pas de toString() (associations bidirectionnelles) : on les affiche en JSON
    private String enTexte(Object valeur) {
        try {
            return objectMapper.writeValueAsString(valeur);
        } catch (JsonProcessingException | RuntimeException e) {
            return String.valueOf(valeur);
        }
    }
}
