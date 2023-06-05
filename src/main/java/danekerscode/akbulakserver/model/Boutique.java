package danekerscode.akbulakserver.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Boutique {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; // entity identifier
    private String name; // name of boutique

    @OneToOne(cascade = CascadeType.DETACH) // one-to-one relationship between address & boutique
    @JsonIgnore
    @JoinColumn(name = "address_id" , referencedColumnName = "id" , unique = true)
    private Address address;  // address of boutique

    @ManyToMany()  // many-yo-many relationship between  boutique & category
    @JoinTable(
            name = "boutique_categories",
            joinColumns = @JoinColumn(name = "boutique_id" , referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "category_id" , referencedColumnName = "id")
    )
    private List<Category> categories;
    private String comment; // optional field for adding some notes about boutique
}
