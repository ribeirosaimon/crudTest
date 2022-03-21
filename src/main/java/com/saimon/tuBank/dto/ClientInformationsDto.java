package com.saimon.tuBank.dto;

import com.saimon.tuBank.entity.model.Client;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClientInformationsDto {
    private String id;
    private String name;
    private Integer old;
    private Client.GENDER gender;
}
