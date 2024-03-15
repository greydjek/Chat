package ChatServePacket.empty;

public class User {
    String login;
    String password;
    String nick;
    String secret;
    public User(){}

    public User(String login,String password,String nick, String secret) {
        this.login = login; this.password= password;this.nick=nick;this.secret=secret;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getNick() {
        return nick;
    }

    public String getSecret() {
        return secret;
    }
}
