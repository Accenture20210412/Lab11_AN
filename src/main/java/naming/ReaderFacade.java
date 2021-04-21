package naming;

import java.util.List;


/**
 * interface odpowiadający za zarządzanie czytelnikami, używany w klasie Library Manager
 */
public interface ReaderFacade {
    void addNewReader(Reader reader);

    List<Reader> getReaders();
}
