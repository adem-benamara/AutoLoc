package tn.esprit.autoloc.service;

import tn.esprit.autoloc.domain.Agence;

import java.util.List;

public interface IAgenceService {

    Agence addAgence(Agence agence);

    List<Agence> retrieveAllAgences();

    Agence retrieveAgence(Long idAgence);

    Agence updateAgence(Agence agence);

    void removeAgence(Long idAgence);
}
