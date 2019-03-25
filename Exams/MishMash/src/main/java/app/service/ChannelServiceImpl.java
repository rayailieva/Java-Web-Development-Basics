package app.service;

import app.domain.entities.Channel;
import app.domain.models.service.ChannelServiceModel;
import app.repository.ChannelRepository;
import app.repository.UserRepository;
import org.modelmapper.ModelMapper;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

public class ChannelServiceImpl implements ChannelService {

    private final ChannelRepository channelRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Inject
    public ChannelServiceImpl(ChannelRepository channelRepository, UserRepository userRepository, ModelMapper modelMapper) {
        this.channelRepository = channelRepository;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void createChannel(ChannelServiceModel channelServiceModel) {
        Channel channel =
                this.modelMapper.map(channelServiceModel, Channel.class);

        this.channelRepository.save(channel);
    }

    @Override
    public List<ChannelServiceModel> findAllChannels() {
        return this.channelRepository.findAll()
                .stream()
                .map(channel -> this.modelMapper.map(channel, ChannelServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public ChannelServiceModel findById(String id) {
        Channel channel = this.channelRepository.findById(id);

        if (channel == null) {
            return null;
        }

        return this.modelMapper.map(channel, ChannelServiceModel.class);
    }

    @Override
    public boolean addUser(ChannelServiceModel channelServiceModel) {
        Channel channel = this.channelRepository.update(this.modelMapper
                .map(channelServiceModel, Channel.class));

        if(channel != null){
            return true;
        }
        return false;
    }
}
