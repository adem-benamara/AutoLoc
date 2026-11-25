package tn.esprit.autoloc.service;

import tn.esprit.autoloc.domain.Employe;

import java.util.List;

public interface IEmployeService {

    Employe addEmploye(Employe employe);

    List<Employe> retrieveAllEmployes();

    Employe retrieveEmploye(Long idEmploye);

    Employe updateEmploye(Employe employe);

    void removeEmploye(Long idEmploye);
}
