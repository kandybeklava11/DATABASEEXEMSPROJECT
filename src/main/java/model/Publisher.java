package model;

import enums.Gender;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "Publishers")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Publisher {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        private String firstName;
        private String address;

        @OneToMany(fetch = FetchType.EAGER)
        private List<Author> authors;

}
