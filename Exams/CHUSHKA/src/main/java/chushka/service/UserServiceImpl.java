package chushka.service;

import chushka.domain.entities.User;
import chushka.domain.models.service.UserServiceModel;
import chushka.repository.UserRepository;
import org.apache.commons.codec.digest.DigestUtils;
import org.modelmapper.ModelMapper;

import javax.inject.Inject;

public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Inject
    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void userRegister(UserServiceModel userServiceModel) {
        User user = this.modelMapper.map(userServiceModel, User.class);
        user.setPassword(DigestUtils.sha256Hex(user.getPassword()));
        this.setUserRole(user);

        this.userRepository.save(user);
    }

    @Override
    public UserServiceModel userLogin(UserServiceModel userServiceModel) {
        User user = this.userRepository.findByUsername(userServiceModel.getUsername());

        if(user == null || !DigestUtils.sha256Hex(userServiceModel.getPassword()).equals(user.getPassword())){
            return null;
        }

        return this.modelMapper.map(user, UserServiceModel.class);
    }

    private void setUserRole(User user){
        user.setRole(this.userRepository.size() == 0 ? "Admin" : "User");
    }
}
