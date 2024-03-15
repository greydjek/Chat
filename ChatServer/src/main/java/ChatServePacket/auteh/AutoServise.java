package ChatServePacket.auteh;
import ChatServePacket.empty.User;
public interface AutoServise {
    void start();
    void stop();
    String autoriseUserByLoginByPassword(String login, String password);
    String changeNick(String login, String newNick);
    User greatNewUser(String login, String passvord, String nickName);
    void deleteUser(String login);
    void changePassword(String login, String OldPassword, String newPassword);
    void resetPassword(String login, String newPass, String secret);
}
