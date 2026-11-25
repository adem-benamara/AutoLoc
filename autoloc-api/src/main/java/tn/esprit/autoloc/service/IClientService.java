package tn.esprit.autoloc.service;

import tn.esprit.autoloc.domain.Client;

import java.util.List;

public interface IClientService {

    Client addClient(Client client);

    List<Client> retrieveAllClients();

    Client retrieveClient(Long idClient);

    Client updateClient(Client client);

    void removeClient(Long idClient);
}
