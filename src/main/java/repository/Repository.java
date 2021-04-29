package repository;

import java.util.List;

public interface Repository <T, ID>{
    List<T> getAll();
    void save(T t);
    void deleteById(ID id);
    private List<T> getListFromFile (){
        return null;
    }
    private void setFileFromList(List<T> type){}

}
