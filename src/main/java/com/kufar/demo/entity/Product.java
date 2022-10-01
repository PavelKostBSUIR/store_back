package com.kufar.demo.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NonNull
    private String name;
    @NonNull
    private String city;
    @NonNull
    private String category;
    @NonNull
    private String description;
    @NonNull
    private Double cost;
    @ManyToOne
    @NonNull
    private User owner;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "photo", joinColumns = @JoinColumn(name = "product_id"))
    @Column(name = "photo_url")
    private List<String> photos;
}
