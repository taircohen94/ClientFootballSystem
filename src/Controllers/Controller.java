package Controllers;
import Client.Client;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;
import java.io.IOException;

public class Controller {


    public static Client client;
    static final String STYLE_SHEET = "../View/common-styles.css";


    public void stillWorkingOnIt() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setContentText("Ops.. Our system is still working on it \n" +
                " please try soon!");
        alert.showAndWait();
    }

    public void raiseAlert(RecordException e1){
        Alert alert;
        alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText(e1.getErrorMessage());
        alert.showAndWait();
    }

    public Stage getStage(FXMLLoader loader, Button button) {
        Stage stage = new Stage();
        stage.initOwner(button.getScene().getWindow());
        try {
            Scene scene = new Scene(loader.load());
            scene.getStylesheets().add(getClass().getResource(STYLE_SHEET).toExternalForm());
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stage;
    }

    public void initGameIdCB(ComboBox comboBox){
//        GameDB gameDB = FootballSystem.getInstance().getGameDB();
//        if(gameDB != null){
//            HashMap<Integer, Game> gameHashMap = gameDB.getAllGames();
//            if(gameHashMap != null && gameHashMap.size() > 0){
//                Set<Integer> gameSet = gameHashMap.keySet();
//                for (Integer gameName : gameSet){
//                    comboBox.getItems().add(gameName);
//                }
//            }
//            else{
//                raiseAlert(new RecordException("There is not games at the DB"));
//            }
//        }
//        else{
//            raiseAlert(new RecordException("games DB is not exits"));
//        }
//    }

    }

    public void setClient(Client connectToServer) {
        this.client = connectToServer;
    }
}
