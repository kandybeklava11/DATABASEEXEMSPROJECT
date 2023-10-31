package service;

import model.Author;
import model.Book;
import model.Publisher;
import model.Reader;

import java.util.List;

public interface UniversalService {
    //PUBLISHER
    Publisher savePublisher(Publisher publisher);
    Publisher getPublisherById(Long id);
    List<Publisher> getAllPublishers();
    Publisher updatePublisher(Publisher publisher);
    void deletePublisherByName(String name);

    //////////////////////////////////////////////////////////////////////////////
//AUTHOR
    Author saveAuthor(Author author);
    Author updateAuthor(Author author);
    Author getAuthorById(Long id);
    List<Author> getAuthorsByPublisherId(Long publisherId);
    void deleteAuthorById(Long id);
    void assignAuthorToPublisher(Long authorId, Long publisherId);

    ///////////////////////////////////////////////////////////////////////////////
    //BOOK
    Book saveBook(Book book, Long authorId);
    Book updateBookAuthor(Long bookId, Long authorId);
    List<Publisher> getBookAndPublisherByBookId(Long bookId);
    void deleteBookByAuthorId(Long authorId);

    //////////////////////////////////////////////////////////////////////////////
    //READER
    void saveReader(Reader reader);
    void updateReader(Reader reader);
    Reader getReaderByBookId(Long bookId);
    void deleteReaderById(Long readerId);
    List<Reader> getReadersByAuthorId(Long authorId);
}
