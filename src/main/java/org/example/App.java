package org.example;

import Config.Config;
import enums.Gender;
import enums.Genre;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.GeneratedValue;
import model.Author;
import model.Book;
import model.Publisher;
import model.Reader;
import service.Impl.UniversalServiceImpl;
import service.UniversalService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App
{
    public static void main( String[] args ){
     //   EntityManagerFactory entityManagerFactory= Config.getSession();

       UniversalService universalService=new UniversalServiceImpl();

//        Author aitmatov = Author.builder()
//                .firstName("Chyngyz")
//                .lastName("Aitmatov")
//                .email("Aitmatov@gmail.com")
//                .dateOfBirth(1928)
//                .country("Kyrgyzstan")
//                .gender(Gender.MALE)
//                .build();
//        universalService.saveAuthor(aitmatov);

//        Book birinchiMugalim = Book.builder()
//                .name("BirinchiMugalim")
//                .country("Kyrgyzstan")
//                .publishedYear(1962)
//                .price(3000)
//                .genre(Genre.DRAMA)
//                .build();
//        universalService.saveBook(birinchiMugalim);



        Publisher publisher = Publisher.builder()
                .firstName("Bakyt")
                .address("Mamytova")
                .build();
        Author aitmatov = Author.builder()
                .firstName("Chyngyz")
                .lastName("Aitmatov")
                .email("Aitmatov@gmail.com")
                .dateOfBirth(1928)
                .country("Kyrgyzstan")
                .gender(Gender.MALE)
                .build();
        Book birinchiMugalim = Book.builder()
                .name("BirinchiMugalim")
                .country("Kyrgyzstan")
                .publishedYear(1962)
                .price(3000)
                .genre(Genre.DRAMA)
                .build();
        Reader reader = Reader.builder()
                .name("Maksat")
                .age(34)
                .email("Mak@gmail.com")
                .build();
        Scanner scanner = new Scanner(System.in);
        int choice = 0;

        do {
            System.out.println("1. Save Publisher");
            System.out.println("2. Get Publisher by ID");
            System.out.println("3. Get all Publishers");
            System.out.println("4. Update Publisher");
            System.out.println("5. Delete Publisher by Name");
            System.out.println("6. Save Author");
            System.out.println("7. Update Author");
            System.out.println("8. Get Author by ID");
            System.out.println("9. Get Authors by Publisher ID");
            System.out.println("10. Delete Author by ID");
            System.out.println("11. Assign Author to Publisher");
            System.out.println("12. Save Book");
            System.out.println("13. Update Book Author");
            System.out.println("14. Get Book and Publisher by Book ID");
            System.out.println("15. Delete Book by Author ID");
            System.out.println("16. Save Reader");
            System.out.println("17. Update Reader");
            System.out.println("18. Get Reader by Book ID");
            System.out.println("19. Delete Reader by ID");
            System.out.println("20. Get Readers by Author ID");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                   universalService.savePublisher(publisher);
                    System.out.println(publisher);
                    System.out.println("Saved Successfully!");
                    break;
                case 2:
                    System.out.println( universalService.getPublisherById(2L));
                    break;
                case 3:
                    System.out.println( universalService.getAllPublishers());
                    break;
                case 4:
                    System.out.println(universalService.updatePublisher(publisher));
                    break;
                case 5:
                    universalService.deletePublisherByName("Bakyt");
                    System.out.println(publisher);
                    break;
                case 6:
                    System.out.println(universalService.saveAuthor(aitmatov));
                    System.out.println("Saved Successfully!");
                    break;
                case 7:
                    universalService.updateAuthor(aitmatov);
                    break;
                case 8:
                    universalService.getAuthorById(1L);
                    break;
                case 9:
                    universalService.getAuthorsByPublisherId(1L);
                    break;
                case 10:
                    universalService.deleteAuthorById(5L);
                    break;
                case 11:
                    universalService.assignAuthorToPublisher(1L, 1L);
                    break;
                case 12:
                    universalService.saveBook(birinchiMugalim,1L);
                    break;
                case 13:
                    universalService.updateBookAuthor(1L,1L);
                    break;
                case 14:
                    universalService.getBookAndPublisherByBookId(1L);
                    break;
                case 15:
                    universalService.deleteBookByAuthorId(1L);
                    break;
                case 16:
                    universalService.saveReader(reader);
                    break;
                case 17:
                    universalService.updateReader(reader);
                    break;
                case 18:
                    universalService.getReaderByBookId(1L);
                    break;
                case 19:
                    universalService.deleteReaderById(1L);
                    break;
                case 20:
                    universalService.getReadersByAuthorId(1L);
                    break;
                case 0:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
           }
        } while (choice != 0);
    }







    }

