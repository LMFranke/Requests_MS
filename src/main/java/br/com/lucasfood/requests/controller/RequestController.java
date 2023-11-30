package br.com.lucasfood.requests.controller;

import br.com.lucasfood.requests.dto.DtoRequest;
import br.com.lucasfood.requests.dto.DtoStatus;
import br.com.lucasfood.requests.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/requests")
public class RequestController {

    @Autowired
    private RequestService service;

    @GetMapping
    public List<DtoRequest> listAll() {
        return service.fetchAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DtoRequest> listById(@PathVariable @NotNull Long id) {
        DtoRequest dto = service.fetchById(id);
        return ResponseEntity.ok(dto);
    }

    @PostMapping()
    public ResponseEntity<DtoRequest> makeRequest(@RequestBody @Valid DtoRequest dto, UriComponentsBuilder uri) {

        DtoRequest request = service.createRequest(dto);
        URI address = uri.path("/request/{id}").buildAndExpand(request.getId()).toUri();

        return ResponseEntity.created(address).body(request);
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<DtoRequest> updateStatus(@PathVariable Long id, @RequestBody DtoStatus status) {
        DtoRequest dto = service.updateStatus(id, status);
        return ResponseEntity.ok(dto);
    }

    @PutMapping("/{id}/paid")
    public ResponseEntity<Void> authPayment(@PathVariable @NotNull Long id) {
        service.authPaymentRequest(id);
        return ResponseEntity.ok().build();
    }

}
