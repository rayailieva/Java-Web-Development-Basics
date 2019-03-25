package sboj.service;

import sboj.domain.entities.User;
import sboj.domain.models.service.UserServiceModel;
import sboj.repository.UserRepository;
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

}
