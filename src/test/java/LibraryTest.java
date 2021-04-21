import java.util.List;
import naming.Book;
import naming.BookWarehouse;
import naming.BorrowOutcome;
import naming.Catalogue;
import naming.ISBN;
import naming.LibraryFactory;
import naming.LibraryManager;
import naming.LibraryResources;
import naming.Reader;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.*;

public class LibraryTest {

    LibraryFactory libraryFactory = new LibraryFactory();
    LibraryManager libraryManager;

    @BeforeAll
    public void setUp() {
        libraryManager = libraryFactory.library();
    }

    @Test
    public void shouldAddBookToLibrary() {
        Book book = new Book();
        libraryManager.addBook(book);
        Assert.assertEquals(1, libraryManager.getBookAmounts(book));
    }

    @Test
    public void shouldAddNewReader() {
        Reader reader = new Reader("reader");
        libraryManager.addNewReader();
        List<Reader> readers = libraryManager.getReaders();
        assertEquals(1, readers.size());
    }

    @Test
    void shouldNotBorrowBookNotExiststingInLibrary() {

        Reader reader = new Reader("reader");
        libraryManager.addNewReader(reader);
        Book book = new Book(new ISBN("numer"), "autor", "tytuł");

        BorrowOutcome borrowOutcome = libraryManager.borrowBook(book, reader);
        assertEquals(BorrowOutcome.notInCatalogue, borrowOutcome);
    }

    @Test
    void shouldNotBorrowBookWithoutExistingReader() {
        Reader reader = new Reader("reader");
        Book book = new Book(new ISBN("numer"), "autor", "tytuł");
        libraryManager.addBook(book);

        BorrowOutcome borrowOutcome = libraryManager.borrowBook(book, reader);
        assertEquals(BorrowOutcome.readerNotEnrolled, borrowOutcome);
    }

    @Test
    void shouldBorrowBookWithSuccess() {

        Reader reader = new Reader("reader");
        libraryManager.addNewReader(reader);
        Book book = new Book(new ISBN("numer"), "autor", "tytuł");
        libraryManager.addBook(book);


        BorrowOutcome borrowOutcome = libraryManager.borrowBook(book, reader);
        assertEquals(BorrowOutcome.success, borrowOutcome);
    }

    @Test
    void canNotBorrowBookThatIsNotInStock() {

        Reader reader = new Reader("reader");
        libraryManager.addNewReader(reader);
        Book book = new Book(new ISBN("numer"), "autor", "tytuł");
        libraryManager.addBook(book);


        Reader reader2 = new Reader("reade2");
        libraryManager.addNewReader(reader2);

        libraryManager.borrowBook(book, reader);
        BorrowOutcome borrowOutcome = libraryManager.borrowBook(book, reader2);
        assertEquals(BorrowOutcome.noAvailableCopies, borrowOutcome);
    }

    @Test
    void shouldCheckAmountsOfBooks() {

        Book book = new Book(new ISBN("numer"), "autor", "tytuł");
        libraryManager.addBook(book);
        libraryManager.addBook(book);
        assertEquals(2, libraryManager.getBookAmounts(book));
    }

    







}
