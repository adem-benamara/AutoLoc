package tn.esprit.autoloc.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.domain.Equipement;
import tn.esprit.autoloc.service.IEquipementService;

import java.util.List;

@RestController
@RequestMapping("/api/equipements")
@RequiredArgsConstructor
public class EquipementController {

    private final IEquipementService equipementService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Equipement addEquipement(@RequestBody Equipement equipement) {
        return equipementService.addEquipement(equipement);
    }

    @GetMapping
    public List<Equipement> retrieveAllEquipements() {
        return equipementService.retrieveAllEquipements();
    }

    @GetMapping("/{idEquipement}")
    public Equipement retrieveEquipement(@PathVariable("idEquipement") Long idEquipement) {
        return equipementService.retrieveEquipement(idEquipement);
    }

    @PutMapping("/{idEquipement}")
    public Equipement updateEquipement(@PathVariable("idEquipement") Long idEquipement, @RequestBody Equipement equipement) {
        equipement.setIdEquipement(idEquipement);
        return equipementService.updateEquipement(equipement);
    }

    @DeleteMapping("/{idEquipement}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeEquipement(@PathVariable("idEquipement") Long idEquipement) {
        equipementService.removeEquipement(idEquipement);
    }
}
