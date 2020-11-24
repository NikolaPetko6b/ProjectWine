package sample;

import Dao.UsersDao;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import tables.UsersEntity;

import javax.swing.text.LabelView;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HostRegistration implements Initializable {

    @FXML
    public TextField NewHostUser;


    @FXML
    public TextField NewHostPass;

    @FXML
    public Label HostRegLabel;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        HostRegLabel.setText("");
    }

    @FXML
    public void AddHostOnAction(javafx.event.ActionEvent event) throws IOException
    {

        try {

            UsersDao usersDao = new UsersDao();
            UsersEntity usersEntity = new UsersEntity();
            usersEntity.setUsername(NewHostUser.getText());
            usersEntity.setPassword(NewHostPass.getText());
            usersEntity.setUserId(usersDao.getMaxID() + 1);
            usersEntity.setWarehouseId(1);
            usersEntity.setPrivilegeId(2);
            if(!usersEntity.Exist())
            {
                usersDao.AddUser(usersEntity);
                Parent AdminView = FXMLLoader.load(getClass().getResource("Admin.fxml"));
                Scene Admin1 = new Scene(AdminView);
                Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                window.setScene(Admin1);
                window.show();


            }
            else{
                    HostRegLabel.setText("Username taken.");
            }


        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
