package com.example.custom.dao;

import com.example.custom.entity.AuthorEntity;
import com.example.custom.entity.BookEntity;
import com.example.custom.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Repository
public class BookDaoNativeImpl implements BookDao{

    @Autowired
    AuthorRepository authorRepository;

    @PersistenceContext
    private EntityManager em;

    public BookDaoNativeImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<BookEntity> getBookList() throws Exception {
        String query = "SELECT * FROM Book";
        List<BookEntity> listBookEntity = getListBookEntity(query);
        return listBookEntity;
    }

    @Override
    public BookEntity getBookDetail(String id) throws Exception {
        String query = "SELECT * FROM Book WHERE ID = :id";
        BookEntity book;
        try {
            book = (BookEntity) em.createNativeQuery(query, BookEntity.class).setParameter("id", id).getSingleResult();
        } catch (Exception e){
            throw new NoResultException("Book not found");
        }
        return book;
    }

    @Override
    @Transactional
    public BookEntity addBook(BookEntity book) throws Exception {
        em.persist(book);
        em.detach(book);
        return book;
    }

    @Override
    @Transactional
    public BookEntity editBook(BookEntity book) throws Exception {
        em.merge(book);
        em.detach(book);
        return book;
    }

    @Override
    @Transactional
    public Boolean deleteBook(String id) throws Exception {
        String query = "DELETE FROM Book WHERE ID = :id";
        int numberEntityDeleted = em.createNativeQuery(query).setParameter("id", id).executeUpdate();
        return numberEntityDeleted>0;
    }

    @Override
    public List<BookEntity> getBookBySearch(String searchValue, Integer searchValueInt) throws Exception {
        String query = null;
        if (searchValueInt==null){
            query = "SELECT b.* FROM Book b " +
                    "LEFT JOIN Author a " +
                    "ON b.AUTHOR_ID = a.ID" +
                    "WHERE b.TITLE LIKE '%:searchValue%' " +
                    "OR a.TITLE LIKE '%:searchValue%' ";
        } else {
            query = "SELECT b.* FROM Book b " +
                "LEFT JOIN Author a " +
                "ON b.AUTHOR_ID = a.ID" +
                "WHERE b.TITLE LIKE '%:searchValue%' " +
                "OR a.TITLE LIKE '%:searchValue%' " +
                "or b.VOLUME = :searchValueInt;";
        }

        List<BookEntity> listBookEntity = getListBookEntity(query);
        return listBookEntity;
    }

    private List<BookEntity> getListBookEntity(String query){
        List<?> resultQuery = em.createNativeQuery(query).getResultList();
        List<BookEntity> listBookEntity = new ArrayList<>();

        resultQuery.forEach(obj->{
            Object[] objArr = (Object[]) obj;
            BookEntity book = new BookEntity();
            book.setId(objArr[0].toString());
            book.setTitle(objArr[1].toString());
            String authorId = objArr[2].toString();
            AuthorEntity author;
            try {
                author = (AuthorEntity) authorRepository.findById(authorId).orElseThrow(NoResultException::new);
            } catch (Throwable e) {
                throw new NoResultException(e.getMessage());
            }
            book.setAuthor(author);
            book.setVolume((Integer) objArr[3]);
            listBookEntity.add(book);
        });
        return listBookEntity;
    }
}
