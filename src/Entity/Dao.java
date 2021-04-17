package Entity;

import java.util.ArrayList;

/**
 * Interfaccia Dao fornisce delle crud operation per manipolare i dati ed andare a fare delle quesry in base a queste ultime
 */
public interface Dao<T> {

    T get(String id);
    ArrayList<T> getAll();
    void save(T t);
    void delete(T t);

}
