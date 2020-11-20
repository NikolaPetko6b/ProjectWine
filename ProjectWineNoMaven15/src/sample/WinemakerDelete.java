package sample;

import Dao.HibernateUtil;
import Dao.UsersDao;
import Dao.WineTypeDao;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.hibernate.Session;
import tables.UsersEntity;
import tables.WinetypeEntity;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class WinemakerDelete implements Initializable {

    @FXML
    public Label WinemakerDelLabel;
    @FXML
    public TextField WinemakerDelUsername;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        WinemakerDelLabel.setText("");
    }


    public void DeleteWinemakerOnAction(javafx.event.ActionEvent event) throws IOException {

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            UsersDao usersDao = new UsersDao();
            UsersEntity result1 = usersDao.getUserByUsername(WinemakerDelUsername.getText());
            WineTypeDao wineTypeDao = new WineTypeDao();
            WinetypeEntity result2 = wineTypeDao.getWineTypeByUserID(result1.getUserId());
            System.out.println(result2.getUserId());
            wineTypeDao.DeleteWineType(result2);
            usersDao.DeleteUser(result1);

            Parent AdminView = FXMLLoader.load(getClass().getResource("Admin.fxml"));
            Scene Admin1 = new Scene(AdminView);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(Admin1);
            window.show();


        } catch (Exception e) {
            WinemakerDelLabel.setText("User not found.");

        }
    }
}
