package ua.nure.parser.model.tiny;

import com.google.errorprone.annotations.Immutable;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * A unique user identifier.
 */
@Immutable
public final class UserId {

    private final String id;

    /**
     * Creates a {@code UserId} instance.
     *
     * @param id
     *         a unique user identifier
     */
    public UserId(String id) {
        checkNotNull(id);
        this.id = id;
    }

    /**
     * Obtains the user unique identifier.
     */
    public String getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        UserId id1 = (UserId) o;

        return id != null ? id.equals(id1.id) : id1.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "UserId{" +
                "id='" + id + '\'' +
                '}';
    }
}
