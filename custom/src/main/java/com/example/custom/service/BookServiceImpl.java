package com.example.custom.service;

import com.example.custom.dao.BookDao;
import com.example.custom.dto.request.BookRequestDto;
import com.example.custom.dto.response.BookResponseDto;
import com.example.custom.dto.response.ResponseStatusOnlyDto;
import com.example.custom.entity.BookEntity;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class BookServiceImpl implements BookService{

    MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();

    @Autowired
    BookDao bookDao;

    @Override
    public List<BookResponseDto> getBookList() throws Exception {
        return mapBookEntityToResponse(bookDao.getBookList());
    }

    @Override
    public BookResponseDto getBookDetail(String id) throws Exception {
        BookResponseDto responseDto = (BookResponseDto) mapObject(bookDao.getBookDetail(id), BookResponseDto.class);
        return responseDto;
    }

    @Override
    public ResponseStatusOnlyDto addBook(BookRequestDto requestDto) throws Exception {
        BookEntity bookInput = (BookEntity) mapObject(requestDto, getBookEntityClass());
        BookEntity bookResult = bookDao.addBook(bookInput);
        ResponseStatusOnlyDto responseDto = new ResponseStatusOnlyDto();
        if (compareBooks(requestDto, bookResult)){
            responseDto.setMessage("Data save was success");
        } else {
            throw new Exception("Data was failed to be saved");
        }
        return responseDto;
    }

    @Override
    public ResponseStatusOnlyDto editBook(BookRequestDto requestDto) throws Exception {
        BookEntity oldBook = bookDao.getBookDetail(requestDto.getId());
        BookEntity newBook = (BookEntity) mapObject(requestDto, getBookEntityClass());
        ResponseStatusOnlyDto responseDto = new ResponseStatusOnlyDto();
        if (compareBooks(oldBook, newBook)){
            throw new Exception("Data was failed to be edited");
        } else {
            responseDto.setMessage("Data edit was success");
        }
        return responseDto;
    }

    @Override
    public ResponseStatusOnlyDto deleteBook(String id) throws Exception {
        Boolean result = bookDao.deleteBook(id);
        ResponseStatusOnlyDto response = new ResponseStatusOnlyDto();
        if (result){
            response.setMessage("Data has been deleted");
        } else {
            response.setMessage("Data was failed to be deleted");
        }
        return response;
    }

    @Override
    public List<BookResponseDto> getBookBySearch(String searchValue) throws Exception {
        String capitalSearchValue = searchValue.toUpperCase();
        Integer numberSearchValue;
        if (StringUtils.isNumeric(searchValue)){
            numberSearchValue = Integer.parseInt(searchValue);
        } else {
            numberSearchValue = null;
        }
        return mapBookEntityToResponse(bookDao.getBookBySearch(capitalSearchValue, numberSearchValue));
    }

    private Class<?> getBookEntityClass(){
        return BookEntity.class;
    }

    private boolean compareBooks(Object object1, Object object2) throws IllegalAccessException {
        boolean result = true;
        for (Field field : object1.getClass().getDeclaredFields()){
            field.setAccessible(true);
            Object value1 = field.get(object1);
            Object value2 = field.get(object2);
            if (Stream.of(value1, value2).allMatch(Objects::nonNull)){
                if (!Objects.equals(value1, value2)){
                    result = false;
                }
            }
        }
        return result;
    }

    private Object mapObject(Object source, Class<?> destinationClass){
        mapperFactory.classMap(source.getClass(), destinationClass);
        MapperFacade mapperFacade = mapperFactory.getMapperFacade();
        Object objectResult = mapperFacade.map(source, destinationClass);
        return objectResult;
    }

    private List<BookResponseDto> mapBookEntityToResponse(List<BookEntity> daoResult){
        List<BookResponseDto> listResponse = daoResult.stream().map(entity->{
            BookResponseDto responseDto = (BookResponseDto) mapObject(entity, BookResponseDto.class);
            return responseDto;
        }).collect(Collectors.toList());
        return listResponse;
    }
}
