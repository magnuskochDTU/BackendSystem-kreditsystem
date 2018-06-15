package data.interfaces;

import java.util.List;

/**
 * Lavet af Magnus Stjernborg Koch
 */

//Creating generic interface for default CRUD operations in data storage
public interface DefaultDAO <T> {
    T add (T element);

    T update (T element);

    List<T> getAll ();
    T getById (int id);

    T delete (int id);
}
