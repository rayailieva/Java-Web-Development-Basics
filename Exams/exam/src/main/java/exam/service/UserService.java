package exam.service;

import exam.domain.models.service.UserServiceModel;

import java.util.List;

public interface UserService {

    void userRegister(UserServiceModel userServiceModel);

    UserServiceModel userLogin(UserServiceModel userServiceModel);

    List<UserServiceModel> listAllUsers();

    UserServiceModel getUserById(String id);


}
