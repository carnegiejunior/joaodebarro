package com.joaodebarro.resident;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Table(name = "TB_RESIDENTIAL_UNIT")
public class ResidentialUnitEntity {
    @Id
    @SequenceGenerator(name = "residentUnit_id_sequence", sequenceName = "residentUnit_id_sequence")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "residentUnit_id_sequence")
    private Long id;

    @Size(min = 3, max = 10)
    @Column(name = "designation", length = 10, nullable = false, unique = true)
    private String designation;
}
