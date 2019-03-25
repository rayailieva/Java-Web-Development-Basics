package app.domain.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "channels")
public class Channel extends BaseEntity {

    private String name;
    private String description;
    private Type type;
    private List<User> followers;

    public Channel(){}

    @Column(name = "name", nullable = false)
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "description", nullable = false)
    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    public Type getType() {
        return this.type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    @ManyToMany(mappedBy = "followedChannels")
    public List<User> getFollowers() {
        return this.followers;
    }

    public void setFollowers(List<User> followers) {
        this.followers = followers;
    }
}
