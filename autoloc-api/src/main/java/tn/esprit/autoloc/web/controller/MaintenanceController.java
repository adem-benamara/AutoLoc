package tn.esprit.autoloc.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.domain.Maintenance;
import tn.esprit.autoloc.service.IMaintenanceService;

import java.util.List;

@RestController
@RequestMapping("/api/maintenances")
@RequiredArgsConstructor
public class MaintenanceController {

    private final IMaintenanceService maintenanceService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Maintenance addMaintenance(@RequestBody Maintenance maintenance) {
        return maintenanceService.addMaintenance(maintenance);
    }

    @GetMapping
    public List<Maintenance> retrieveAllMaintenances() {
        return maintenanceService.retrieveAllMaintenances();
    }

    @GetMapping("/{idMaintenance}")
    public Maintenance retrieveMaintenance(@PathVariable("idMaintenance") Long idMaintenance) {
        return maintenanceService.retrieveMaintenance(idMaintenance);
    }

    @PutMapping("/{idMaintenance}")
    public Maintenance updateMaintenance(@PathVariable("idMaintenance") Long idMaintenance, @RequestBody Maintenance maintenance) {
        maintenance.setIdMaintenance(idMaintenance);
        return maintenanceService.updateMaintenance(maintenance);
    }

    @DeleteMapping("/{idMaintenance}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeMaintenance(@PathVariable("idMaintenance") Long idMaintenance) {
        maintenanceService.removeMaintenance(idMaintenance);
    }
}
