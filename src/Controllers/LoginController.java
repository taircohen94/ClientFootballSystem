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
import java.util.Observable;
import java.util.Observer;

public class LoginController extends Controller implements Observer {


    @FXML
    public TextField txtUserName;
    public TextField txtPassword;
    public RequiredField requiredField1;
    public RequiredField requiredField2;
    public Button loginBtn;
    static final String STYLE_SHEET = "../View/common-styles.css";


    public LoginController() {

    }

    @FXML
    public void submitPressed(ActionEvent actionEvent) {
        requiredField1.eval();
        requiredField2.eval();
        if (!requiredField1.getHasErrors() && !requiredField2.getHasErrors()) {
            String ans = client.login(txtUserName.getText(), txtPassword.getText());
            if(ans != null && ans.equals("Ok")){
                showMainPage();
            }
            else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText(ans);
                alert.showAndWait();
            }
        }
    }

    private void showMainPage() {
        Parent newRoot = null;
        try {
            newRoot = FXMLLoader.load(getClass().getResource("../View/MainPageUI.fxml"));
            newRoot.getStylesheets().add(getClass().getResource(STYLE_SHEET).toExternalForm());
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage primaryStage = (Stage) loginBtn.getScene().getWindow();
        primaryStage.getScene().setRoot(newRoot);
        primaryStage.setResizable(true);
        primaryStage.setMinWidth(700);
        primaryStage.setMinHeight(450);
        primaryStage.setTitle("Football Association System");
    }

    @Override
    public void update(Observable o, Object arg) {
//        if (o.equals(model)) {
//            ArrayList<String> notifications = (ArrayList<String>) arg;
//            for (String req : notifications) {
//                Alert a = new Alert(Alert.AlertType.CONFIRMATION);
//                a.setContentText(req);
//                Optional<ButtonType> option = a.showAndWait();
//                if (ButtonType.OK.equals(option.get())) {
//                    // TODO: 5/26/2020 send notifications to the relevent and create team
//                }
//                RepresentativeFootballAssociation.notificationTeams.remove(req);
//            }
//        }
    }
}
