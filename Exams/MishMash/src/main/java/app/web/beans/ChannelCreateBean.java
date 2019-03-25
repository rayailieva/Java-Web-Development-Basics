package app.web.beans;

import app.domain.models.binding.ChannelCreateBindingModel;
import app.domain.models.service.ChannelServiceModel;
import app.service.ChannelService;
import org.modelmapper.ModelMapper;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;

@Named
@RequestScoped
public class ChannelCreateBean {

    private ChannelCreateBindingModel channelCreateBindingModel;
    private ChannelService channelService;
    private ModelMapper modelMapper;

    public ChannelCreateBean() {
    }

    @Inject
    public ChannelCreateBean(ChannelService channelService, ModelMapper modelMapper) {
        this.channelService = channelService;
        this.modelMapper = modelMapper;
        this.channelCreateBindingModel = new ChannelCreateBindingModel();
    }

    public ChannelCreateBindingModel getChannelCreateBindingModel() {
        return this.channelCreateBindingModel;
    }

    public void setChannelCreateBindingModel(ChannelCreateBindingModel channelCreateBindingModel) {
        this.channelCreateBindingModel = channelCreateBindingModel;
    }

    public void create() throws IOException {
        ChannelServiceModel channelServiceModel = this.modelMapper
                .map(this.channelCreateBindingModel, ChannelServiceModel.class);

        this.channelService.createChannel(channelServiceModel);

        FacesContext.getCurrentInstance()
                .getExternalContext()
                .redirect("/home");
    }
}
