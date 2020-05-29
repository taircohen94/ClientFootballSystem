
import Client.Client;
import Client.Strategies.ClientSender;
import Controllers.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class Main extends Application {
    static final String RESOURCE = "View/Login2.fxml";
    static final String STYLE_SHEET = "View/common-styles.css";
    static Stage stg;

    @Override
    public void start(Stage stage) throws Exception {
        this.stg = stage;
        FXMLLoader fxmlLoader = new FXMLLoader();
        Parent root = fxmlLoader.load(getClass().getResource(RESOURCE).openStream());
        root.getStylesheets().add(getClass().getResource(STYLE_SHEET).toExternalForm());
        stage.setTitle("Football Association System");
        stage.setScene(new Scene(root, 900, 900));
        stage.setHeight(650);
        stage.show();
        Controller controller = new Controller();
        controller.setClient(connectToServer());
    }

    private static Client connectToServer() {
        Client client = null;
        try {
            client = new Client(
                    InetAddress.getLocalHost(),
                    5400,
                    /*new ClientStrategyCLI()*/
                    new ClientSender()
                    /*new ClientSender()*/
            );
            client.start();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return client;
    }

    public static void main(String[] args) {
        launch(args);
    }

}
