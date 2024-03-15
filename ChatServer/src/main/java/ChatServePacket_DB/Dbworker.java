package ChatServePacket_DB;

import ChatServePacket.empty.ContactsUser;

import java.sql.*;
import java.util.Objects;

public class Dbworker {
    protected static final String getContactsLogin= "select login, password, nick from Contacts;";
private static final String addnewUser= "insert into Contacts(login, password, nick,secret) values(?,?,?,?);";
    private Statement statement;
    private PreparedStatement ps;
    private static final String DbConnectionSt = "jdbc:sqlite:ChatServer/src/main/java/ChatServePacket_DB/dbChatServer.db";
private Connection connection;
    static String selectAll= "select * from Contacts;";


    public void connectToDb() {
        DB_Instruction dbInstruction= new DB_Instruction();
        try { connection = DriverManager.getConnection(DbConnectionSt);
            //Statement statement= dbInstruction.getConnection().createStatement();
            statement= connection.createStatement();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            dbInstruction.disconnect();
        }
    }
    public String getContactsLogin(String s, String pas) throws SQLException{
        String o= null;
        try (var user = statement.executeQuery(getContactsLogin) ){
            while (user.next())
            {
                String log = user.getString("login");
                var p = user.getString("password");
                if ((Objects.equals(log, s))&&(pas.equals(p))){
                    return o = user.getString("nick");
                }
            } return null;
        }
    }
    public void addnewUser(String l, String p,String n,String s) throws SQLException{
ps = connection.prepareStatement(addnewUser);
ps.setString(1, l);
    ps.setString(2,p);
    ps.setString(3,n);
 ps.setString(4,s);
    ps.executeUpdate();
    }

}
        //    dbInstruction.greatTable();

//          dbInstruction.addUserLoginPassNickSecret("log2", "2222", "contact2","bobik2");

//            ResultSet resultSet = statement.executeQuery(selectAll);
// while (resultSet.next()){
//     int id = resultSet.getInt(1);
//     String login = resultSet.getString(2);
//     String pass = resultSet.getString(3);
//     String nick = resultSet.getString(4);
//contactsUser.setNomber(id);
//contactsUser.setLogin(login);
//contactsUser.setPassword(pass);
//contactsUser.setNick(nick);
//System.out.print(contactsUser);
//     //System.out.print(id + login + pass);
// }
////statement.executeQuery("drop table Contacts");
////            statement.execute("alter table Contacts alter column isconnected integer;");
// System.out.print(dbInstruction.getContactsLogin("log2", "2222"));
//// dbInstruction.getContactsLogin("log1", 1111);
//    public static void main(String... args){
////        User user= new User();
//        ContactsUser contactsUser= new ContactsUser();
////        connectToDb();
//    }
