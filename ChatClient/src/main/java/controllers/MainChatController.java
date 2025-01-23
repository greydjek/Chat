package controllers;

import NetWork.HistoryServise.greatHistory;
import NetWork.MessageProcessor;
import NetWork.NetworkService;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MainChatController implements Initializable, MessageProcessor {
    public TextArea privateMessage;
    public VBox HistoryVBox;
    public Button cancelHistory;
    public TextArea HistoryTextAreaField;
    ButtonType buttonError = new ButtonType("ALL TEXT FIELD MAST NOT NULL");
    public HBox mainBox;
    public TextField newLogin;
    public Button addNewUser;
    public TextField newPassword;
    public TextField newNick;
    public TextField newSecret;
    public VBox addNewUs;
    public Button cancel;

    private greatHistory greatHistory;
    private NetworkService netWorkService;
    static final String RESEX = "%!%";
    private String nick;
    public VBox MainChatPannel;
    public TextArea MainControls;
    public ListView ContactList;
    public TextField InputField;
    public Button ButtonSend;
    @FXML
    public VBox loginpanel;
    @FXML
    public TextField loginField;
    @FXML
    public javafx.scene.control.PasswordField PasswordField;

    public boolean ISContactSetdMessage() {
        MultipleSelectionModel<String> iscontact = ContactList.getSelectionModel();
        if (iscontact != null) {
            return true;
        }
        return false;
    }

    public void Connection(ActionEvent actionEvent) {
    }

    public void Disconnect(ActionEvent actionEvent) {
    }

    public void edit1(ActionEvent actionEvent) {
    }

    public void client1(ActionEvent actionEvent) {
        MainControls.setVisible(false);
        mainBox.setVisible(false);
        MainChatPannel.setVisible(false);
        addNewUs.setVisible(true);

    }

    public void SendMessage(ActionEvent actionEvent) {
        var message = InputField.getText();
        //  System.out.println(" (first message Send message MCC)");
        var privatMessage = "";
        var from = ContactList.getSelectionModel().getSelectedItem();
        String namePrivateMessage = RESEX + from + RESEX + message;
        if (message.isBlank()) {
            return;
        }
        if (ContactList.getSelectionModel().getSelectedIndex() > 0 || !ISContactSetdMessage()) {
            privatMessage = "/w" + namePrivateMessage;
            netWorkService.sendMessage(privatMessage);
            System.out.println(" message Send Private message MCC");
        } else {
            netWorkService.sendMessage("/broadcast" + RESEX + message + "\n");
            System.out.println(" message Send message MCC");
        }

        InputField.clear();
    }

    public void Exit(ActionEvent actionEvent) {
        System.exit(1);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.netWorkService = new NetworkService(this);

    }

    @Override
    public void processMessage(String message) {
        Platform.runLater(() -> {
            try {
                joinParsingMessage(message);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    private void joinParsingMessage(String message) throws IOException {
//    System.out.println(" -input Message in MCC-");
        var splitMessage = message.split(RESEX);
//    MainControls.appendText(splitMessage[0]);
        switch (splitMessage[0]) {
            case "/Auto_ok":
                this.nick = splitMessage[0];
                loginpanel.setVisible(false);
                MainControls.setVisible(true);
                MainChatPannel.setVisible(true);
                mainBox.setVisible(true);
                break;
            case "/broadcast":
                //  System.out.println("MainChat controller send message");
                MainControls.appendText(splitMessage[1] + " : " + splitMessage[2]);
                this.greatHistory = new greatHistory();
                greatHistory.readAndWriteInTxtFile(splitMessage[1], splitMessage[2]);

                break;
            case "/cancel":
                addNewUs.setVisible(false);
                MainChatPannel.setVisible(true);
                MainControls.setVisible(true);
                mainBox.setVisible(true);
                break;
            case "/new":
                addNewUs.setVisible(false);
                MainChatPannel.setVisible(true);
                MainControls.setVisible(true);
                mainBox.setVisible(true);
                MainControls.appendText(splitMessage[1]);
                break;
            case "/error":
                showError(splitMessage);
                System.out.println("Get error in" + splitMessage[1]);
                break;
            case "/w":
                System.out.println(" - PRIVATE MESSAGE MCC-");
                privateMessage.appendText(splitMessage[1] + System.lineSeparator());
                break;
            case "/list: ":
                var contacts = new ArrayList<String>();
                contacts.add("ALL");
                for (int i = 1; i < splitMessage.length; i++) {
                    contacts.add(splitMessage[i]);
                }
                ContactList.setItems(FXCollections.observableList(contacts));
                break;
            default:
                MainControls.appendText(splitMessage[0] + System.lineSeparator());
        }
    }

    private static void showError(String[] splitMessage) {
        var alert = new Alert(Alert.AlertType.ERROR, "Is error account " +
                splitMessage[1], ButtonType.OK);
        alert.showAndWait();
    }

    @FXML
    public void sendAut(ActionEvent actionEvent) {
        var login = loginField.getText();
        var password = PasswordField.getText();
        if (login.isBlank() || password.isBlank()) {
            return;
        }
        var message = "/auth" + RESEX + login + RESEX + password;
        if (!netWorkService.isConnected()) {
            try {
                netWorkService.connect();

            } catch (RuntimeException | IOException e) {
                e.printStackTrace();
            }
        }
        netWorkService.sendMessage(message);
    }

    public void setButtonError(ActionEvent actionEvent) {

        // buttonError.
    }

    public void addnew(ActionEvent actionEvent) throws IOException {
        if (!(newLogin.getText() == null || newPassword.getText() == null || newNick.getText() == null || newSecret.getText() == null)) {
//var buttonError = new ButtonType("ALL TEXT FIELD MAST NOT NULL");
            String log = newLogin.getText();
            String newpass = newPassword.getText();
            String newnick = newNick.getText();
            String newSec = newSecret.getText();
            String m = "/new" + RESEX + log + RESEX + newpass + RESEX + newnick + RESEX + newSec;
            netWorkService.sendMessage(m);
            newLogin.clear();
            newPassword.clear();
            newNick.clear();
            newSecret.clear();
        } else {
            netWorkService.sendMessage("/error" + RESEX + "TEXT IS NULL");

        }
    }

    public void toMainMenu(ActionEvent actionEvent) throws IOException {
        String canc = "/cancel" + RESEX;
        joinParsingMessage(canc);
    }

    public void clientHistory(ActionEvent actionEvent) throws IOException {
        this.greatHistory = new greatHistory();
        MainChatPannel.setVisible(false);
        MainControls.setVisible(false);
        mainBox.setVisible(false);
        HistoryVBox.setVisible(true);
        HistoryTextAreaField.appendText(greatHistory.readHistoryUser() + "\n");

    }

    public void cancel(ActionEvent actionEvent) {
        HistoryVBox.setVisible(false);
        MainChatPannel.setVisible(true);
        MainControls.setVisible(true);
        mainBox.setVisible(true);
    }
}
