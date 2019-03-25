package app.domain.models.service;

import app.domain.entities.Channel;
import app.domain.entities.User;

import java.util.List;

public class UserServiceModel {

    private String id;
    private String username;
    private String password;
    private String email;
    private List<ChannelServiceModel> followedChannels;

    public UserServiceModel() {
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<ChannelServiceModel> getFollowedChannels() {
        return this.followedChannels;
    }

    public void setFollowedChannels(List<ChannelServiceModel> followedChannels) {
        this.followedChannels = followedChannels;
    }


}
