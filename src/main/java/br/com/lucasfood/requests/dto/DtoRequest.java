package br.com.lucasfood.requests.dto;

import br.com.lucasfood.requests.model.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DtoRequest {

    private Long id;
    private LocalDateTime dateTime;
    private Status status;
    private List<DtoRequestItem> items = new ArrayList<>();

}
