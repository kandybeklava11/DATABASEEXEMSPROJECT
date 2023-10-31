package repository.Impl;

import Config.Config;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;
import model.Author;
import model.Book;
import model.Publisher;
import model.Reader;
import org.hibernate.Hibernate;
import repository.UniversalRepository;

import java.util.ArrayList;
import java.util.List;

public class UniversalRepositoryImpl implements  UniversalRepository{
    private final EntityManagerFactory entityManagerFactory=Config.getSession();

    @Override
    public Publisher savePublisher(Publisher publisher) {
        EntityManager entityManager= entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(publisher);
        entityManager.getTransaction().commit();
        entityManager.close();
        return publisher;
    }

    @Override
    public Publisher getPublisherById(Long id) {
        EntityManager entityManager=entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Publisher publisher=entityManager.find(Publisher.class,id);
        entityManager.getTransaction().commit();
        entityManager.close();
        return publisher;
    }

    @Override
    public List<Publisher> getAllPublishers() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            TypedQuery<Publisher> query = entityManager.createQuery(
                    "SELECT p FROM Publisher p ORDER BY p.firstName", Publisher.class);
            List<Publisher> publishers = query.getResultList();
            return publishers;
        } finally {
            entityManager.close();
        }
    }

    @Override
    public Publisher updatePublisher(Publisher publisher) {
        EntityManager entityManager=entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.merge(publisher);
        entityManager.getTransaction().commit();
        entityManager.close();

        return publisher;
    }

    @Override
    public void deletePublisherByName(String name) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        try {
            TypedQuery<Publisher> query = entityManager.createQuery(
                    "SELECT p FROM Publisher p WHERE p.firstName = :name", Publisher.class);
            query.setParameter("name", name);

            List<Publisher> publishers = query.getResultList();

            if (!publishers.isEmpty()) {
                Publisher publisher = publishers.get(0);
                entityManager.remove(publisher);
            }

            entityManager.getTransaction().commit();
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            // Обработка исключения или логирование ошибки
            throw e;
        } finally {
            entityManager.close();

        }
    }
    ////////////////////////////////////////////////////////////

    @Override
    public Author saveAuthor(Author author) {
        EntityManager entityManager=entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(author);
        entityManager.getTransaction().commit();
        entityManager.close();
        return author;
    }

    @Override
    public Author updateAuthor(Author author) {
        EntityManager entityManager=entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.merge(author);
        entityManager.getTransaction().commit();
        entityManager.close();
        return author;
    }

    @Override
    public Author getAuthorById(Long id) {
        EntityManager entityManager=entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Author author=entityManager.find(Author.class,id);
        entityManager.getTransaction().commit();
        entityManager.close();
        return author;
    }

    @Override
    public List<Author> getAuthorsByPublisherId(Long publisherId) {
        EntityManager entityManager=entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        TypedQuery<Author> typedQuery=entityManager.createQuery(
                "SELECT a FROM Author a WHERE a.id = :publisherId", Author.class);
        typedQuery.setParameter("publisherId", publisherId);
        List<Author> authors = typedQuery.getResultList();

        entityManager.getTransaction().commit();
        entityManager.close();
        return authors;
    }

    @Override
    public void deleteAuthorById(Long id) {
        EntityManager entityManage=entityManagerFactory.createEntityManager();
        entityManage.getTransaction().begin();
        TypedQuery<Author>typedQuery=entityManage.createQuery(
                "SELECT a FROM Author a WHERE a.id=:id",Author.class);
        typedQuery.setParameter("id",id);
        List<Author>authors=typedQuery.getResultList();

        if(!authors.isEmpty()){
            Author author=authors.get(0);
            entityManage.remove(author);
        }
        entityManage.getTransaction().commit();
        entityManage.close();


    }

    @Override
    public void assignAuthorToPublisher(Long authorId, Long publisherId) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        try {
            TypedQuery<Author> typedQuery = entityManager.createQuery(
                    "SELECT a FROM Author a WHERE a.id = :authorId", Author.class);
            typedQuery.setParameter("authorId", authorId);
            Author author = typedQuery.getSingleResult();

            TypedQuery<Publisher> publisherQuery = entityManager.createQuery(
                    "SELECT p FROM Publisher p WHERE p.id = :publisherId", Publisher.class);
            publisherQuery.setParameter("publisherId", publisherId);
            Publisher publisher = publisherQuery.getSingleResult();

            if (author != null && publisher != null) {
                publisher.getAuthors().add(author); // Установка связи между автором и издателем
                entityManager.getTransaction().commit();
            } else {
                // Обработка случая, когда автор или издатель не найдены
            }
        } catch (Exception e) {
            // Обработка исключений при работе с базой данных
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
        } finally {
            entityManager.close();
        }
    }
    //////////////////////////////////////////////////////////////////////////

    @Override
    public Book saveBook(Book book, Long authorId) {
        EntityManager entityManager=entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.merge(book);
        entityManager.getTransaction().commit();
        entityManager.close();
        return null;
    }

    @Override
    public Book updateBookAuthor(Long bookId, Long authorId) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            Book book = entityManager.find(Book.class, bookId);
            if (book != null) {
                Author author = entityManager.find(Author.class, authorId);
                if (author != null) {
                    book.setAuthor(author);
                    entityManager.merge(book);
                    entityManager.getTransaction().commit();
                    return book;
                } else {
                    System.out.println("Author not found.");
                }
            } else {
                System.out.println("Book not found.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            entityManager.getTransaction().rollback();
        } finally {
            entityManager.close();
        }
        return null;
    }

    @Override
    public List<Publisher> getBookAndPublisherByBookId(Long bookId) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Publisher publisher = null;
        try {
            entityManager.getTransaction().begin();

            Book book = entityManager.find(Book.class, bookId);
            if (book != null) {
                publisher = book.getPublisher();
            } else {
                System.out.println("Book not found.");
            }

            entityManager.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            entityManager.getTransaction().rollback();
        } finally {
            entityManager.close();
        }
        return (List<Publisher>) publisher;
    }

    @Override
    public void deleteBookByAuthorId(Long authorId) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        try {
            TypedQuery<Book> query = entityManager.createQuery(
                    "SELECT b FROM Book b WHERE b.author.id = :authorId", Book.class);
            query.setParameter("authorId", authorId);
            List<Book> books = query.getResultList();

            for (Book book : books) {
                entityManager.remove(book);
            }

            entityManager.getTransaction().commit();
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
        } finally {
            entityManager.close();
        }
    }
    ///////////////////////////////////////////////////////////////////

    @Override
    public void saveReader(Reader reader) {
        EntityManager entityManager=entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(reader);
        entityManager.getTransaction().commit();
        entityManager.close();

    }

    @Override
    public void updateReader(Reader reader) {
        EntityManager entityManager=entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.merge(reader);
        entityManager.getTransaction().commit();
        entityManager.close();

    }

    @Override
    public Reader getReaderByBookId(Long bookId) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        TypedQuery<Reader> typedQuery = entityManager.createQuery(
                "SELECT r FROM Reader r WHERE r.id=:bookId", Reader.class);
        typedQuery.setParameter("bookId", bookId);
        List<Reader> readers = typedQuery.getResultList();
        entityManager.getTransaction().commit();
        entityManager.close();

        if (!readers.isEmpty()) {
            return readers.get(0);
        }

        return null;
    }

    @Override
    public void deleteReaderById(Long readerId) {
        EntityManager entityManager=entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        TypedQuery <Reader>typedQuery=entityManager.createQuery(
                "SELECT r FROM Reader r WHERE r.id=:readerId",Reader.class
        );
        typedQuery.setParameter("readerId",readerId);
        List<Reader> reader=typedQuery.getResultList();

        if(!reader.isEmpty()){
         Reader reader1=reader.get(0);
         entityManager.remove(reader1);
        }
        entityManager.getTransaction().commit();
        entityManager.close();

        


    }

    @Override
    public List<Reader> getReadersByAuthorId(Long authorId) {
        EntityManager entityManager=entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        TypedQuery<Reader>typedQuery=entityManager.createQuery(
                "SELECT r FROM Reader r WHERE r.id=:authorId",Reader.class);
        typedQuery.setParameter("authorId",authorId);
        List<Reader> readers=typedQuery.getResultList();
        entityManager.getTransaction().commit();
        entityManager.close();
        return  readers;

    }
}
