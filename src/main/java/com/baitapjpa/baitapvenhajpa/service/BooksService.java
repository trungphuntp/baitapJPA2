package com.baitapjpa.baitapvenhajpa.service;

import com.baitapjpa.baitapvenhajpa.entity.Books;
import com.baitapjpa.baitapvenhajpa.request.SaveBooksRequest;

import java.util.List;

public interface BooksService {
     List<Books> getAllBooks();
     Books saveBooks(SaveBooksRequest saveBooksRequest);
     List<Books> getBooksByAuthor(String author);
     List<Books> getBooksByPriceBetween(Double priceStart, Double priceEnd);
}
