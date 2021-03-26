package com.example.widgets_are_us.productsservice.models;

import com.example.widgets_are_us.productsservice.utils.Dollars;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity
@Slf4j
@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    private Dollars price;


    @Transient
    @JsonIgnore
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private static final ObjectMapper mapper = new ObjectMapper();

    public String toJson() {
        try {
            log.info("Mapping customer to json");
            return mapper.writeValueAsString(this);
        }
        catch(Exception e) {
            log.error(e.getClass().getName(), e.getMessage());
            return "";
        }
    }

    public static Product fromJson(String json) {
        try {
            log.info("Mapping customer from json");
            return mapper.readValue(json, Product.class);
        }
        catch(Exception e) {
            log.error(e.getClass().getName(), e.getMessage());
            return new Product();
        }
    }


}
