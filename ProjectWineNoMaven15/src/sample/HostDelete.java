package sample;

import Dao.HibernateUtil;
import Dao.UsersDao;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import jdk.jshell.spi.ExecutionControl;
import org.hibernate.Session;
import org.hibernate.query.Query;
import tables.UsersEntity;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HostDelete implements Initializable {

    @FXML
    public TextField HostDelUsername;


    @FXML
    public Label DelLabel;


    public void DeleteHostOnAction(javafx.event.ActionEvent event) throws IOException
    {

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            DelLabel.setText("");
            UsersDao usersDao = new UsersDao();
            UsersEntity result = usersDao.getUserByUsername(HostDelUsername.getText());

            usersDao.DeleteUser(result);
            Parent AdminView = FXMLLoader.load(getClass().getResource("Admin.fxml"));
            Scene Admin1 = new Scene(AdminView);
            Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
            window.setScene(Admin1);
            window.show();


        }
        catch(Exception e) {
            DelLabel.setText("User not found.");

            Parent AdminView = FXMLLoader.load(getClass().getResource("Admin.fxml"));
            Scene Admin1 = new Scene(AdminView);
            Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
            window.setScene(Admin1);
            window.show();
        }



    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        DelLabel.setText("");
    }



}
