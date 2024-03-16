package NetWork.HistoryServise;

import java.io.*;

public class greatHistory {
    static final String RESEX = "%!%";

    public static void main(String... args) throws IOException {
//        greatFileHistory();
    }//C:\Users\Artem\Chat\ChatClient\src\main\java\NetWork\HistoryServise\greatHistory.java

    public void greatFileHistory() throws IOException {
        File history = new File("ChatClient/src/main/java/NetWork/HistoryServise/History.txt");
        if (!history.exists()) {
            history.createNewFile();
        }
    }

    public void reedAndWriteInTxtFile(String user, String message) throws IOException {
        greatFileHistory();
//        try (var bufread= new BufferedReader(new FileReader("ChatClient/src/main/java/NetWork/HistoryServise/History.txt"))){
//            if (bufread.readLine().endsWith(RESEX)){
        try (var fileWriter = new FileWriter("ChatClient/src/main/java/NetWork/HistoryServise/History.txt", true)) {
            fileWriter.write(user + " " + message);
//                }
//            }
        }
    }

    public String reedHistoryUser() {
        String s = " History is null " ;
        try (var boofer = new BufferedReader(new FileReader("ChatClient/src/main/java/NetWork/HistoryServise/History.txt"))) {
            while (!boofer.readLine().isEmpty()) {
              return boofer.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
  return s;
    }
}