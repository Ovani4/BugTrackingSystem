package repository;

import java.util.List;

public interface Repository <T, ID>{
    List<T> getAll();
    T save(T t);
    void deleteById(ID id);
    private List<T> getListFromFile (){
        return null;
    }

}
