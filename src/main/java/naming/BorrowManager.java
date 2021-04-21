package naming;

// TODO: clean up methods


import static naming.BorrowOutcome.bookAlreadyBorrowedByReader;
import static naming.BorrowOutcome.noAvailableCopies;
import static naming.BorrowOutcome.notInCatalogue;
import static naming.BorrowOutcome.readerNotEnrolled;
import static naming.BorrowOutcome.success;

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
        if (readersManager.contains(reader) &&
                libraryResources.contains(book) &&
                !borrowedBooksRegistry.readerHasBookCopy(book, reader) &&
                libraryResources.availableCopies(book) > 0
        ) {
            libraryResources.borrowBook(book.getIsbn());
            borrowedBooksRegistry.borrow(book, reader);
            return success;
        } else
        if (!readersManager.contains(reader)) {
            return readerNotEnrolled;
        } else if (!libraryResources.contains(book)) {
            return notInCatalogue;
        } else if (borrowedBooksRegistry.readerHasBookCopy(book, reader)) {
            return bookAlreadyBorrowedByReader;
        } else if (libraryResources.availableCopies(book) == 0) {
            return noAvailableCopies;
        }
        return null;
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