package exam.web.beans;

import exam.domain.models.binding.UserLoginBindingModel;
import exam.domain.models.service.UserServiceModel;
import exam.service.UserService;
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

    private UserLoginBindingModel model;

    private UserService userService;
    private ModelMapper modelMapper;

    public UserLoginBean() {
    }

    @Inject
    public UserLoginBean(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
        this.initModel();
    }

    private void initModel() {
        this.model = new UserLoginBindingModel();
    }

    public void login() throws IOException {
        UserServiceModel userServiceModel =
                this.userService.userLogin(this.modelMapper.map(this.model, UserServiceModel.class));

        if(userServiceModel == null){
            FacesContext.getCurrentInstance().getExternalContext().redirect("/login");
            return;
        }

        HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
                .getExternalContext()
                .getSession(false);

        session.setAttribute("username", userServiceModel.getUsername());
        session.setAttribute("userId", userServiceModel.getId());


        FacesContext.getCurrentInstance().getExternalContext().redirect("/home");
    }

    public UserLoginBindingModel getModel() {
        return this.model;
    }

    public void setModel(UserLoginBindingModel model) {
        this.model = model;
    }
}

