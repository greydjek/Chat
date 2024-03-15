package ChatServePacket_DB;

import java.sql.*;
import java.util.Objects;

public class DB_Instruction {
  //  static AutorazeDb autorazeDb;
private static Connection connection;
static PreparedStatement ps;
private static Statement statement;
    private static final String getContactsNick= "select nick from Contacts;";
    private static final String getContactsPass= "select login, password from Contacts where value(?,?);";
            //"select password from Contacts;";
protected static final String getContactsLogin= "select login, password, nick from Contacts;";
private static final String dropTable = "drop table if exists Contacts;";
private static final String addUser = "insert into Contacts(login, password, nick,secret) values(?,?,?,?);";
private static final String selectUserNickByLoginPassworg = "select nick from Contacts where login= ? and password = ?; ";
    private static final String DbConnectionSt = "jdbc:sqlite:ChatServer/src/main/java/ChatServePacket_DB/dbChatServer.db";
    private static final String greatTable = "create table if not exists Contacts (nomber integer primary key autoincrement," +
            " login text, password text, nick text, secret text ); ";
    public void addUserLoginPassNickSecret(String log,String pass,String nick,String secret)throws SQLException{
ps= connection.prepareStatement(addUser);
        ps.setString(1,log);
        ps.setString(2,pass);
        ps.setString(3,nick);
        ps.setString(4,secret);
        ps.executeUpdate();
    }
    private static void logAndPass(String s,int p)throws SQLException{
       statement.executeUpdate(getContactsPass);

    }
    private static void deleteTable()throws SQLException {
statement.execute(dropTable);
    }
public void greatTable()throws SQLException {
statement.execute(greatTable);
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
    } return "Client is not in Db";
      }
}
 public DB_Instruction(){
        try {
            connection = DriverManager.getConnection(DbConnectionSt);
statement = connection.createStatement();
            //return null;
        } catch (SQLException e){
            e.getErrorCode();
        }
}
public Connection getConnection(){
        return connection;
}
public void disconnect(){
try {
    if(connection!=null){
        connection.close();
    }
}catch (SQLException e ){
    e.getErrorCode();
}
}

//public static void main(String... args){
//    try {
//
//        //dbConnection();
//      //  greatTable();
//       // addUserByLoginAndPassword("log1", 1213254 );
//      // addUserLoginPassNickSecret("log3",3333,"contact1","name my dog Barbosa");
////    deleteTable();
////        autorazeDb.autoriseByLogAndPassInDb("log1", 1111);
////getContactsLogin("log3");
//        }catch (SQLException e ){
//        e.getErrorCode();
//    } finally {
//        disconnect();
//    }
//}
}
