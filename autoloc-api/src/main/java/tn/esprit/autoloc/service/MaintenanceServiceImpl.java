package tn.esprit.autoloc.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.autoloc.domain.Maintenance;
import tn.esprit.autoloc.repository.IMaintenanceRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MaintenanceServiceImpl implements IMaintenanceService {

    private final IMaintenanceRepository maintenanceRepository;

    @Override
    public Maintenance addMaintenance(Maintenance maintenance) {
        return maintenanceRepository.save(maintenance);
    }

    @Override
    public List<Maintenance> retrieveAllMaintenances() {
        return maintenanceRepository.findAll();
    }

    @Override
    public Maintenance retrieveMaintenance(Long idMaintenance) {
        return maintenanceRepository.findById(idMaintenance)
                .orElseThrow(() -> new EntityNotFoundException("Maintenance introuvable : id=" + idMaintenance));
    }

    @Override
    public Maintenance updateMaintenance(Maintenance maintenance) {
        if (maintenance.getIdMaintenance() == null || !maintenanceRepository.existsById(maintenance.getIdMaintenance())) {
            throw new EntityNotFoundException("Maintenance introuvable : id=" + maintenance.getIdMaintenance());
        }
        return maintenanceRepository.save(maintenance);
    }

    @Override
    public void removeMaintenance(Long idMaintenance) {
        maintenanceRepository.deleteById(idMaintenance);
    }
}
