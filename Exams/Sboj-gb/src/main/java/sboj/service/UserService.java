package sboj.service;

import sboj.domain.models.service.UserServiceModel;

public interface UserService {

    void userRegister(UserServiceModel userServiceModel);

    UserServiceModel userLogin(UserServiceModel userServiceModel);

}
