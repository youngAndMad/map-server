package danekerscode.akbulakserver.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; // entity identifier

    private String bazar; // name of bazar('Akbulak')
    private Integer floor; // floor value 1 || 2 || ... n
    private String container; // container name 2a || 3c ...

    @OneToOne() // one-to-one relationship between address & boutique
    @JoinColumn(name = "boutique_id" , referencedColumnName = "id")
    private Boutique boutique;
}
