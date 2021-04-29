package controller;

import model.User;
import repository.UserRepository;
import repository.implclass.IOUserRepository;


import java.util.List;

public class UserController {
    UserRepository ur = new IOUserRepository();

    public List<User> getAll(){
        return ur.getAll();
    }

    public void save(User user){
        ur.save(user);
    }

    public void deleteById(Integer integer){
        ur.deleteById(integer);
    }


}
