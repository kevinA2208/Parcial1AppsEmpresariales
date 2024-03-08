package org.example.jpa.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Entity
@Table(name = "Users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user")
    private Long idUser;

    @NotBlank(message = "Number Document is mandatory")
    @NotNull
    @Column(name = "number_document_user", nullable = false)
    private String numberDocumentUser;

    @NotBlank(message = "Email is mandatory")
    @NotNull
    @Column(name = "email_user", nullable = false)
    private String emailUser;

    @NotBlank(message = "Names are mandatory")
    @NotNull
    @Column(name = "names_user", nullable = false)
    private String namesUser;

    @NotBlank(message = "Lastnames are mandatory")
    @NotNull
    @Column(name = "lastnames_user", nullable = false)
    private String lastNamesUser;
}
