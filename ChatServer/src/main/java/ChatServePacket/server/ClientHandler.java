package ChatServePacket.server;

import ChatServePacket.error.WrongCreditialsExeption;
//import ChatServePacket_DB.AutorazeDb;
import ChatServePacket_DB.DB_Instruction;
import ChatServePacket_DB.Dbworker;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.lang.invoke.WrongMethodTypeException;
import java.net.Socket;
import java.sql.SQLException;

import static ChatServePacket.server.Server.RESEX;

public class ClientHandler {
private static int clientCounter=0;
private Socket socket;
private Server server;
private DataOutputStream out;
private DataInputStream in;
private Thread handlerThead;
private String user;
private int clientNumber;
private Dbworker dbworker;

    public ClientHandler(Socket socket, Server server){
        try {
            this.socket= socket;
            this.server= server;
            this.in= new DataInputStream(socket.getInputStream());
            this.out= new DataOutputStream(socket.getOutputStream());
            this.dbworker= new Dbworker();
            dbworker.connectToDb();
        System.out.println("Handler Thread great");
        this.clientNumber= ++clientCounter;
        } catch (IOException e ){
            e.printStackTrace();
        }

    }

    public String getHandlerByUser() {
        return user;
    }

    public void handle(){
        handlerThead= new Thread(()-> {
            autorize();
            while(!Thread.currentThread().isInterrupted()&& socket.isConnected()){
                try{
                    String message= in.readUTF();

                    handleMessage(message);
                } catch (IOException e){
                    e.printStackTrace();
                }
            }
        });handlerThead.start();
}

    private void handleMessage(String message) {
  var splitMessage= message.split(RESEX);
        switch (splitMessage[0]){
            case "/w": server.privateMessage(this.user,
                    splitMessage[1], splitMessage[2],this);
        break;
            case "/broadcast": server.broadCastMessage(user,splitMessage[1]);
        break;
            case "/new":
                //if(splitMessage[1]!=null&&splitMessage[2]!=null&&splitMessage[3]!=null&&splitMessage[4]!=null)
                {
                try {
                    dbworker.addnewUser(splitMessage[1],splitMessage[2],splitMessage[3],splitMessage[4]);
                    send("/new" + RESEX+ " new Client add");
                } catch (SQLException e) {
                    send("/error"+RESEX+ "ALL TEXT FIELD NOT NULL");
                  //  throw new RuntimeException(e);
                }
            }

    break;
        }
    }
    public void autorize(){
       System.out.print("Autorizing ");
        while(true){
            try {
                var message= in.readUTF();
                if(message.startsWith("/auth")){
                    var parsedautrizeessage = message.split(RESEX);
                    var respponse= "";
                    String nickName= null;
                    try {
                        nickName= dbworker.getContactsLogin(parsedautrizeessage[1],parsedautrizeessage[2]);
//                       nickName = server.getAutoServise().autoriseUserByLoginByPassword(parsedautrizeessage[1],
//                         parsedautrizeessage[2]);

                    } catch (WrongCreditialsExeption e) {
                        respponse= "/ChatServePacket/error" + RESEX + e.getMessage();
                    System.out.print("Wrong creditials nick " + parsedautrizeessage[1]);
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                    if(server.isNickBuzy(nickName)){
                        respponse= "/ChatServePacket/error" + RESEX + "this nick is already connected";
                    System.out.print("Nick is busy " + nickName );
                    }
                    if(!respponse.equals("")|nickName==null)
                    {send(respponse);}
                        else{
                this.user= nickName;
                server.addAutorizedClientToList(this);
                send("/Auto_ok" + RESEX + nickName );
    break;}
                           }
            } catch (IOException e) {

            }

        }
}
public void send(String message){
        try {
            out.writeUTF(message);//System.out.println("send messege ClientHendler");
             }
catch (IOException e){
            e.printStackTrace();
}
}
public Thread getHandlerThead(){return handlerThead;}
    public String getUserNick() {
    return this.user;
    }
}


