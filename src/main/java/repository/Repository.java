package repository;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public interface Repository <T, ID>{
    Logger logger = LogManager.getRootLogger();
    List<T> getAll();
    void save(T t);
    void deleteById(ID id);
    default List<T> getListFromFile(String filePath){
        return null;
    }
    Integer generateId();

}
