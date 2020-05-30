package Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController extends Controller {


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
        stage.showAndWait();
        start();
    }

    public void start(){
        new Thread(() -> {
            notificationFromServer();
        }).start();
    }

    private void notificationFromServer() {
        String ans = client.checkNotification();
        String[] array;
        if (ans != null) {
            array = ans.split(",");
            if (array[0].equals("Ok")) {
                showNotification(array);
            }
        }
}

    private void showNotification(String [] notifications) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 1; i < notifications.length; i++) {
            stringBuilder.append(notifications[i]);
            if(i % 3 == 0){
                stringBuilder.append("\n");
            }
        }
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(stringBuilder.toString());
        alert.showAndWait();
    }

}
