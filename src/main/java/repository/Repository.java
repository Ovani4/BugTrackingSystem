package repository;

import java.util.List;

public interface Repository <T, ID>{
    List<T> getAll();
    T getById(ID id);
    T save(T t);
    T deleteById(ID id);
    T update (ID id);

}
