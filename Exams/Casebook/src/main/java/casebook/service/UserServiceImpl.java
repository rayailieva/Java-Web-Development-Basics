package casebook.service;

import casebook.domain.entities.User;
import casebook.domain.models.service.UserServiceModel;
import casebook.repository.UserRepository;
import org.apache.commons.codec.digest.DigestUtils;
import org.modelmapper.ModelMapper;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

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

    @Override
    public List<UserServiceModel> listAllUsers() {
        return this.userRepository.findAll()
                .stream()
                .map(user -> this.modelMapper.map(user, UserServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public UserServiceModel getUserById(String id) {
        User user = this.userRepository.findById(id);

        if (user == null) {
            return null;
        }

        return this.modelMapper.map(user, UserServiceModel.class);
    }

    @Override
    public boolean addFriend(UserServiceModel userServiceModel) {
        User user = this.userRepository.update(this.modelMapper
                .map(userServiceModel, User.class));

        if(user != null){
            return true;
        }
        return false;
    }

    @Override
    public void delete(String id) {
        this.userRepository.delete(id);
    }

}
