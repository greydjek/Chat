package NetWork;



import app.ExempleAplication;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
@RequiredArgsConstructor
public class NetworkService {
    private static final Logger logger = LogManager.getLogger(NetworkService.class);
    private static final Marker marker2= MarkerManager.getMarker("MARKER2");
    private static final String HOST = "127.0.0.1";
    private static final int PORT = 8189;
    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;
    private MessageProcessor messageProcessor;

    public NetworkService(MessageProcessor messageProcessor) {
        this.messageProcessor = messageProcessor;


    }

    public void connect() throws IOException {
logger.info(marker2, " in Network Servise!!!!!!!!!!!!!!!!!!!!!!!!");
        this.socket = new Socket(HOST, PORT);
        this.in = new DataInputStream(socket.getInputStream());
        this.out = new DataOutputStream(socket.getOutputStream());
        readMessage();

    }

    public boolean isConnected() {
        return socket != null && !socket.isClosed();
    }

    public void readMessage() {
        var thread = new Thread(() ->
        {
            try {
                while (!Thread.currentThread().isInterrupted() && !socket.isClosed()) {
                    var message = in.readUTF();
                    messageProcessor.processMessage(message);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        thread.setDaemon(true);
        thread.start();
    }

    public void sendMessage(String message) {
        try {
//            log.info("hbchbbbbbbbbbbbbbbbbbs!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
            out.writeUTF(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void close() {
        try {
            if (socket != null && !socket.isClosed()) {
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
