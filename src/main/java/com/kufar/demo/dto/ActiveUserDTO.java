package com.kufar.demo.dto;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
public class ActiveUserDTO {
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
