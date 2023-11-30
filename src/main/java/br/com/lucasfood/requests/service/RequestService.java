package br.com.lucasfood.requests.service;

import br.com.lucasfood.requests.dto.DtoRequest;
import br.com.lucasfood.requests.dto.DtoStatus;
import br.com.lucasfood.requests.model.Request;
import br.com.lucasfood.requests.model.Status;
import br.com.lucasfood.requests.repository.RequestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RequestService {

    @Autowired
    private RequestRepository repository;

    @Autowired
    private final ModelMapper modelMapper;

    public List<DtoRequest> fetchAll() {
        return repository.findAll().stream()
                .map(request -> modelMapper.map(request, DtoRequest.class))
                .collect(Collectors.toList());
    }

    public DtoRequest fetchById(Long id) {
        Request request = repository.findById(id)
                .orElseThrow(EntityNotFoundException::new);

        return modelMapper.map(request, DtoRequest.class);
    }

    public DtoRequest createRequest(DtoRequest dto) {

        Request request = modelMapper.map(dto, Request.class);

        request.setDateTime(LocalDateTime.now());
        request.setStatus(Status.ACCOMPLISHMENT);
        request.getItems().forEach(item -> item.setRequest(request));
        Request save = repository.save(request);

        return modelMapper.map(request, DtoRequest.class);
    }

    public DtoRequest updateStatus(Long id, DtoStatus dto) {

        Request request = repository.byIdWithItems(id);

        if (request == null) {
            throw new EntityNotFoundException();
        }

        request.setStatus(dto.getStatus());
        repository.updateStatus(dto.getStatus(), request);
        return modelMapper.map(request, DtoRequest.class);
    }

    public void authPaymentRequest(Long id) {

        Request request = repository.byIdWithItems(id);

        if (request == null) {
            throw new EntityNotFoundException();
        }

        request.setStatus(Status.PAID);
        repository.updateStatus(Status.PAID, request);
    }

}
