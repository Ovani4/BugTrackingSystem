package repository;

import java.util.List;

public interface Repository <T, ID>{
    List<T> getAll();
    void save(T t);
    void deleteById(ID id);
    default List<T> getListFromFile(){
        return null;
    }
    default void setFileFromList(List<T> type){}

}
