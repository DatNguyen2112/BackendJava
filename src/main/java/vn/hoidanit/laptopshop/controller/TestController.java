package vn.hoidanit.laptopshop.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import org.springframework.boot.autoconfigure.integration.IntegrationProperties.RSocket.Client;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import vn.hoidanit.laptopshop.domain.Test;
import vn.hoidanit.laptopshop.domain.ResponseStatus.ResponseStatus;
import vn.hoidanit.laptopshop.repository.TestRepository;

@RestController
@RequestMapping("/clients")
public class TestController {
    private TestRepository testRepository;

    public TestController(TestRepository testRepository) {
        this.testRepository = testRepository;
    }

    @GetMapping
    public List<Test> getCLients() {
        List<Test> lstClients = this.testRepository.findAll();
        return lstClients;
    }

    @GetMapping("/{id}")
    public Test getClientById(@PathVariable Long id) {
        return this.testRepository.findById(id).orElseThrow(RuntimeException::new);
    }

    @PostMapping
    public ResponseEntity<Test> createClient(@RequestBody Test newClient)
            throws URISyntaxException {
        Test client = this.testRepository.save(newClient);
        return ResponseEntity.created(new URI("/clients" + client.getId())).body(client);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Test> updateClient(@PathVariable Long id, @RequestBody Test newClient) {
        Test currentClient = this.testRepository.findById(id).orElseThrow(RuntimeException::new);
        if (currentClient != null) {
            currentClient.setEmail(newClient.getEmail());
            currentClient.setName(newClient.getName());
            this.testRepository.save(currentClient);
        }
        return ResponseEntity.ok(currentClient);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Test> deleteClient(@PathVariable Long id) {
        this.testRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
