package sample;

import Dao.UsersDao;
import Dao.WineDao;
import Dao.WineTypeDao;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import tables.UsersEntity;
import tables.WineEntity;
import tables.WinetypeEntity;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class WinemakerRegistration implements Initializable {


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        NewRegLabel.setText("");
    }


    @FXML
    public javafx.scene.control.TextField NewWineUser;
    @FXML
    public javafx.scene.control.TextField NewWinePass;
    @FXML
    public javafx.scene.control.TextField NewWineType;
    @FXML
    public javafx.scene.control.TextField WhiteGrNeeded;
    @FXML
    public javafx.scene.control.TextField RedGrNeeded;
    @FXML
    public Label NewRegLabel;


    @FXML
    public void AddWinemakerOnAction(javafx.event.ActionEvent event) throws IOException
    {

        try {

            UsersDao usersDao = new UsersDao();
            UsersEntity usersEntity = new UsersEntity();
            WineDao wineDao = new WineDao();
            WinetypeEntity winetypeEntity = new WinetypeEntity();
            WineTypeDao wineTypeDao = new WineTypeDao();
            usersEntity.setUsername(NewWineUser.getText());
            usersEntity.setPassword(NewWinePass.getText());
            usersEntity.setUserId(usersDao.getMaxID() + 1);
            usersEntity.setPrivilegeId(3);
            usersEntity.setWarehouseId(1);
            winetypeEntity.setWinetypeId(wineTypeDao.getMaxID()+1);
            winetypeEntity.setWinename(NewWineType.getText());
            winetypeEntity.setWhiteGrapeNeeded(Integer.parseInt(WhiteGrNeeded.getText()));
            winetypeEntity.setRedGrapeNeeded(Integer.parseInt(RedGrNeeded.getText()));
            winetypeEntity.setUserId(usersDao.getMaxID()+1);

            if(!usersEntity.Exist())
            {
                usersDao.AddUser(usersEntity);
                wineTypeDao.AddWineType(winetypeEntity);
                Parent AdminView = FXMLLoader.load(getClass().getResource("Admin.fxml"));
                Scene Admin1 = new Scene(AdminView);
                Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                window.setScene(Admin1);
                window.show();


            }
            else{
                NewRegLabel.setText("Username taken.");
            }


        } catch (Exception e) {
            NewRegLabel.setText("Username taken.");
        }

    }




}
