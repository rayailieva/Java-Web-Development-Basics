package casebook.web.beans;

import org.modelmapper.ModelMapper;
import casebook.domain.models.binding.UserRegisterBindingModel;
import casebook.domain.models.service.UserServiceModel;
import casebook.service.UserService;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;

@Named
@RequestScoped
public class UserRegisterBean {

    private UserRegisterBindingModel model;

    private UserService userService;
    private ModelMapper modelMapper;

    public UserRegisterBean() {
    }

    @Inject
    public UserRegisterBean(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
        this.initModels();
    }

    private void initModels() {
        this.model = new UserRegisterBindingModel();
    }

    public UserRegisterBindingModel getUserRegisterBindingModel() {
        return this.model;
    }

    public void setUserRegisterBindingModel(UserRegisterBindingModel userRegisterBindingModel) {
        this.model = userRegisterBindingModel;
    }

    public void register() throws IOException {
        if (!this.model.getPassword().equals(this.model.getConfirmPassword())) {
            throw new IllegalArgumentException("Passwords don't match!");
        }
        this.userService.userRegister(this.modelMapper.map(this.model, UserServiceModel.class));
        FacesContext.getCurrentInstance().getExternalContext().redirect("/login");
    }
}