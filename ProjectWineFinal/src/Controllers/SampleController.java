package Controllers;



import Dao.AES;
import Dao.UsersDao;
import Scenes.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import tables.UsersEntity;

import java.io.IOException;
import java.net.URL;
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


        label.setText("");





    }
    static Logger logger = Logger.getLogger(Main.class.getName());



    @FXML
    public void Switch (javafx.event.ActionEvent event) throws IOException
    {

        Parent AdminView = FXMLLoader.load(getClass().getResource("/Scenes/Admin.fxml"));
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
        try {

            result = usersDao.getUserByUsername(username);
            usersDao.setLoggedid(result.getUserId());
        }
        catch (Exception e)
        {
            result = null;
        }




        if(result!=null) {


            if (username.equals(result.getUsername())) {
                if (password.equals(AES.decrypt(result.getPassword(),"Secret"))) {
                    if (result.getPrivilegeId() == 1) {
                        Parent AdminView = FXMLLoader.load(getClass().getResource("/Scenes/Admin.fxml"));
                        Scene Admin1 = new Scene(AdminView);
                        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        window.setScene(Admin1);
                        window.show();
                        PropertyConfigurator.configure("src/Scenes/log4j.properties");
                        logger.info("Admin entered");

                    }
                    else if (result.getPrivilegeId() == 2) {
                        Parent HostView = FXMLLoader.load(getClass().getResource("/Scenes/Host.fxml"));
                        Scene Host = new Scene(HostView);
                        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        window.setScene(Host);
                        window.show();
                        PropertyConfigurator.configure("src/Scenes/log4j.properties");
                        logger.info("Host entered");

                    }
                    else if(result.getPrivilegeId() == 3) {


                        Parent Winemk = FXMLLoader.load(getClass().getResource("/Scenes/Winemaker.fxml"));
                        Scene Wine = new Scene(Winemk);
                        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
                        window.setScene(Wine);
                        window.show();
                        PropertyConfigurator.configure("src/Scenes/log4j.properties");
                        logger.info("Winemaker entered");


                    }
                }
                else
                {
                    label.setText("Invalid Password");
                }
            }
            else
            {
                label.setText("Sorry we couldn't locate a user with that username.");

            }
        }
        else {
            label.setText("Sorry we couldn't locate a user with that username.");

        }







    }




}