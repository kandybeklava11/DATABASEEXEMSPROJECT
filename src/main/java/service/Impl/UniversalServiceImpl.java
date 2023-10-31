package service.Impl;

import model.Author;
import model.Book;
import model.Publisher;
import model.Reader;
import repository.Impl.UniversalRepositoryImpl;
import repository.UniversalRepository;
import service.UniversalService;

import javax.security.auth.kerberos.KerberosTicket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Flow;

public class UniversalServiceImpl implements UniversalService {
    private final UniversalRepositoryImpl universalRepository=new UniversalRepositoryImpl();
    @Override
    public Publisher savePublisher(Publisher publisher) {
       return universalRepository.savePublisher(publisher);

    }

    @Override
    public Publisher getPublisherById(Long id) {
      return universalRepository.getPublisherById(id);

    }

    @Override
    public List<Publisher> getAllPublishers() {
        return universalRepository.getAllPublishers();
    }

    @Override
    public Publisher updatePublisher(Publisher publisher) {
        return universalRepository.updatePublisher(publisher);
    }

    @Override
    public void deletePublisherByName(String name) {
universalRepository.deletePublisherByName(name);
    }
    ////////////////////////////////////////////////////////////////

    @Override
    public Author saveAuthor(Author author) {
        return universalRepository.saveAuthor(author);
    }

    @Override
    public Author updateAuthor(Author author) {
        return universalRepository.updateAuthor(author);
    }

    @Override
    public Author getAuthorById(Long id) {
        return universalRepository.getAuthorById(id);
    }

    @Override
    public List<Author> getAuthorsByPublisherId(Long publisherId) {
        return universalRepository.getAuthorsByPublisherId(publisherId);
    }

    @Override
    public void deleteAuthorById(Long id) {
    universalRepository.deleteAuthorById(id);
    }

    @Override
    public void assignAuthorToPublisher(Long authorId, Long publisherId) {
    universalRepository.assignAuthorToPublisher(authorId,publisherId);
    }
    //////////////////////////////////////////////////////////////////////////

    @Override
    public Book saveBook(Book book, Long authorId) {
        return universalRepository.saveBook(book, authorId);
    }

    @Override
    public Book updateBookAuthor(Long bookId, Long authorId) {
        return universalRepository.updateBookAuthor(bookId, authorId);
    }

    @Override
    public List<Publisher> getBookAndPublisherByBookId(Long bookId) {
      return universalRepository.getBookAndPublisherByBookId(bookId);
    }

    @Override
    public void deleteBookByAuthorId(Long authorId) {
universalRepository.deleteBookByAuthorId(authorId);
    }
    ////////////////////////////////////////////////////////////////////////////

    @Override
    public void saveReader(Reader reader) {
universalRepository.saveReader(reader);
    }

    @Override
    public void updateReader(Reader reader) {
universalRepository.updateReader(reader);
    }

    @Override
    public Reader getReaderByBookId(Long bookId) {
        return universalRepository.getReaderByBookId(bookId);
    }

    @Override
    public void deleteReaderById(Long readerId) {
universalRepository.deleteReaderById(readerId);
    }

    @Override
    public List<Reader> getReadersByAuthorId(Long authorId) {
        return universalRepository.getReadersByAuthorId(authorId);
    }
}
