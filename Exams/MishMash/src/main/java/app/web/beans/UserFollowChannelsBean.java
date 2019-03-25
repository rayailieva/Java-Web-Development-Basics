package app.web.beans;

import app.domain.models.service.ChannelServiceModel;
import app.domain.models.service.UserServiceModel;
import app.domain.models.view.FollowingChannelsViewModel;
import app.service.ChannelService;
import app.service.UserService;
import org.modelmapper.ModelMapper;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Named
@RequestScoped
public class UserFollowChannelsBean {

    private List<FollowingChannelsViewModel> models;

    private UserService userService;
    private ChannelService channelService;
    private ModelMapper modelMapper;
    
    public UserFollowChannelsBean(){}

    @Inject
    public UserFollowChannelsBean(UserService userService, ChannelService channelService, ModelMapper modelMapper) {
        this.userService = userService;
        this.channelService = channelService;
        this.modelMapper = modelMapper;
        this.initModels();
    }

    private void initModels() {
        String id = (String) ((HttpSession) FacesContext
                .getCurrentInstance()
                .getExternalContext()
                .getSession(false))
                .getAttribute("userId");

        this.models = this.userService.getUserById(id)
                .getFollowedChannels()
                .stream()
                .map(f -> this.modelMapper.map(f, FollowingChannelsViewModel.class))
                .collect(Collectors.toList());
    }

    public List<FollowingChannelsViewModel> getModels() {
        return this.models;
    }

    public void setModels(List<FollowingChannelsViewModel> models) {
        this.models = models;
    }


}
