package app.web.beans;

import app.domain.models.service.ChannelServiceModel;
import app.domain.models.service.UserServiceModel;
import app.domain.models.view.ChannelListViewModel;
import app.service.ChannelService;
import app.service.UserService;
import org.modelmapper.ModelMapper;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Named
@RequestScoped
public class ChannelListBean {

    private List<ChannelListViewModel> channels;

    private ChannelService channelService;
    private UserService userService;
    private ModelMapper modelMapper;

    public ChannelListBean() {
    }

    @Inject
    public ChannelListBean(ChannelService channelService, ModelMapper modelMapper, UserService userService) {
        this.channelService = channelService;
        this.modelMapper = modelMapper;
        this.userService = userService;
        this.initModel();
    }

    private void initModel() {
        String id = (String) ((HttpSession) FacesContext
                .getCurrentInstance()
                .getExternalContext()
                .getSession(false))
                .getAttribute("userId");

        UserServiceModel loggedInUser = this.userService.getUserById(id);

        this.channels = this.channelService.findAllChannels()
                .stream()
                .filter(c ->!loggedInUser.getFollowedChannels()
                        .stream()
                        .map(ChannelServiceModel::getName)
                        .collect(Collectors.toList()).contains(c.getName()))
                .map(c -> this.modelMapper.map(c, ChannelListViewModel.class))
                .collect(Collectors.toList());

    }

    public List<ChannelListViewModel> getChannels() {
        return this.channels;
    }

    public void setChannels(List<ChannelListViewModel> channels) {
        this.channels = channels;
    }

    public void follow(String id) throws IOException {
        UserServiceModel loggedInUser =
                this.userService
                        .getUserById((String) ((HttpSession) FacesContext
                                .getCurrentInstance()
                                .getExternalContext()
                                .getSession(true))
                                .getAttribute("userId"));


        ChannelServiceModel channelServiceModel =
                this.channelService.findById(id);

        loggedInUser.getFollowedChannels().add(channelServiceModel);
        channelServiceModel.getFollowers().add(loggedInUser);

        if (!this.userService.addChannel(loggedInUser)) {
            throw new IllegalArgumentException("Something went wrong!");
        }

        if (!this.channelService.addUser(channelServiceModel)) {
            throw new IllegalArgumentException("Something went wrong!");
        }


        FacesContext.getCurrentInstance().getExternalContext().redirect("/home");

    }
}
