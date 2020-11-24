package sample;

import Dao.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import tables.CurrentStockEntity;
import tables.UsersEntity;
import tables.WineEntity;
import tables.WinetypeEntity;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class WinemakerController implements Initializable {

    @FXML
    public TextField RedGrapeYouNeed;

    @FXML
    public TextField WhiteGrapeYouNeed;


    @FXML
    public TextField LitersYouCanMake;


    @FXML
    public TextField LitersForMaking;


    @FXML
    public TextField WarehouseRedGrape;

    @FXML
    public TextField WarehouseWhiteGrape;

    @FXML
    public TextField Warehouse187Bottles;

    @FXML
    public TextField Warehouse200Bottles;

    @FXML
    public TextField  Warehouse375Bottles;

    @FXML
    public TextField  Warehouse750Bottles;



   


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        CurrentStockDao currentStockDao = new CurrentStockDao();
        WineTypeDao wineTypeDao = new WineTypeDao();
        WinetypeEntity winetypeEntity = new WinetypeEntity();
        UsersEntity usersEntity = new UsersEntity();
        UsersDao usersDao = new UsersDao();

            try {
                WarehouseRedGrape.setText(String.valueOf(currentStockDao.curRedGrape()));
                WarehouseWhiteGrape.setText(String.valueOf(currentStockDao.curWhiteGrape()));
                Warehouse187Bottles.setText(String.valueOf(currentStockDao.cur187Bottles()));
                Warehouse200Bottles.setText(String.valueOf(currentStockDao.cur200Bottles()));
                Warehouse375Bottles.setText(String.valueOf(currentStockDao.cur375Bottles()));
                Warehouse750Bottles.setText(String.valueOf(currentStockDao.cur750Bottles()));


            }
            catch (Exception e)
            {

            }


            RedGrapeYouNeed.setText(String.valueOf(5.00));
            WhiteGrapeYouNeed.setText(String.valueOf(5.00));
            winetypeEntity = wineTypeDao.getWineTypeByUserID(usersDao.getLoggedid());
            WhiteGrapeYouNeed.setText(String.valueOf(winetypeEntity.getWhiteGrapeNeeded()));
            RedGrapeYouNeed.setText(String.valueOf(winetypeEntity.getRedGrapeNeeded()));
    }












    public void CalculateOnAction()throws IOException
    {
        GrapeDao grapeDao = new GrapeDao();
        double liters = 0.00;
            if(Double.parseDouble(WhiteGrapeYouNeed.getText()) < Double.parseDouble(WarehouseWhiteGrape.getText()) &&
                    Double.parseDouble(RedGrapeYouNeed.getText()) < Double.parseDouble(WarehouseRedGrape.getText()))
            {
                if(Double.parseDouble(WhiteGrapeYouNeed.getText())!=0) {

                    liters = liters + Double.parseDouble(WarehouseWhiteGrape.getText())/Double.parseDouble(WhiteGrapeYouNeed.getText());
                }

                if(Double.parseDouble(RedGrapeYouNeed.getText())!=0) {
                    liters = liters + Double.parseDouble(WarehouseRedGrape.getText())/Double.parseDouble(RedGrapeYouNeed.getText());
                }
            }


                    LitersYouCanMake.setText(String.valueOf(liters));
    }







    public void MakeOnAction()throws IOException
    {
        
    }




}
