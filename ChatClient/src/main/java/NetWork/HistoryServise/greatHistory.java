package NetWork.HistoryServise;

import lesson3java2.MainChatController;
import Error.ErrorClient;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;


public class greatHistory {
    static final String RESEX = "%!%";
    MainChatController mainChatController;

    public static void main(String... args) throws IOException {

    }
    public void greatFileHistory() throws ErrorClient, IOException {
        File history = new File("ChatClient/src/main/java/NetWork/HistoryServise/History.txt");
        if (!history.exists()) {
                   history.createNewFile();
             {
                 throw new ErrorClient("не возможно создать файл истории по указанному пути");
            }
        }
    }

    public void readAndWriteInTxtFile(String user, String message) throws IOException {
        greatFileHistory();
        try (var fileWriter = new FileWriter("ChatClient/src/main/java/NetWork/HistoryServise/History.txt", true)) {
            fileWriter.write(user + " " + message);

        }
    }

    public ArrayList readHistoryUser() throws IOException {
        ArrayList<String> listHistory = new ArrayList<>();
        String s;
        try (var boofer = new BufferedReader(new FileReader("ChatClient/src/main/java/NetWork/HistoryServise/History.txt"))) {
            while ( (s= boofer.readLine())!=null) {
//             s =  boofer.readLine();
//            listHistory.add(s);
        Scanner in = new Scanner(boofer);
        while (in.hasNextLine()){
            listHistory.add(in.nextLine());
                }
            }
        }
       return listHistory;
    }
    public ArrayList read100HistoryLines() throws IOException {
        int b = readHistoryUser().size();
        ArrayList<String> abort100History = new ArrayList<>();
        for(int index=0;index<100;index++, b-- ){
            String s = readHistoryUser().get(b-1).toString();
            abort100History.add(s);
        }
        return abort100History;
    }

}