package casebook.web.beans;

import casebook.domain.models.view.UserFriendsViewModel;
import casebook.service.UserService;
import org.modelmapper.ModelMapper;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Named
@RequestScoped
public class UserFriendsBean {

    private List<UserFriendsViewModel> models;

    private UserService userService;
    private ModelMapper modelMapper;

    public UserFriendsBean(){}

    @Inject
    public UserFriendsBean(UserService userService, ModelMapper modelMapper) {
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

        this.models = this.userService.getUserById(id)
                .getFriends()
                .stream()
                .map(f -> this.modelMapper.map(f, UserFriendsViewModel.class))
                .collect(Collectors.toList());
    }

    public List<UserFriendsViewModel> getModels() {
        return this.models;
    }

    public void setModels(List<UserFriendsViewModel> models) {
        this.models = models;
    }

    public void delete(String id) throws IOException {
        this.userService.delete(id);
        FacesContext.getCurrentInstance().getExternalContext().redirect("/friends");
    }
}
