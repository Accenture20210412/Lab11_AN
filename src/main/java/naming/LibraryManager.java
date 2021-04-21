package naming;

import java.util.List;
import java.util.Set;

public class LibraryManager implements BookFacade, ReaderFacade {
    private final ReadersManagerInterface readersManager;
    private final BorrowManager borrowManager;
    private final LibraryResources libraryResources;

    public LibraryManager(ReadersManagerInterface readersManager, BorrowManager borrowManager, LibraryResources libraryResources) {
        this.readersManager = readersManager;
        this.borrowManager = borrowManager;
        this.libraryResources = libraryResources;
    }

    public LibraryManager() {
    }

    @Override
    public void addBook(Book book) {
        libraryResources.addBook(book);
    }

    @Override
    public void addBooks(Book book, int howMany) {
        libraryResources.addBooks(book, howMany);
    }

    @Override
    public int getBookAmounts(Book book) {
        return libraryResources.availableCopies(book);
    }

    @Override
    public Set<Book> getBooks() {
        return libraryResources.bookCatalogue();
    }

    @Override
    public void addNewReader(Reader reader) {
        readersManager.addReader(reader);
    }

    @Override
    public List<Reader> getReaders() {
        return readersManager.getReaders();
    }

    @Override
    public BorrowOutcome borrowBook(Book book, Reader reader) {
        return borrowManager.borrowBook(book, reader);
    }

    @Override
    public ReturnOutcome returnBook(Book book, Reader reader) {
        return borrowManager.returnBook(book, reader);
    }
}
