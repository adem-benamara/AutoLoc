package tn.esprit.autoloc.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.autoloc.domain.Client;
import tn.esprit.autoloc.repository.IClientRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements IClientService {

    private final IClientRepository clientRepository;

    @Override
    public Client addClient(Client client) {
        return clientRepository.save(client);
    }

    @Override
    public List<Client> retrieveAllClients() {
        return clientRepository.findAll();
    }

    @Override
    public Client retrieveClient(Long idClient) {
        return clientRepository.findById(idClient)
                .orElseThrow(() -> new EntityNotFoundException("Client introuvable : id=" + idClient));
    }

    @Override
    public Client updateClient(Client client) {
        if (client.getIdClient() == null || !clientRepository.existsById(client.getIdClient())) {
            throw new EntityNotFoundException("Client introuvable : id=" + client.getIdClient());
        }
        return clientRepository.save(client);
    }

    @Override
    public void removeClient(Long idClient) {
        clientRepository.deleteById(idClient);
    }
}
