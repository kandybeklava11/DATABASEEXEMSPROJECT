package model;

import enums.Gender;
import jakarta.persistence.*;
import lombok.*;

import javax.lang.model.element.Name;
import java.util.List;

@Entity
@Table(name = "Authors")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private int dateOfBirth;
    private String country;
    private Gender gender;
    @ManyToMany(fetch = FetchType.LAZY)
    private List<Publisher> publisherList;

    public Author(String firstName, String lastName, String email, int dateOfBirth, String country, Gender gender) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
        this.country = country;
        this.gender = gender;
    }
}
