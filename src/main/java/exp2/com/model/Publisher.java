package exp2.com.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "publishers")
public class Publisher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_publisher")
    private Long id;

    @Column(name = "name_publisher")
    private String name;

    private String city;

    @OneToMany(mappedBy = "publisher",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Author> authors = new ArrayList<>();
    public void AddAuthor(Author author){
        authors.add(author);
    }
    public void RemoveAuthor(Author author){
        authors.remove(author);
    }

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_zipcode")
    private Zipcode zipcode;
}
