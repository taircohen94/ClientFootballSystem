package Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class ManagePolicyController extends Controller {

    public RequiredField requiredField1;
    public RequiredField requiredField2;
    public ChoiceBox policyChoiceBox;
    public TextField leagueName;
    public TextField season;
    public ComboBox cmbLeagueType;
    public ComboBox cmbSeasonType;

    @FXML
    public void clickOnAssignGamePolicy(ActionEvent e) {

        requiredField1.eval();
        requiredField2.eval();
        if (!requiredField1.getHasErrors() && !requiredField2.getHasErrors()) {
            String policy = policyChoiceBox.getValue().toString();
            if (policy.equalsIgnoreCase("Regular Schedule Policy") || policy.equalsIgnoreCase("One Round Schedule Policy")) {
                String ans = client.defineGameSchedulingPolicy(policy, leagueName.getText(), season.getText());
                String[] array;
                if (ans != null) {
                    array = ans.split(",");
                    if (array[0].equals("Ok")) {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setContentText("The game schedule policy set successfully!");
                        alert.showAndWait();
                    } else {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setContentText(ans);
                        alert.showAndWait();
                    }
                }
            } else {
                String ans = client.defineScoreTablePolicy(policy, leagueName.getText(), season.getText());
                String[] array;
                if (ans != null) {
                    array = ans.split(",");
                    if (array[0].equals("Ok")) {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setContentText("The score policy set successfully!");
                        alert.showAndWait();
                    } else {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setContentText(ans);
                        alert.showAndWait();
                    }
                }
            }

        }

    }


    public void init() {
        String ans = client.getAllLeagues();
        String[] array;
        if (ans != null) {
            array = ans.split(",");
            if (array[0].equals("Ok")) {
                for (int i = 1; i < array.length; i++) {
                    this.cmbLeagueType.getItems().add(array[i]);
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText(ans);
                alert.showAndWait();
            }
        }

        ans = client.getAllSeasons();
        String[] array1;
        if (ans != null) {
            array1 = ans.split(",");
            if (array1[0].equals("Ok")) {
                for (int i = 1; i < array1.length; i++) {
                    this.cmbSeasonType.getItems().add(array1[i]);
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText(ans);
                alert.showAndWait();
            }
        }
    }

}

