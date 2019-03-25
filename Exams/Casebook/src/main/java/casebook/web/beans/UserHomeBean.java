package casebook.web.beans;

import casebook.domain.models.service.UserServiceModel;
import casebook.domain.models.view.UserViewModel;
import casebook.service.UserService;
import org.modelmapper.ModelMapper;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Named
@RequestScoped
public class UserHomeBean {

    private List<UserViewModel> models;

    private UserService userService;
    private ModelMapper modelMapper;

    public UserHomeBean(){}

    @Inject
    public UserHomeBean(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
        this.initModels();
    }

    private void initModels() {
        String id = (String) (((HttpServletRequest) FacesContext
                .getCurrentInstance()
                .getExternalContext()
                .getRequest())
                .getSession())
                .getAttribute("userId");

        String username = (String) (((HttpServletRequest) FacesContext
                .getCurrentInstance()
                .getExternalContext()
                .getRequest())
                .getSession())
                .getAttribute("username");

        UserServiceModel loggedInUser = this.userService.getUserById(id);

        this.models = this.userService
                .listAllUsers()
                .stream()
                .filter(u -> !u.getUsername().equals(username) &&
                                !loggedInUser.getFriends()
                                .stream()
                                .map(UserServiceModel::getUsername)
                                .collect(Collectors.toList()).contains(u.getUsername()))
                .map(u -> this.modelMapper.map(u, UserViewModel.class))
                .collect(Collectors.toList());
    }

    public List<UserViewModel> getModels() {
        return this.models;
    }

    public void setModels(List<UserViewModel> models) {
        this.models = models;
    }

    public void addFriend(String id) throws IOException {
        UserServiceModel loggedInUser =
                this.userService
                        .getUserById((String) ((HttpSession) FacesContext
                                .getCurrentInstance()
                                .getExternalContext()
                                .getSession(true))
                                .getAttribute("userId"));

        UserServiceModel userServiceModel =
                this.userService.getUserById(id);

        loggedInUser.getFriends().add(userServiceModel);
        userServiceModel.getFriends().add(loggedInUser);

        if (!this.userService.addFriend(loggedInUser)) {
            throw new IllegalArgumentException("Something went wrong!");
        }

        if (!this.userService.addFriend(userServiceModel)) {
            throw new IllegalArgumentException("Something went wrong!");
        }


        FacesContext.getCurrentInstance().getExternalContext().redirect("/home");
    }
}
