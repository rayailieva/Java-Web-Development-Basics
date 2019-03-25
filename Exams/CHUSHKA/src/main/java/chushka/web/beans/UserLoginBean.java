package chushka.web.beans;

import chushka.domain.models.binding.UserLoginBindingModel;
import chushka.domain.models.service.UserServiceModel;
import chushka.service.UserService;
import org.modelmapper.ModelMapper;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Named
@RequestScoped
public class UserLoginBean {

    private UserLoginBindingModel userLoginBindingModel;

    private UserService userService;
    private ModelMapper modelMapper;

    public UserLoginBean() {
    }

    @Inject
    public UserLoginBean(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
        this.userLoginBindingModel = new UserLoginBindingModel();
    }

    public UserLoginBindingModel getUserLoginBindingModel() {
        return this.userLoginBindingModel;
    }

    public void setUserLoginBindingModel(UserLoginBindingModel userLoginBindingModel) {
        this.userLoginBindingModel = userLoginBindingModel;
    }

    public void login() throws IOException {
        UserServiceModel userServiceModel =
                this.userService.userLogin(this.modelMapper.map(this.userLoginBindingModel, UserServiceModel.class));

        if(userServiceModel == null){
            FacesContext.getCurrentInstance().getExternalContext().redirect("/faces/view/login.xhtml");
            return;
        }

        HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
                .getExternalContext()
                .getSession(false);

        session.setAttribute("username", userServiceModel.getUsername());
        session.setAttribute("role", userServiceModel.getRole());
        session.setAttribute("userId", userServiceModel.getId());

        FacesContext.getCurrentInstance().getExternalContext().redirect("/faces/view/home.xhtml");
    }
}

