package app.domain.models.view;

import app.domain.entities.User;

import java.util.List;

public class ChannelListViewModel {

    private String id;
    private String name;
    private String description;

    public ChannelListViewModel() {
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

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
