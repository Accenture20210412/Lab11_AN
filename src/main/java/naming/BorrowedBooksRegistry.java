package naming;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Klasa zapisuje listę czytelników i ich wypożyczone książki
 */
class BorrowedBooksRegistry implements BorrowedBooksRegistryInterface {
    private final Map<Reader, List<ISBN>> rentals;

    BorrowedBooksRegistry() {
        rentals = new HashMap<>();
    }

    @Override
    public void borrow(Book book, Reader reader) {
        if (rentals.containsKey(reader)) {
            rentals.get(reader).add(book.getIsbn());
        } else {
            List<ISBN> books = new ArrayList<ISBN>();
            books.add(book.getIsbn());
            rentals.put(reader, books);
        }
    }

    public boolean readerHasNoBookCopy(Book book, Reader reader) {
        return !readerHasBookCopy(book, reader);
    }

    public void returnBook(Book book, Reader reader) {
        rentals.get(reader).remove(book.getIsbn());
    }

    public boolean readerHasBookCopy(Book book, Reader reader) {
        return rentals.containsKey(reader) && rentals.get(reader).contains(book.getIsbn());
    }
}
