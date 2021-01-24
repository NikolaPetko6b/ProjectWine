package Controllers;

import Dao.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import org.hibernate.Session;
import tables.UsersEntity;
import tables.WinetypeEntity;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AdminController implements Initializable {



    @FXML
    public ListView winemakers;


    @FXML
    public Label UpdateLabel;





    @FXML
    public void StockInfoOnAction(javafx.event.ActionEvent event)throws IOException
    {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/Scenes/Host.fxml"));
        Parent root = (Parent) loader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Stock Manager");
        stage.show();
    }


    @FXML
    public void RefreshListView()
    {
        winemakers.getItems().clear();
        PopulateListView();
    }




    @FXML
    public void editONAction(javafx.event.ActionEvent event)throws IOException
    {
        try {
            UpdateLabel.setText("");
            WineTypeDao wineTypeDao = new WineTypeDao();
            WinetypeEntity winetypeEntity = new WinetypeEntity();
            UsersDao usersDao = new UsersDao();
            UsersEntity usersEntity = new UsersEntity();
            usersEntity = usersDao.getUserByUsername(String.valueOf(winemakers.getSelectionModel().getSelectedItem()));
            winetypeEntity = wineTypeDao.getWineTypeByUserID(usersEntity.getUserId());


            FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/Scenes/WinemakerUpdate.fxml"));
            Parent root = (Parent) loader.load();
            WinemakerUpdate secController = (WinemakerUpdate) loader.getController();
            secController.UpdateUser.setText(usersEntity.getUsername());
            secController.UpdatePass.setText(AES.decrypt(usersEntity.getPassword(),"Secret"));
            secController.UpdateWineType.setText(winetypeEntity.getWinename());
            secController.setUpdateid(usersEntity.getUserId());
            secController.UpdateRedGrNeeded.setText(String.valueOf(winetypeEntity.getRedGrapeNeeded()));
            secController.UpdateWhiteGrNeeded.setText(String.valueOf(winetypeEntity.getWhiteGrapeNeeded()));
            winemakers.getItems().clear();
            PopulateListView();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.showAndWait();
            RefreshListView();
        }
        catch(Exception e)
        {
            UpdateLabel.setText("No selection!");
        }

    }




















    @FXML
    public void SwitchHostReg(javafx.event.ActionEvent event)throws IOException
    {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/Scenes/Host_Registration.fxml"));
        Parent root = (Parent) loader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Host Registration");
        stage.showAndWait();

    }

    @FXML
    public void SwitchHostDel(javafx.event.ActionEvent event)throws IOException
    {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/Scenes/Host_Delete.fxml"));
        Parent root = (Parent) loader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Host Delete");
        stage.showAndWait();

    }

    @FXML
    public void BackOnAction(javafx.event.ActionEvent event) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("/Scenes/sample.fxml"));
        Scene scene = new Scene(parent);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    @FXML
    public void SwitchWinemakerReg(javafx.event.ActionEvent event)throws IOException
    {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/Scenes/Winemaker_Registration.fxml"));
        Parent root = (Parent) loader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Winemaker Registration");
        stage.showAndWait();
    }
    public void SwitchLog(javafx.event.ActionEvent event)throws IOException
    {
        Parent Log = FXMLLoader.load(getClass().getResource("/Scenes/logs.fxml"));
        Scene logs = new Scene(Log);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(logs);
        window.show();
    }


    public void SwitchWinemakerDel(javafx.event.ActionEvent event)throws IOException
    {
        Parent WinemkDel = FXMLLoader.load(getClass().getResource("/Scenes/Winemaker_Delete.fxml"));
        Scene WineDel = new Scene(WinemkDel);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(WineDel);
        window.show();
    }


    public void PopulateListView()
    {

        ObservableList<UsersEntity> result = null;
        WineTypeDao wineTypeDao = new WineTypeDao();
        WinetypeEntity winetypeEntity = new WinetypeEntity();
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            result = FXCollections.observableList(session.createQuery("from UsersEntity person where privilegeId = 3").list());
            for(int i=0;i<result.size();i++)
            {
                winetypeEntity = wineTypeDao.getWineTypeByUserID(result.get(i).getUserId());
                winemakers.getItems().add(result.get(i).getUsername());
            }



        }
        catch(Exception e)
        {
            e.printStackTrace();
        }


    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        UpdateLabel.setText("");
        PopulateListView();




    }



}



