package com.joaodebarro.resident;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Table(name = "TB_RESIDENT")
public class ResidentModel {
    @Id
    @SequenceGenerator(name = "resident_id_sequence", sequenceName = "resident_id_sequence")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "resident_id_sequence")
    private Long id;

    @NotBlank
    @Size(min = 3, max = 50)
    @Column(name = "name", length = 50, nullable = false)
    private String name;

    @CPF
    @Size(min = 11, max = 11)
    @Column(name = "cpf",unique = true, length = 11)
    private String cpf;

    @Email
    @NotBlank
    @Size(min = 5, max = 100)
    @Column(name = "email", length = 100, nullable = false, unique = true)
    private String email;

    @Past
    @NotNull
    @Column(name="birth_date")
    private LocalDate birthDate;

    @Column(name = "phone_number", length = 14)
    @Size(max = 14)
    private String phoneNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "residential_unit_id", nullable = false)
    @NotNull
    private ResidentialUnitModel residentialUnit;

}
