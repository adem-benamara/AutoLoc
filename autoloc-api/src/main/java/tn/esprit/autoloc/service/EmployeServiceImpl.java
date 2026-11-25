package tn.esprit.autoloc.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.autoloc.domain.Employe;
import tn.esprit.autoloc.repository.IEmployeRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeServiceImpl implements IEmployeService {

    private final IEmployeRepository employeRepository;

    @Override
    public Employe addEmploye(Employe employe) {
        return employeRepository.save(employe);
    }

    @Override
    public List<Employe> retrieveAllEmployes() {
        return employeRepository.findAll();
    }

    @Override
    public Employe retrieveEmploye(Long idEmploye) {
        return employeRepository.findById(idEmploye)
                .orElseThrow(() -> new EntityNotFoundException("Employe introuvable : id=" + idEmploye));
    }

    @Override
    public Employe updateEmploye(Employe employe) {
        if (employe.getIdEmploye() == null || !employeRepository.existsById(employe.getIdEmploye())) {
            throw new EntityNotFoundException("Employe introuvable : id=" + employe.getIdEmploye());
        }
        return employeRepository.save(employe);
    }

    @Override
    public void removeEmploye(Long idEmploye) {
        employeRepository.deleteById(idEmploye);
    }
}
