package Controllers;
import java.io.*;
import java.io.File;
import java.io.FileNotFoundException;
import Dao.AES;
import Dao.CurrentStockDao;
import Dao.UsersDao;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import org.apache.log4j.Logger;
import tables.CurrentStockEntity;
import tables.UsersEntity;
import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;

public class LogsController implements Initializable {

    @FXML
    TextArea ShowLogs;

    @FXML
    public void Switch(javafx.event.ActionEvent event) throws IOException {
        Parent AdminView = FXMLLoader.load(getClass().getResource("/Scenes/Admin.fxml"));
        Scene Admin1 = new Scene(AdminView);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(Admin1);
        window.show();
    }





   public void initialize(URL url, ResourceBundle resourceBundle) {

        try {
            Scanner s = new Scanner(new File("D:/log.out"));
            while (s.hasNextLine()) {
                ShowLogs.appendText(s.nextLine()+"\n");
            }
                s.close();


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
