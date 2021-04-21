package naming;

// TODO: clean up methods

import static java.example.naming.BorrowOutcome.*;
import static java.example.naming.BorrowOutcome.bookAlreadyBorrowedByReader;
import static java.example.naming.BorrowOutcome.noAvailableCopies;
import static java.example.naming.BorrowOutcome.notInCatalogue;
import static java.example.naming.BorrowOutcome.readerNotEnrolled;

class BorrowManager {
    private final Resources libraryResources;
    private final ReadersManagerInterface readersManager;
    private final BorrowedBooksRegistryInterface borrowedBooksRegistry;

    BorrowManager(Resources books, ReadersManagerInterface readersManager, BorrowedBooksRegistryInterface borrowedBookRegistry) {
        this.libraryResources = books;
        this.readersManager = readersManager;
        this.borrowedBooksRegistry = borrowedBookRegistry;
    }

    BorrowOutcome borrowBook(Book book, Reader reader) {
        if (isRegister(reader)) {
            return readerNotEnrolled;
        } else if (isAvailableInCatalogue(book)) {
            return notInCatalogue;
        } else if (isAlreadyBorrowed(book, reader)) {
            return bookAlreadyBorrowedByReader;
        } else if (copyIsAvailable(book)) {
            return noAvailableCopies;
        } else {
            return borrowBook(book, reader);
        }
    }


    ReturnOutcome returnBook(Book book, Reader reader) {
        if (isRegister(reader)) {
            return ReturnOutcome.READER_NOT_ENROLLED;
        }else
        if (!isAvailableInCatalogue(book)) {
            return ReturnOutcome.NOT_IN_CATALOGUE;
        }else
        if (isAlreadyBorrowed(book, reader)) {
            return ReturnOutcome.BOOK_NOT_BORROWED_BY_READER;
        }else {
            return giveBack(book, reader);
        }
    }

    private ReturnOutcome borrow(Book book, Reader reader) {
        libraryResources.borrowBook(book.getIsbn());
        borrowedBooksRegistry.borrow(book, reader);
        return ReturnOutcome.SUCCESS;
    }

    private ReturnOutcome giveBack(Book book, Reader reader) {
        libraryResources.addToResources(book.getIsbn());
        borrowedBooksRegistry.returnBook(book, reader);
        return ReturnOutcome.SUCCESS;
    }

    private boolean isAlreadyBorrowed(Book book, Reader reader) {
        return borrowedBooksRegistry.readerHasNoBookCopy(book, reader);
    }


    private boolean copyIsAvailable(Book book) {
        return libraryResources.availableCopies(book) == 0;
    }

    private boolean isAvailableInCatalogue(Book book) {
        return libraryResources.contains(book);
    }

    private boolean isRegister(Reader reader) {
        return !readersManager.contains(reader);
    }

}