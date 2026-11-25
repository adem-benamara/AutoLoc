package tn.esprit.autoloc.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.domain.Client;
import tn.esprit.autoloc.service.IClientService;

import java.util.List;

@RestController
@RequestMapping("/api/clients")
@RequiredArgsConstructor
public class ClientController {

    private final IClientService clientService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Client addClient(@RequestBody Client client) {
        return clientService.addClient(client);
    }

    @GetMapping
    public List<Client> retrieveAllClients() {
        return clientService.retrieveAllClients();
    }

    @GetMapping("/{idClient}")
    public Client retrieveClient(@PathVariable("idClient") Long idClient) {
        return clientService.retrieveClient(idClient);
    }

    @PutMapping("/{idClient}")
    public Client updateClient(@PathVariable("idClient") Long idClient, @RequestBody Client client) {
        client.setIdClient(idClient);
        return clientService.updateClient(client);
    }

    @DeleteMapping("/{idClient}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeClient(@PathVariable("idClient") Long idClient) {
        clientService.removeClient(idClient);
    }
}
