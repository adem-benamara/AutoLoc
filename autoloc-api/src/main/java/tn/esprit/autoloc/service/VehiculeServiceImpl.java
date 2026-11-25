package tn.esprit.autoloc.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import tn.esprit.autoloc.domain.Vehicule;
import tn.esprit.autoloc.repository.IVehiculeRepository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class VehiculeServiceImpl implements IVehiculeService {

    private static final DateTimeFormatter FORMAT_DATE = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

    private final IVehiculeRepository vehiculeRepository;

    @Override
    public Vehicule addVehicule(Vehicule vehicule) {
        return vehiculeRepository.save(vehicule);
    }

    @Override
    public List<Vehicule> retrieveAllVehicules() {
        return vehiculeRepository.findAll();
    }

    @Override
    public Vehicule retrieveVehicule(Long idVehicule) {
        return vehiculeRepository.findById(idVehicule)
                .orElseThrow(() -> new EntityNotFoundException("Vehicule introuvable : id=" + idVehicule));
    }

    @Override
    public Vehicule updateVehicule(Vehicule vehicule) {
        if (vehicule.getIdVehicule() == null || !vehiculeRepository.existsById(vehicule.getIdVehicule())) {
            throw new EntityNotFoundException("Vehicule introuvable : id=" + vehicule.getIdVehicule());
        }
        return vehiculeRepository.save(vehicule);
    }

    @Override
    public void removeVehicule(Long idVehicule) {
        vehiculeRepository.deleteById(idVehicule);
    }

    // Question 1 : démarre 5 s après le lancement de l'application, puis toutes les 60 s
    @Scheduled(initialDelay = 5000, fixedRate = 60000)
    public void actualiserEtatVehicules() {
        List<Vehicule> vehicules = vehiculeRepository.findAll();
        log.info("[Actualisation véhicules] Nombre total de véhicules enregistrés : {}", vehicules.size());
        log.info("[Actualisation véhicules] Date et heure de l'actualisation : {}", LocalDateTime.now().format(FORMAT_DATE));
    }

    // Question 3 : tous les lundis à 09h00 (secondes minutes heures jour-du-mois mois jour-de-la-semaine)
    //@Scheduled(cron = "0 0 9 * * MON")
    @Scheduled(cron = "0 * * * * *")
    public void genererRapportVehicules() {
        List<Vehicule> vehicules = vehiculeRepository.findAll();
        log.info("========== RAPPORT HEBDOMADAIRE DES VÉHICULES ==========");
        log.info("Nombre total de véhicules : {}", vehicules.size());
        log.info("Rapport généré le : {}", LocalDateTime.now().format(FORMAT_DATE));
        log.info("=========================================================");
    }
}
