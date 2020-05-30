package Controllers;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sun.awt.windows.ThemeReader;

import java.io.IOException;
import java.util.concurrent.*;

public class LoginController extends Controller{


    @FXML
    public TextField txtUserName;
    public TextField txtPassword;
    public RequiredField requiredField1;
    public RequiredField requiredField2;
    public Button loginBtn;
    static final String STYLE_SHEET = "../View/common-styles.css";
    MainPageController controller;

    public LoginController() {

    }

    @FXML
    public void submitPressed(ActionEvent actionEvent) {
        requiredField1.eval();
        requiredField2.eval();
        if (!requiredField1.getHasErrors() && !requiredField2.getHasErrors()) {
            String ans = client.login(txtUserName.getText(), txtPassword.getText());
            String[] array;
            if (ans != null) {
                array = ans.split(",");
                if (array[0].equals("Ok")) {
                    showMainPage(array[1]);
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText(ans);
                    alert.showAndWait();
                }
            }
        }
    }

//    private void showMainPage(String fanByUserName) {
//        Parent newRoot = null;
//        try {
//            FXMLLoader loader = new FXMLLoader(getClass().getResource("../View/MainPageUI.fxml"));
//            newRoot = FXMLLoader.load(getClass().getResource("../View/MainPageUI.fxml"));
//            newRoot.getStylesheets().add(getClass().getResource(STYLE_SHEET).toExternalForm());
//            controller = loader.getController();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        Stage primaryStage = (Stage) loginBtn.getScene().getWindow();
//        primaryStage.getScene().setRoot(newRoot);
//        primaryStage.setResizable(true);
//        primaryStage.setMinWidth(700);
//        primaryStage.setMinHeight(450);
//        primaryStage.setTitle("Football Association System");
//        controller.init(fanByUserName);
//    }

    private void showMainPage(String fanByUserName) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../View/MainPageUI.fxml"));
        Stage stage = getStage(loader, loginBtn);
        stage.setTitle("Football Association System");
        controller = loader.getController();
        controller.init(fanByUserName);
        // showAndWait will block execution until the window closes...
        start();
        stage.showAndWait();
    }

    private void start(){
        new Thread() {
            public void run() {
                //Do some stuff in another thread
                Platform.runLater(new Runnable() {
                    public void run() {
                        StringBuilder ans = notificationFromServer();
                        if(ans != null) {
                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setContentText(ans.toString());
                            alert.showAndWait();
                        }
                    }
                });
            }
        }.start();


//        new java.util.Timer().schedule(
//                new java.util.TimerTask() {
//                    @Override
//                    public void run() {
//                        System.out.println("Does it work?");
//                        notificationFromServer();
//                        System.out.println("Nope, it doesnt...again.");
//
//                    }
//                },
//                5000
//        );
//        Thread one = new Thread(() -> {
//            try {
//                while(true) {
//                    System.out.println("Does it work?");
//                    notificationFromServer();
//                    Thread.sleep(1000000);
//                    System.out.println("Nope, it doesnt...again.");
//                }
//            } catch(InterruptedException v) {
//                System.out.println(v);
//            }
//        });
//        one.start();

//        new Thread(() -> {
//            notificationFromServer();
//        }).start();
    }

    private StringBuilder notificationFromServer() {
        System.out.println("notificationFromServer");
        String ans = client.checkNotification();
        System.out.println("answer is: "+ ans);
        String[] array;
        if (ans != null) {
            array = ans.split(",");
            if (array[0].equals("Ok")) {
                return showNotification(array);
            }
        }
        return null;
    }

    private StringBuilder showNotification(String [] notifications) {
        StringBuilder stringBuilder = new StringBuilder();
        int count = 0;
        for (int i = 1; i < notifications.length; i++) {
            count++;
            if(count == 4){
                count = 0;
                continue;
            }
            stringBuilder.append(notifications[i]);
            stringBuilder.append(" ");
                if(i % 3 == 0){
                stringBuilder.append("\n");
            }
        }
        return stringBuilder;
    }

}
