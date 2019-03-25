package app.service;

import app.domain.models.service.ChannelServiceModel;

import java.util.List;

public interface ChannelService {

    void createChannel(ChannelServiceModel channelServiceModel);

    List<ChannelServiceModel> findAllChannels();

    ChannelServiceModel findById(String id);

    boolean addUser(ChannelServiceModel channelServiceModel);

}
