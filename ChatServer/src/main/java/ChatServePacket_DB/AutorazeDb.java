package ChatServePacket_DB;

import java.sql.SQLException;
import java.sql.Statement;

public class AutorazeDb {
    private static final String DbConnectionSt = "jdbc:sqlite:ChatServer/src/main/java/ChatServePacket_DB/dbChatServer.db";

    private Statement statement;
//    public String autoriseByLogAndPassInDb(String log, int pass)throws SQLException {
//      //  try(var login1 = statement.executeQuery(dbInstruction.getContactsLogin())){
//            while (login1.next()){
//                if(login1.equals(log)){//return log;
//                    statement.execute("select password from Contacts");
//        var l=   statement.execute("select nomber from Contacts where login value (?);");
//        dbInstruction.ps.
//        return String.valueOf(l);
//
//                if(pass==)
//                //}
//            }
//        }
//        return null;
//        if(dbInstruction.getContactsLogin().equals(log)&& dbInstruction.getContactspass().equals(pass)){
//            return true;
//        }
//    return false;
// }
// public static void main(String... args){
//     autoriseByLogAndPassInDb();
// }

}
