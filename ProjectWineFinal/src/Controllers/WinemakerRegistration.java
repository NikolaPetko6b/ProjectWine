package Controllers;

import Dao.AES;
import Dao.UsersDao;
import Dao.WineDao;
import Dao.WineTypeDao;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
    public Button button1;
    int loggedid;

    public void setLoggedid(int id){loggedid = id;}

    public int getLoggedid() {
        return loggedid;
    }

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
            String secretkey = "Secret";
            String encryptpass = AES.encrypt(NewWinePass.getText(),secretkey);
            usersEntity.setPassword(encryptpass);
            usersEntity.setUserId(usersDao.getMaxID() + 1);
            usersEntity.setPrivilegeId(3);
            usersEntity.setWarehouseId(1);
            winetypeEntity.setWinetypeId(wineTypeDao.getMaxID()+1);
            winetypeEntity.setWinename(NewWineType.getText());
            winetypeEntity.setWhiteGrapeNeeded(Double.parseDouble(WhiteGrNeeded.getText()));
            winetypeEntity.setRedGrapeNeeded(Double.parseDouble(RedGrNeeded.getText()));
            winetypeEntity.setUserId(usersDao.getMaxID()+1);

            if(!usersEntity.Exist())
            {
                usersDao.AddUser(usersEntity);
                wineTypeDao.AddWineType(winetypeEntity);



            }
            else{
                NewRegLabel.setText("Username taken.");
            }


        } catch (Exception e) {
            NewRegLabel.setText("Username taken.");
        }

    }





}
