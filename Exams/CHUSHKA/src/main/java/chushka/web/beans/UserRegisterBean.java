package chushka.web.beans;

import org.modelmapper.ModelMapper;
import chushka.domain.models.binding.UserRegisterBindingModel;
import chushka.domain.models.service.UserServiceModel;
import chushka.service.UserService;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;

@Named
@RequestScoped
public class UserRegisterBean {

    private UserRegisterBindingModel userRegisterBindingModel;

    private UserService userService;
    private ModelMapper modelMapper;

    public UserRegisterBean() {
    }

    @Inject
    public UserRegisterBean(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
        this.userRegisterBindingModel = new UserRegisterBindingModel();
    }

    public UserRegisterBindingModel getUserRegisterBindingModel() {
        return this.userRegisterBindingModel;
    }

    public void setUserRegisterBindingModel(UserRegisterBindingModel userRegisterBindingModel) {
        this.userRegisterBindingModel = userRegisterBindingModel;
    }

    public void register() throws IOException {
        if (!this.userRegisterBindingModel.getPassword().equals(this.userRegisterBindingModel.getConfirmPassword())) {
            throw new IllegalArgumentException("Passwords don't match!");
        }
        this.userService.userRegister(this.modelMapper.map(this.userRegisterBindingModel, UserServiceModel.class));
        FacesContext.getCurrentInstance().getExternalContext().redirect("/faces/view/login.xhtml");
    }
}