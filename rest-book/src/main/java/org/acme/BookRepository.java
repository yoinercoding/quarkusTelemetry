package org.acme;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.PathParam;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class BookRepository {

    @ConfigProperty(name = "books.genre", defaultValue = "Sci Fi")
    String genre;

    public List<Book> getAllBooks() {
        return List.of(
                new Book(1, "Understanding Quarkus", "Yoiner", 2024, genre),
                new Book(2, "Practicing Quarkus", "Yoiner", 2020, genre),
                new Book(3, "Effective Java", "Yoiner", 2003, genre),
                new Book(4, "Thinking Java", "Yoiner", 2022, genre)
        );
    }

    public Optional<Book> getBook(int id) {
        return getAllBooks().stream()
                .filter(book -> book.id == id).findFirst();
    }
}