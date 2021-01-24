package Controllers;

import Dao.CurrentStockDao;
import Dao.UsersDao;
import Dao.WineDao;
import Dao.WineTypeDao;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import Scenes.WinNotifications;
import tables.CurrentStockEntity;
import tables.WineEntity;
import tables.WinetypeEntity;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class BottlesFillController implements Initializable {


    @FXML
    public Label Count187;

    @FXML
    public Label Count200;

    @FXML
    public Label Count375;

    @FXML
    public Label Count750;



    @FXML
    public Label liters;
    @FXML
    public Button fill;

    public int bottle1;
    public int bottle2;
    public int bottle3;
    public int bottle4;
    public double tempLiters;
    public double allLiters;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Count187.setText("0");
        Count200.setText("0");
        Count375.setText("0");
        Count750.setText("0");

    }

    public void increment187() throws IOException {
        CurrentStockDao currentStockDao = new CurrentStockDao();
        if(tempLiters>=0.187 && bottle1<currentStockDao.cur187Bottles())
        {

            tempLiters = tempLiters - 0.187;
            bottle1 += 1;
            Count187.setText(String.valueOf(bottle1));
            liters.setText(String.valueOf(tempLiters));

        }
        else
        {

        }

    }


    public void increment200() throws IOException {
        CurrentStockDao currentStockDao = new CurrentStockDao();
        if(tempLiters>=0.200 && bottle2<currentStockDao.cur200Bottles())
        {

            tempLiters = tempLiters - 0.200;
            bottle2 += 1;
            Count200.setText(String.valueOf(bottle2));
            liters.setText(String.valueOf(tempLiters));

        }
        else
        {

        }

    }


    public void increment375() throws IOException {
        CurrentStockDao currentStockDao = new CurrentStockDao();
        if(tempLiters>=0.375 && bottle3<currentStockDao.cur375Bottles())
        {

            tempLiters = tempLiters - 0.375;
            bottle3 += 1;
            Count375.setText(String.valueOf(bottle3));
            liters.setText(String.valueOf(tempLiters));

        }
        else
        {

        }

    }

    public void increment750() throws IOException {
        CurrentStockDao currentStockDao = new CurrentStockDao();
        if(tempLiters>=0.750 && bottle4<currentStockDao.cur750Bottles())
        {

            tempLiters = tempLiters - 0.750;
            bottle4 += 1;
            Count750.setText(String.valueOf(bottle4));
            liters.setText(String.valueOf(tempLiters));

        }
        else
        {

        }

    }

    public void decrement187(){

        if(bottle1 >0 ) {
            tempLiters = tempLiters + 0.187;
            bottle1 -= 1;
            Count187.setText(String.valueOf(bottle1));
            liters.setText(String.valueOf(tempLiters));
        }

    }

    public void decrement200(){
        if(bottle2 >0) {
            tempLiters = tempLiters + 0.200;
            bottle2 -= 1;
            Count200.setText(String.valueOf(bottle2));
            liters.setText(String.valueOf(tempLiters));
        }
    }

    public void decrement375(){
        CurrentStockDao currentStockDao = new CurrentStockDao();
        if(bottle3>0) {
            tempLiters = tempLiters + 0.375;
            bottle3 -= 1;
            Count375.setText(String.valueOf(bottle3));
            liters.setText(String.valueOf(tempLiters));
        }
    }

    public void decrement750(){
        CurrentStockDao currentStockDao = new CurrentStockDao();
        if(bottle4>0) {
            tempLiters = tempLiters + 0.750;
            bottle4 -= 1;
            Count750.setText(String.valueOf(bottle4));
            liters.setText(String.valueOf(tempLiters));
        }
    }


    public void FillOnAction(javafx.event.ActionEvent event) throws IOException, AWTException {
        Stage stage = (Stage) fill.getScene().getWindow();
        java.util.Date date=new java.util.Date();
        UsersDao usersDao = new UsersDao();
        WineTypeDao wineTypeDao = new WineTypeDao();
        WinetypeEntity winetypeEntity = wineTypeDao.getWineTypeByUserID(usersDao.getLoggedid());
        WineEntity wineEntity= new WineEntity();
        WineDao wineDao = new WineDao();
        CurrentStockDao currentStockDao = new CurrentStockDao();
        CurrentStockEntity currentStockEntity = new CurrentStockEntity();

        if(bottle1 !=0 || bottle2!=0||bottle3!=0||bottle4!=0)
        {
            currentStockEntity.setBottles187(currentStockDao.cur187Bottles()-bottle1);
            currentStockEntity.setBottles200(currentStockDao.cur200Bottles()-bottle2);
            currentStockEntity.setBottles375(currentStockDao.cur375Bottles()-bottle3);
            currentStockEntity.setBottles750(currentStockDao.cur750Bottles()-bottle4);
            currentStockEntity.setWhiteGrape(currentStockDao.curWhiteGrape()-(allLiters-tempLiters)*winetypeEntity.getWhiteGrapeNeeded());
            currentStockEntity.setRedGrape(currentStockDao.curRedGrape()-(allLiters-tempLiters)*winetypeEntity.getRedGrapeNeeded());
            currentStockEntity.setCurrentstockid(currentStockDao.getLatestRecordID()+1);
            currentStockEntity.setWarehouseId(1);
            currentStockEntity.setCurDate(date);
            currentStockDao.AddRecord(currentStockEntity);

            wineEntity.setMadedate(date);
            wineEntity.setSerialnum(String.valueOf(wineDao.getMaxID()+1));
            wineEntity.setWarehouseId(1);
            wineEntity.setWinetypeId(winetypeEntity.getWinetypeId());


            if(bottle1!=0)
            {
                wineEntity.setWineId(wineDao.getMaxID()+1);
                wineEntity.setSize(187.0);
                wineEntity.setWinequantity(bottle1);
                wineDao.AddWine(wineEntity);
            }

            if(bottle2!=0)
            {
                wineEntity.setWineId(wineDao.getMaxID()+1);
                wineEntity.setSize(200.0);
                wineEntity.setWinequantity(bottle2);
                wineDao.AddWine(wineEntity);
            }

            if(bottle3!=0)
            {
                wineEntity.setWineId(wineDao.getMaxID()+1);
                wineEntity.setSize(375.0);
                wineEntity.setWinequantity(bottle3);
                wineDao.AddWine(wineEntity);
            }

            if(bottle4!=0)
            {
                wineEntity.setWineId(wineDao.getMaxID()+1);
                wineEntity.setSize(750.0);
                wineEntity.setWinequantity(bottle4);
                wineDao.AddWine(wineEntity);
            }


            bottle1=0;
            bottle2 = 0;
            bottle3 = 0;
            bottle4 = 0;

        }


        if(currentStockDao.curWhiteGrape()<20){
            WinNotifications.showNotification("White Grape");
        }
        if(currentStockDao.curRedGrape()<20){
            WinNotifications.showNotification("Red Grape");
        }
        if(currentStockDao.cur187Bottles()<20){
            WinNotifications.showNotification("Bottle 187ml");
        }
        if(currentStockDao.cur200Bottles()<20){
            WinNotifications.showNotification("Bottle 200ml");
        }
        if(currentStockDao.cur375Bottles()<20){
            WinNotifications.showNotification("Bottle 375ml");
        }
        if(currentStockDao.cur750Bottles()<20){
            WinNotifications.showNotification("Bottle 750ml");
        }


        stage.close();




    }



    public void AutoFillOnAction() throws IOException {

        CurrentStockDao currentStockDao = new CurrentStockDao();
        System.out.println(currentStockDao.cur187Bottles());
        if((int)(tempLiters/0.750) != 0)
        {
            while(tempLiters>=0.750 && bottle4<currentStockDao.cur750Bottles())
            {
                increment750();
            }

        }

        if((int)(tempLiters/0.375) != 0)
        {
            while(tempLiters>=0.375 && bottle3<currentStockDao.cur375Bottles())
            {
                increment375();
            }


        }

        if((int)(tempLiters/0.200) != 0)
        {
            while(tempLiters>=0.200 && bottle2<currentStockDao.cur200Bottles())
            {
                increment200();
            }


        }

        if((int)(tempLiters/0.187) != 0)
        {

            while(tempLiters>=0.187 && bottle1<currentStockDao.cur187Bottles())
            {
                increment187();
            }


        }












    }

}
