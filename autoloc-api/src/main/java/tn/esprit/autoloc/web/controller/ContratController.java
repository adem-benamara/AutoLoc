package tn.esprit.autoloc.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.domain.Contrat;
import tn.esprit.autoloc.service.IContratService;

import java.util.List;

@RestController
@RequestMapping("/api/contrats")
@RequiredArgsConstructor
public class ContratController {

    private final IContratService contratService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Contrat addContrat(@RequestBody Contrat contrat) {
        return contratService.addContrat(contrat);
    }

    @GetMapping
    public List<Contrat> retrieveAllContrats() {
        return contratService.retrieveAllContrats();
    }

    @GetMapping("/{idContrat}")
    public Contrat retrieveContrat(@PathVariable("idContrat") Long idContrat) {
        return contratService.retrieveContrat(idContrat);
    }

    @PutMapping("/{idContrat}")
    public Contrat updateContrat(@PathVariable("idContrat") Long idContrat, @RequestBody Contrat contrat) {
        contrat.setIdContrat(idContrat);
        return contratService.updateContrat(contrat);
    }

    @DeleteMapping("/{idContrat}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeContrat(@PathVariable("idContrat") Long idContrat) {
        contratService.removeContrat(idContrat);
    }
}
