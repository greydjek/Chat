package ChatServePacket.auteh;

import ChatServePacket.empty.User;
import ChatServePacket.error.WrongCreditialsExeption;

import java.lang.invoke.WrongMethodTypeException;
import java.util.ArrayList;
import java.util.List;

public class InMemoryAutoServis implements AutoServise {
    private List<User> users;

    public InMemoryAutoServis() {
        this.users = new ArrayList<>();
        users.addAll(List.of(new User("log1", "pas", "nickname","secret"),
                new User("log2", "pas", "2nickname","secret"),
                new User("log3", "pas", "3nickname","secret"),
                new User("log4", "pas", "4nickname","secret"),
                new User("log5", "pas", "5nickname","secret")
                ));

    }

    @Override
    public void start() {
        System.out.println("Auto service started");

    }

    @Override
    public void stop() {
        System.out.println("Auto service stop");
    }

    @Override
    public String autoriseUserByLoginByPassword(String login, String password) {
        for(User user:users){
            if(login.equals(user.getLogin())&& password.equals(user.getPassword())){
return user.getNick();
            }
        }
        throw new WrongCreditialsExeption("Login or password is not equals");
    }

    @Override
    public String changeNick(String login, String newNick) {
        return null;
    }

    @Override
    public User greatNewUser(String login, String passvord, String nickName) {
        return null;
    }

    @Override
    public void deleteUser(String login) {

    }

    @Override
    public void changePassword(String login, String OldPassword, String newPassword) {

    }

    @Override
    public void resetPassword(String login, String newPass, String secret) {

    }
}
