package com.example.CodeChallenge.institution.models;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table(name = "Institution")
@Data
@NoArgsConstructor
public class Institution {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotBlank
    @Size(max = 5, message = "Institution code must have a maximum length of 5")
    private String code;

    @NotBlank
    @Size(max = 50, message = "Institution name must have a maximum length of 50")
    private String name;

    @NotNull
    @Pattern(regexp = "[01]", message = "Institution status must be either 0 or 1")
    private String status;

}
