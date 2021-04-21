package naming;
import java.util.List;

public interface ReadersManagerInterface {
    void addReader(Reader reader);

    List<Reader> getReaders();

    boolean contains(Reader reader);
}
