package br.com.lucasfood.requests.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DtoRequestItem {

    private Long id;
    private Integer quantity;
    private String description;

}
