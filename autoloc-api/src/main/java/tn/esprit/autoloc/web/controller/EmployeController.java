package tn.esprit.autoloc.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.domain.Employe;
import tn.esprit.autoloc.service.IEmployeService;

import java.util.List;

@RestController
@RequestMapping("/api/employes")
@RequiredArgsConstructor
public class EmployeController {

    private final IEmployeService employeService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Employe addEmploye(@RequestBody Employe employe) {
        return employeService.addEmploye(employe);
    }

    @GetMapping
    public List<Employe> retrieveAllEmployes() {
        return employeService.retrieveAllEmployes();
    }

    @GetMapping("/{idEmploye}")
    public Employe retrieveEmploye(@PathVariable("idEmploye") Long idEmploye) {
        return employeService.retrieveEmploye(idEmploye);
    }

    @PutMapping("/{idEmploye}")
    public Employe updateEmploye(@PathVariable("idEmploye") Long idEmploye, @RequestBody Employe employe) {
        employe.setIdEmploye(idEmploye);
        return employeService.updateEmploye(employe);
    }

    @DeleteMapping("/{idEmploye}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeEmploye(@PathVariable("idEmploye") Long idEmploye) {
        employeService.removeEmploye(idEmploye);
    }
}
