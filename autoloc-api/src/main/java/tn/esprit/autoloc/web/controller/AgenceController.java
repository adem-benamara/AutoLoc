package tn.esprit.autoloc.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.domain.Agence;
import tn.esprit.autoloc.service.IAgenceService;

import java.util.List;

@RestController
@RequestMapping("/api/agences")
@RequiredArgsConstructor
public class AgenceController {

    private final IAgenceService agenceService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Agence addAgence(@RequestBody Agence agence) {
        return agenceService.addAgence(agence);
    }

    @GetMapping
    public List<Agence> retrieveAllAgences() {
        return agenceService.retrieveAllAgences();
    }

    @GetMapping("/{idAgence}")
    public Agence retrieveAgence(@PathVariable("idAgence") Long idAgence) {
        return agenceService.retrieveAgence(idAgence);
    }

    @PutMapping("/{idAgence}")
    public Agence updateAgence(@PathVariable("idAgence") Long idAgence, @RequestBody Agence agence) {
        agence.setIdAgence(idAgence);
        return agenceService.updateAgence(agence);
    }

    @DeleteMapping("/{idAgence}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeAgence(@PathVariable("idAgence") Long idAgence) {
        agenceService.removeAgence(idAgence);
    }
}
