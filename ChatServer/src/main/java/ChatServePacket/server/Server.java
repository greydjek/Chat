package ChatServePacket.server;

import ChatServePacket.auteh.AutoServise;
import ChatServePacket.auteh.InMemoryAutoServis;
import ChatServePacket_DB.DB_Instruction;
import ChatServePacket_DB.Dbworker;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;

public class Server {
    private static final int PORT = 8189;
    protected static final String RESEX = "%!%";
    private AutoServise autoServise;
    private List<ClientHandler> clientHandlers;
    private Dbworker dbworker;
    private DB_Instruction dbInstruction;

    public Server(AutoServise autoServise) {
        this.clientHandlers = new ArrayList<>();
        this.autoServise = autoServise;
    }

    public void start() {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Server start");
            dbworker = new Dbworker();
            dbworker.connectToDb();
            while (true) {
                System.out.println("Waiting for connection...");
                Socket socket = serverSocket.accept();
                System.out.println("Client connect");
                ClientHandler clientHandler = new ClientHandler(socket, this);
                clientHandler.handle();
                if (socket.isClosed()) {
                    start();
                    dbInstruction.disconnect();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            autoServise.stop();
            shotDawn();
        }
    }

    public void privateMessage(String from, String foruser, String message, ClientHandler clientHandler) {
        var handler = getHendlerByUser(foruser);//System.out.print("111private message try send from server");
        if (handler == null) {
            clientHandler.send("error user is not found!!!");
        } else {
            message = "/w" + RESEX + " Private " + from + "  --->> " + foruser + " " + message;
            handler.send(message);
            clientHandler.send(message);
        }
    }

    public void broadCastMessage(String from, String message) {
        message = "/broadcast" + RESEX + from + RESEX + message;
        for (ClientHandler clientHandler : clientHandlers) {
            clientHandler.send(message);
        }
    }

    public synchronized void addAutorizedClientToList(ClientHandler clientHandler) {
        clientHandlers.add(clientHandler);
        sendOnLineClient();
    }

    public synchronized void removeAutorizedClientToList(ClientHandler clientHandler) {
        clientHandlers.remove(clientHandler);
        sendOnLineClient();
    }

    private void shotDawn() {

    }

    public void sendOnLineClient() {
        var sb = new StringBuilder("/list: ");
        sb.append(RESEX);
        for (ClientHandler clientHandler : clientHandlers) {
            sb.append(clientHandler.getUserNick());
            sb.append(RESEX);
        }
        var message = sb.toString();
        for (ClientHandler clientHandler : clientHandlers) {
            clientHandler.send(message);
        }
    }

    public synchronized boolean isNickBuzy(String nick) {
        for (ClientHandler clientHandler : clientHandlers) {
            if (clientHandler.getUserNick().equals(nick)) {
                return true;
            }
        }
        return false;
    }

    private ClientHandler getHendlerByUser(String userName) {
        for (ClientHandler clientHandler : clientHandlers) {
            if (clientHandler.getUserNick().equals(userName)) {
                return clientHandler;
            }
        }
        return null;
    }

    public AutoServise getAutoServise() {
        return autoServise;
    }
}