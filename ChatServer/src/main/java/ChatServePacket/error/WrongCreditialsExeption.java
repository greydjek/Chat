package ChatServePacket.error;

public class WrongCreditialsExeption extends RuntimeException{
    public WrongCreditialsExeption() {
    }

    public WrongCreditialsExeption(String message) {
        super(message);
    }
}
