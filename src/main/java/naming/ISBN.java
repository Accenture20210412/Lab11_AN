package naming;

import java.util.Objects;

public class ISBN {
    static ISBN of(String rawIsbn) {
        return new ISBN(rawIsbn);
    }

    private final String raw;

    public ISBN(String raw) {
        this.raw = raw;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ISBN isbn = (ISBN) o;
        return raw.equals(isbn.raw);
    }

    @Override
    public int hashCode() {
        return Objects.hash(raw);
    }
}
