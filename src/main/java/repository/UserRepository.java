package repository;

import model.User;

public interface UserRepository extends Repository<User, Integer>{
    User getById(Integer id);
}
