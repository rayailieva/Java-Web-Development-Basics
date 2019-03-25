package casebook.service;

import casebook.domain.models.service.UserServiceModel;

import java.util.List;

public interface UserService {

    void userRegister(UserServiceModel userServiceModel);

    UserServiceModel userLogin(UserServiceModel userServiceModel);

    List<UserServiceModel> listAllUsers();

    UserServiceModel getUserById(String id);

    boolean addFriend(UserServiceModel userServiceModel);

    void delete(String id);
}
