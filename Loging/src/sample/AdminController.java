package sample;



import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;




public  class AdminController implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void RegHost (ActionEvent event) throws IOException {
        Parent HostReg = FXMLLoader.load(getClass().getResource("Host_Registration.fxml"));
        Scene Host1 = new Scene(HostReg);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(Host1);
        window.show();
    }

    public void RegWineMaker (ActionEvent event) throws IOException {
        Parent WineReg = FXMLLoader.load(getClass().getResource("Winemaker_Registration.fxml"));
        Scene Wine1 = new Scene(WineReg);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(Wine1);
        window.show();
    }

    public void viewHost (ActionEvent event) throws IOException{
        Parent Host = FXMLLoader.load(getClass().getResource("Host.fxml"));
        Scene H1 = new Scene(Host);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(H1);
        window.show();

    }





}
