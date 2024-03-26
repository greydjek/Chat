package Error;

import java.io.FileNotFoundException;
import java.io.IOException;

public class ErrorClient extends RuntimeException {
    public ErrorClient(String message){
super(message);
    }
}
