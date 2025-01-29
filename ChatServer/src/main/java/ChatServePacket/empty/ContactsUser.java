package ChatServePacket.empty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContactsUser {
    private int nomber;
    private String login;
    String password;
    private String nick;
    private String secret;

    public String toString() {
        return "Contact - ( id = " + nomber + " log = " + login + " password = " + password + " nick = " + nick + ")\n ";
    }
}
