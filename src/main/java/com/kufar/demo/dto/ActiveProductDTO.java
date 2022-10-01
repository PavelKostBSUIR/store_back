package com.kufar.demo.dto;

import com.kufar.demo.entity.User;
import lombok.*;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import java.util.List;

@NoArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
public class ActiveProductDTO {
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
    @NotNull
    @NonNull
    private UserDTO owner;
    @NonNull
    private List<String> photos;
}
