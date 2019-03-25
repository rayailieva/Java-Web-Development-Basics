package casebook.domain.models.view;

public class UserProfileViewModel {

    private String username;
    private String gender;

    public UserProfileViewModel() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
