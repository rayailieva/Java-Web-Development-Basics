package app.web.beans;

import app.domain.models.service.ChannelServiceModel;
import app.domain.models.service.UserServiceModel;
import app.domain.models.view.ChannelDetailsViewModel;
import app.service.ChannelService;
import app.service.UserService;
import org.modelmapper.ModelMapper;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Named
@RequestScoped
public class ChannelDetailsBean {

    private ChannelDetailsViewModel channelDetailsViewModel;

    private ChannelService channelService;
    private UserService userService;
    private ModelMapper modelMapper;

    public ChannelDetailsBean() {
    }

    @Inject
    public ChannelDetailsBean(ChannelService channelService, UserService userService, ModelMapper modelMapper) {
        this.channelService = channelService;
        this.userService = userService;
        this.modelMapper = modelMapper;
        this.initModel();
    }

    private void initModel() {
        String id = FacesContext.getCurrentInstance()
                .getExternalContext()
                .getRequestParameterMap().get("id");

        ChannelServiceModel channelServiceModel =
                this.channelService.findById(id);

        if (channelServiceModel == null) {
            throw new IllegalArgumentException("Something went wrong!");
        }

        this.channelDetailsViewModel = this.modelMapper
                .map(channelServiceModel, ChannelDetailsViewModel.class);
    }

    public ChannelDetailsViewModel getChannelDetailsViewModel() {
        return this.channelDetailsViewModel;
    }

    public void setChannelDetailsViewModel(ChannelDetailsViewModel channelDetailsViewModel) {
        this.channelDetailsViewModel = channelDetailsViewModel;
    }

        public void unfollow(String id) throws IOException {
            UserServiceModel loggedInUser =
                    this.userService
                            .getUserById((String) ((HttpSession) FacesContext
                                    .getCurrentInstance()
                                    .getExternalContext()
                                    .getSession(true))
                                    .getAttribute("userId"));


            ChannelServiceModel channelServiceModel =
                    this.channelService.findById(id);

            loggedInUser.getFollowedChannels().remove(channelServiceModel);
            channelServiceModel.getFollowers().remove(loggedInUser);

            this.userService.delete(channelServiceModel.getId());

            if (!this.channelService.addUser(channelServiceModel)) {
                throw new IllegalArgumentException("Something went wrong!");
            }


            FacesContext.getCurrentInstance().getExternalContext().redirect("/home");
    }
}
