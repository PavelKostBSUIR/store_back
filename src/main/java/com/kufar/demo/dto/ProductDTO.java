package com.kufar.demo.dto;

import lombok.*;

import javax.validation.constraints.NotNull;
import java.util.List;

@NoArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
public class ProductDTO {
    @NonNull
    @NotNull
    private Long id;
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
    private Double cost;
    @NonNull
    private List<String> photos;

}
