package app.domain.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
public class User extends BaseEntity {

    private String username;
    private String password;
    private String email;
    private List<Channel> followedChannels;

    public User(){}

    @Column(name = "username", nullable = false)
    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Column(name = "password", nullable = false)
    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name = "email", nullable = false)
    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @ManyToMany()
    @JoinTable(name = "channels_users",
    joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name = "channel_id", referencedColumnName = "id"))
    public List<Channel> getFollowedChannels() {
        return this.followedChannels;
    }

    public void setFollowedChannels(List<Channel> followedChannels) {
        this.followedChannels = followedChannels;
    }


}
