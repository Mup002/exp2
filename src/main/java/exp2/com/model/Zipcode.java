package exp2.com.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "zipcodes")
public class Zipcode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_zipcode")
    private Long id;

    private String code;


}
