package sample;


import Dao.CurrentStockDao;
import Dao.UsersDao;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import tables.CurrentStockEntity;
import tables.UsersEntity;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;


public  class SampleController implements Initializable {


    @FXML
    private AnchorPane rootPane;

    @FXML
    private TextField usernameField;

    @FXML
    private TextField passwordField;

    @FXML
    public Label label;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {

    }
    @FXML
    public void Switch (javafx.event.ActionEvent event) throws IOException
    {

        Parent AdminView = FXMLLoader.load(getClass().getResource("Admin.fxml"));
        Scene Admin1 = new Scene(AdminView);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(Admin1);
        window.show();

    }





    @FXML
    public void LoginValidation(javafx.event.ActionEvent event) throws IOException
    {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();

        String username = usernameField.getText();
        String password = passwordField.getText();
        UsersDao usersDao = new UsersDao();
        UsersEntity result = null;
        result = usersDao.getUserByUsername(username);
        usersDao.setLoggedid(result.getUserId());





        if(result!=null) {


            if (username.equals(result.getUsername())) {
                if (password.equals(result.getPassword())) {
                    if (result.getPrivilegeId() == 1) {
                        Parent AdminView = FXMLLoader.load(getClass().getResource("Admin.fxml"));
                        Scene Admin1 = new Scene(AdminView);
                        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        window.setScene(Admin1);
                        window.show();
                    }
                    else if (result.getPrivilegeId() == 2) {
                        Parent HostView = FXMLLoader.load(getClass().getResource("Host.fxml"));
                        Scene Host = new Scene(HostView);
                        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        window.setScene(Host);
                        window.show();
                    }
                    else if(result.getPrivilegeId() == 3) {


                        Parent Winemk = FXMLLoader.load(getClass().getResource("Winemaker.fxml"));
                        Scene Wine = new Scene(Winemk);
                        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
                        window.setScene(Wine);
                        window.show();

                    }
                }
                else
                {
                    label.setText("Invalid Username/Password");
                }
            }
            else
            {
                label.setText("Invalid Username/Password");
            }
        }
        else

            label.setText("Invalid Username/Password");

        {






        }
    }




}