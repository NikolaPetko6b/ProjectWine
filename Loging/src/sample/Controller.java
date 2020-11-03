package sample;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.scene.layout.AnchorPane;


public  class Controller implements Initializable {


    @FXML
    private AnchorPane rootPane;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {

    }
    @FXML
    public void Switch (javafx.event.ActionEvent event) throws IOException
    {
            //opens FXML in the same scene
//        AnchorPane pane = FXMLLoader.load(getClass().getResource("Admin.fxml"));
//        rootPane.getChildren().setAll(pane);


       Parent AdminView = FXMLLoader.load(getClass().getResource("Admin.fxml"));
       Scene Admin1 = new Scene(AdminView);
       Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
       window.setScene(Admin1);
       window.show();

    }

    public void Exit(javafx.event.ActionEvent event){

        System.exit(0);

    }

}
