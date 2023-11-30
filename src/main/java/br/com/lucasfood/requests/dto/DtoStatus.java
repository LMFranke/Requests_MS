package br.com.lucasfood.requests.dto;

import br.com.lucasfood.requests.model.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DtoStatus {
    private Status status;
}
