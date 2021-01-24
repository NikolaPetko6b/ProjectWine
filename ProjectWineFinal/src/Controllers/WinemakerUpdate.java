package Controllers;

import Dao.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.hibernate.Session;
import tables.UsersEntity;
import tables.WinetypeEntity;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class WinemakerUpdate implements Initializable {


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        UpdateRegLabel.setText("");
    }


    @FXML
    public javafx.scene.control.TextField UpdateUser;
    @FXML
    public javafx.scene.control.TextField UpdatePass;
    @FXML
    public javafx.scene.control.TextField UpdateWineType;
    @FXML
    public javafx.scene.control.TextField UpdateWhiteGrNeeded;
    @FXML
    public javafx.scene.control.TextField UpdateRedGrNeeded;
    @FXML
    public Label UpdateRegLabel;
    @FXML
    public Button updatewinemaker;


    int updateUserid;

    public void setUpdateid(int updateid) {
        this.updateUserid = updateid;
    }

    public int getUpdateid() {
        return updateUserid;
    }

    @FXML
    public void UpdateWinemakerOnAction(javafx.event.ActionEvent event) throws IOException {


        try {
            Stage stage = (Stage) updatewinemaker.getScene().getWindow();
            UsersDao usersDao = new UsersDao();
            UsersEntity usersEntity = new UsersEntity();
            WineDao wineDao = new WineDao();
            WinetypeEntity winetypeEntity = new WinetypeEntity();
            WineTypeDao wineTypeDao = new WineTypeDao();
            usersEntity.setUsername(UpdateUser.getText());
            usersEntity.setPassword(AES.encrypt(UpdatePass.getText(),"Secret"));
            usersEntity.setUserId(updateUserid);
            usersEntity.setPrivilegeId(3);
            usersEntity.setWarehouseId(1);
            winetypeEntity.setWinetypeId(wineTypeDao.getWineTypeByUserID(updateUserid).getWinetypeId());
            winetypeEntity.setUserId(updateUserid);
            winetypeEntity.setWinename(UpdateWineType.getText());
            winetypeEntity.setWhiteGrapeNeeded(Double.parseDouble(UpdateWhiteGrNeeded.getText()));
            winetypeEntity.setRedGrapeNeeded(Double.parseDouble(UpdateRedGrNeeded.getText()));
            wineTypeDao.UpdateWineType(winetypeEntity);
            usersDao.UpdateUser(usersEntity);
            stage.close();

        } catch (Exception ex) {
            UpdateRegLabel.setText("Username taken.");
        }


    }



    public void DeleteWinemakerOnAction(javafx.event.ActionEvent event) throws IOException {

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Stage stage = (Stage) updatewinemaker.getScene().getWindow();
            UsersDao usersDao = new UsersDao();
            UsersEntity result1 = usersDao.getUserByUsername(UpdateUser.getText());
            WineTypeDao wineTypeDao = new WineTypeDao();
            WinetypeEntity result2 = wineTypeDao.getWineTypeByUserID(result1.getUserId());
            System.out.println(result2.getUserId());
            wineTypeDao.DeleteWineType(result2);
            usersDao.DeleteUser(result1);

            Parent AdminView = FXMLLoader.load(getClass().getResource("/Scenes/Admin.fxml"));
            Scene Admin1 = new Scene(AdminView);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(Admin1);
            stage.close();


        } catch (Exception e) {


        }
    }
}