package naming;

/**
 * tworzenie biblioteki
 */
public class LibraryFactory {
    public LibraryManager library() {
        ReadersManager readersManager = new ReadersManager();
        LibraryResources libraryResources = new LibraryResources(new Catalogue(), new BookWarehouse());
        BorrowedBooksRegistry borrowedBookRegistry = new BorrowedBooksRegistry();
        BorrowManager borrowManager = new BorrowManager(libraryResources, readersManager, borrowedBookRegistry);
        return new LibraryManager(readersManager, borrowManager, libraryResources);
    }
}
