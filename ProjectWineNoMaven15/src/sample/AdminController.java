package sample;

import Dao.HibernateUtil;
import Dao.UsersDao;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.hibernate.Session;
import org.hibernate.query.Query;
import tables.UsersEntity;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AdminController implements Initializable {


















    @FXML
    public void SwitchHostReg(javafx.event.ActionEvent event)throws IOException
    {
        Parent HostReg = FXMLLoader.load(getClass().getResource("Host_Registration.fxml"));
        Scene HostR = new Scene(HostReg);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(HostR);
        window.show();
    }

    @FXML
    public void SwitchHostDel(javafx.event.ActionEvent event)throws IOException
    {
        Parent HostDel = FXMLLoader.load(getClass().getResource("Host_Delete.fxml"));
        Scene Del = new Scene(HostDel);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(Del);
        window.show();
    }

    @FXML
    public void SwitchWinemakerReg(javafx.event.ActionEvent event)throws IOException
    {
        Parent WinemkReg = FXMLLoader.load(getClass().getResource("Winemaker_Registration.fxml"));
        Scene WineReg = new Scene(WinemkReg);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(WineReg);
        window.show();
    }


    public void SwitchWinemakerDel(javafx.event.ActionEvent event)throws IOException
    {
        Parent WinemkDel = FXMLLoader.load(getClass().getResource("Winemaker_Delete.fxml"));
        Scene WineDel = new Scene(WinemkDel);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(WineDel);
        window.show();
    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
