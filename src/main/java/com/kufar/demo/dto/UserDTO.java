package com.kufar.demo.dto;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
public class UserDTO {
    @NotNull
    @NonNull
    private Long id;
    @NotNull
    @NonNull
    private String name;
    @NotNull
    @NonNull
    private String surname;
    @NotNull
    @NonNull
    private String phoneNumber;
}
