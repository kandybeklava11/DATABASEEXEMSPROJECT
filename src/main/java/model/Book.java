package model;

import enums.Gender;
import enums.Genre;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "Books")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Book {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        private String name;
        private String country;
        private int publishedYear;
        private int price;
        private Genre genre;

        @ManyToOne
        private Author author;

        @ManyToOne
        private Publisher publisher;

}
