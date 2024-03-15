package ChatServePacket.empty;

public class ContactsUser {
    private int nomber;
    private String login;
    String password;
    private String nick;
    private String secret;
public ContactsUser(){

}
    public ContactsUser(int nomber, String login, String password, String nick, String secret) {
        this.nomber = nomber;
        this.login = login;
        this.password = password;
        this.nick = nick;
        this.secret = secret;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public void setNomber(int nomber) {
        this.nomber = nomber;
    }

    public int getNomber() {
        return nomber;
    }

    public String getLogin() {
        return login;
    }

    public String    getPassword() {
        return password;
    }

    public String getNick() {
        return nick;
    }

    public String getSecret() {
        return secret;
    }
    public String toString(){
return  "Contact - ( id = " + nomber+ " log = " +login+ " password = " + password +" nick = "+ nick+ ")\n " ;
    }
}
