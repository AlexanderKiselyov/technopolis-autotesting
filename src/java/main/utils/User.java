package utils;

public class User {
    private final String login;
    private final String password;
    private String username;
    private String id;

    public User(String login, String password, String username, String id) {
        this.login = login;
        this.password = password;
        this.username = username;
        this.id = id;
    }

    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public String getID() {
        return id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setID(String id) {
        this.id = id;
    }
}
