package com.saimon.tuBank.dto;

import com.saimon.tuBank.entity.model.Client;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Min;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClientDto {

    private String id;
    private String name;
    private String login;
    private String password;
    @Min(18)
    private Integer old;
    private Client.GENDER gender;
}
