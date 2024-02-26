package agp.sanclemen.agp_proyecto.DAO;

import java.util.List;

public interface DAO<T> {

    T get(long id);

    List<T> getAll();

    void save(T t);

    void update(T t);

    void delete(T t);

    public boolean deleteById(long id);

    public List<Integer> getAllIds();

    void deleteAll();
}
