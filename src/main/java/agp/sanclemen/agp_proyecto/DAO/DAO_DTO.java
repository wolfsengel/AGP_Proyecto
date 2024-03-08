package agp.sanclemen.agp_proyecto.DAO;

import java.util.List;

public interface DAO_DTO<T,A>{
    T get(long id);

    List<T> getAll();
    List<A> getAllDTO();

    void save(T t);

    void update(T t);

    void delete(T t);

    boolean deleteById(long id);

    List<Long> getAllIds();

    List<A>  getProductsByClient(long id);
    void deleteAll();

}
