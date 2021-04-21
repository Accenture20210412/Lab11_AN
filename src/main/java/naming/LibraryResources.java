package naming;

import java.util.Set;

// TODO: class cleanup

public class LibraryResources implements Resources {
    private final Catalogue catalogue;
    private final BookWarehouse bookWarehouse;

    LibraryResources(Catalogue catalogue, BookWarehouse bookWarehouse) {
        this.catalogue = catalogue;
        this.bookWarehouse = bookWarehouse;
    }

    void addBook(Book book) {
        catalogue.addBook(book);
        bookWarehouse.addBook(book.getIsbn());
    }

    void addBooks(Book book, int amount) {
        catalogue.addBook(book);
        bookWarehouse.addBook(book.getIsbn(), amount);
    }

    @Override
    public int availableCopies(Book book) {
        return bookWarehouse.availableCopies(book.getIsbn());
    }

    Set<Book> bookCatalogue() {
        return catalogue.getAll();
    }

    public void borrowBook(ISBN isbn) {
        bookWarehouse.borrowBook(isbn);
    }

    @Override
    public boolean contains(Book book) {
        return catalogue.contains(book);
    }

    @Override
    public void addToResources(ISBN isbn) {
        bookWarehouse.addBook(isbn);
    }
}