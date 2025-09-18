package com.baitapjpa.baitapvenhajpa.service;

import com.baitapjpa.baitapvenhajpa.entity.Books;
import com.baitapjpa.baitapvenhajpa.repository.BooksRepository;
import com.baitapjpa.baitapvenhajpa.request.SaveBooksRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BooksService {
    @Autowired
    private BooksRepository booksRepository;

    public List<Books> getAllBooks() {
        return booksRepository.findAll();
    }

    public Books saveBooks(SaveBooksRequest saveBooksRequest) {
        Books books = new Books();
        books.setTitle(saveBooksRequest.getTitle().trim());
        books.setPrice(saveBooksRequest.getPrice());
        books.setAuthor(saveBooksRequest.getAuthor().trim());
        return booksRepository.save(books);
    }

    public List<Books> getBooksByAuthor(String author) {
        return booksRepository.findByAuthor(author);
    }

    public List<Books> getBooksByPriceBetween(Double priceStart, Double priceEnd) {
        return booksRepository.findByPriceBetween(priceStart, priceEnd);
    }

}
