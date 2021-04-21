package naming;

import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.unmodifiableList;


/**
 * klasa odpowiada za zarządzanie czytelnikami w obrębie naszej biblioteki
 */
class ReadersManager implements ReadersManagerInterface {
    private final List<Reader> readers;

    ReadersManager() {
        readers = new ArrayList<>();
    }

    @Override
    public void addReader(Reader reader) {
        readers.add(reader);
    }
    @Override
    public List<Reader> getReaders() {
        return unmodifiableList(readers);
    }
    @Override
    public boolean contains(Reader reader) {
        return readers.contains(reader);
    }
}