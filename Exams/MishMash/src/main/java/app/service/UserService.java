package app.service;


import app.domain.models.service.ChannelServiceModel;
import app.domain.models.service.UserServiceModel;

import java.util.List;

public interface UserService {

    void userRegister(UserServiceModel userServiceModel);

    UserServiceModel userLogin(UserServiceModel userServiceModel);

    List<UserServiceModel> findAllUsers();

    UserServiceModel getUserById(String id);

    boolean addChannel(UserServiceModel userServiceModel);

}
