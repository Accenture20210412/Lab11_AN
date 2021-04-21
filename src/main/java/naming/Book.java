package naming;

import java.util.Objects;

/**
 * Klasa przedstawiająca obiekt typu książka.
 */

public class Book {

    private ISBN isbn;
    private String author;
    private String title;

    public Book(ISBN isbn, String author, String title) {
        this.isbn = isbn;
        this.author = author;
        this.title = title;
    }

    public Book() {
    }

    ISBN getIsbn() {
        return isbn;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return isbn.equals(book.isbn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(isbn);
    }
}
