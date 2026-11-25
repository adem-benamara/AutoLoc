package tn.esprit.autoloc.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.domain.Paiement;
import tn.esprit.autoloc.service.IPaiementService;

import java.util.List;

@RestController
@RequestMapping("/api/paiements")
@RequiredArgsConstructor
public class PaiementController {

    private final IPaiementService paiementService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Paiement addPaiement(@RequestBody Paiement paiement) {
        return paiementService.addPaiement(paiement);
    }

    @GetMapping
    public List<Paiement> retrieveAllPaiements() {
        return paiementService.retrieveAllPaiements();
    }

    @GetMapping("/{idPaiement}")
    public Paiement retrievePaiement(@PathVariable("idPaiement") Long idPaiement) {
        return paiementService.retrievePaiement(idPaiement);
    }

    @PutMapping("/{idPaiement}")
    public Paiement updatePaiement(@PathVariable("idPaiement") Long idPaiement, @RequestBody Paiement paiement) {
        paiement.setIdPaiement(idPaiement);
        return paiementService.updatePaiement(paiement);
    }

    @DeleteMapping("/{idPaiement}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removePaiement(@PathVariable("idPaiement") Long idPaiement) {
        paiementService.removePaiement(idPaiement);
    }
}
