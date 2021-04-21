package naming;

import java.util.HashSet;
import java.util.Set;

import static java.util.Collections.unmodifiableSet;

public class Catalogue {
    private final Set<Book> catalogue;

    Catalogue() {
        catalogue = new HashSet<>();
    }

    void addBook(Book book) {
        catalogue.add(book);
    }

    Set<Book> getAll() {
        return unmodifiableSet(catalogue);
    }

    boolean contains(Book book) {
        return catalogue.contains(book);
    }

}
