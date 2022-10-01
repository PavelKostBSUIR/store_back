package com.kufar.demo.dto;

import lombok.*;

import javax.validation.constraints.NotNull;
import java.util.List;

@NoArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
public class CreateProductDTO {
    @NotNull
    @NonNull
    private String name;
    @NotNull
    @NonNull
    private String city;
    @NotNull
    @NonNull
    private String category;
    @NotNull
    @NonNull
    private String description;
    @NotNull
    @NonNull
    private Double cost;
    @NonNull
    private List<String> photos;
}
