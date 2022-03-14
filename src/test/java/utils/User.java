package utils;

public class User {
    private final String LOGIN;
    private final String PASSWORD;
    private String USERNAME;
    private String ID;

    public User(String LOGIN, String PASSWORD, String USERNAME, String ID) {
        this.LOGIN = LOGIN;
        this.PASSWORD = PASSWORD;
        this.USERNAME = USERNAME;
        this.ID = ID;
    }

    public User(String LOGIN, String PASSWORD) {
        this.LOGIN = LOGIN;
        this.PASSWORD = PASSWORD;
    }

    public String getLOGIN() {
        return LOGIN;
    }

    public String getPASSWORD() {
        return PASSWORD;
    }

    public String getUSERNAME() {
        return USERNAME;
    }

    public String getID() {
        return ID;
    }

    public void setUSERNAME(String USERNAME) {
        this.USERNAME = USERNAME;
    }

    public void setID(String ID) {
        this.ID = ID;
    }
}
