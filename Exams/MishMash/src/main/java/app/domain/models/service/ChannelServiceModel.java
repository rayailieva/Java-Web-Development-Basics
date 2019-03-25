package app.domain.models.service;

import app.domain.entities.Type;
import app.domain.entities.User;

import java.util.List;

public class ChannelServiceModel {

    private String id;
    private String name;
    private String description;
    private Type type;
    private List<UserServiceModel> followers;

    public ChannelServiceModel() {
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Type getType() {
        return this.type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public List<UserServiceModel> getFollowers() {
        return this.followers;
    }

    public void setFollowers(List<UserServiceModel> followers) {
        this.followers = followers;
    }
}
