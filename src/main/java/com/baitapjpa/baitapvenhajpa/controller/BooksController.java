package com.baitapjpa.baitapvenhajpa.controller;

import com.baitapjpa.baitapvenhajpa.entity.Books;
import com.baitapjpa.baitapvenhajpa.request.SaveBooksRequest;
import com.baitapjpa.baitapvenhajpa.service.BooksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.util.List;

@RestController
@RequestMapping("/books")
public class BooksController {
    @Autowired
    private BooksService booksService;

    @GetMapping
    public ResponseEntity<List<Books>> getAllBooks()  {
        return  ResponseEntity.ok().body(booksService.getAllBooks());
    }

    @PostMapping
    public ResponseEntity<Books> saveBooks(@RequestBody SaveBooksRequest saveBooksRequest) {
        return ResponseEntity.ok().body(booksService.saveBooks(saveBooksRequest));
    }

    @GetMapping(value={"/search"}, params = {"author"})
    public ResponseEntity<List<Books>> getBooksByAuthor(@RequestParam String author) {
        return ResponseEntity.ok().body(booksService.getBooksByAuthor(author));
    }

    @GetMapping(value="/search" ,params = {"minPrice", "maxPrice"})
    public ResponseEntity<List<Books>> getBooksByPriceBetween(@RequestParam Double minPrice, @RequestParam Double maxPrice) {
        return ResponseEntity.ok().body(booksService.getBooksByPriceBetween(minPrice, maxPrice));
    }
}
