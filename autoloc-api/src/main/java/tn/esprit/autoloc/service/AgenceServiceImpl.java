package tn.esprit.autoloc.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import tn.esprit.autoloc.domain.Agence;
import tn.esprit.autoloc.repository.IAgenceRepository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class AgenceServiceImpl implements IAgenceService {

    private static final DateTimeFormatter FORMAT_DATE = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

    private final IAgenceRepository agenceRepository;

    @Override
    public Agence addAgence(Agence agence) {
        return agenceRepository.save(agence);
    }

    @Override
    public List<Agence> retrieveAllAgences() {
        return agenceRepository.findAll();
    }

    @Override
    public Agence retrieveAgence(Long idAgence) {
        return agenceRepository.findById(idAgence)
                .orElseThrow(() -> new EntityNotFoundException("Agence introuvable : id=" + idAgence));
    }

    @Override
    public Agence updateAgence(Agence agence) {
        if (agence.getIdAgence() == null || !agenceRepository.existsById(agence.getIdAgence())) {
            throw new EntityNotFoundException("Agence introuvable : id=" + agence.getIdAgence());
        }
        return agenceRepository.save(agence);
    }

    @Override
    public void removeAgence(Long idAgence) {
        agenceRepository.deleteById(idAgence);
    }

    // Question 2 : 2 minutes (120 000 ms) après la FIN de chaque exécution
    @Scheduled(fixedDelay = 120000)
    public void suivreNombreAgences() {
        List<Agence> agences = agenceRepository.findAll();
        log.info("[Suivi agences] Nombre total d'agences enregistrées : {}", agences.size());
        log.info("[Suivi agences] Date et heure de la vérification : {}", LocalDateTime.now().format(FORMAT_DATE));
    }
}
