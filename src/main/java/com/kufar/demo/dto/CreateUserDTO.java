package com.kufar.demo.dto;

import lombok.*;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@NoArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
public class CreateUserDTO {
    @NotNull
    @NonNull
    private String name;
    @NotNull
    @NonNull
    private String surname;
    @NotNull
    @NonNull
    @Email
    private String login;
    @NotNull
    @NonNull
    private String phoneNumber;
    @NotNull
    @NonNull
    @Size(min = 6)
    private String password;

}
