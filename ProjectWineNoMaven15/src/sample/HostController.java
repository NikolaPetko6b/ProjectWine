package sample;

import Dao.BottlesDao;
import Dao.CurrentStockDao;
import Dao.GrapeDao;
import Dao.WineDao;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import tables.BottlesEntity;
import tables.CurrentStockEntity;
import tables.GrapeEntity;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;

public class HostController implements Initializable {
    @FXML
    public TextField bottle1Field;

    @FXML
    public TextField bottle2Field;

    @FXML
    public TextField bottle3Field;

    @FXML
    public TextField bottle4Field;

    @FXML
    public TextField WhiteGrapeField;
    @FXML
    public TextField RedGrapeField;

    @FXML
    public CheckBox bottle1Box;
    @FXML
    public CheckBox bottle2Box;
    @FXML
    public CheckBox bottle3Box;
    @FXML
    public CheckBox bottle4Box;
    @FXML
    public CheckBox WhiteGrapeBox;
    @FXML
    public CheckBox RedGrapeBox;

    @FXML
    public TextField bottle1Quantity;
    @FXML
    public TextField bottle2Quantity;
    @FXML
    public TextField bottle3Quantity;
    @FXML
    public TextField bottle4Quantity;
    @FXML
    public TextField WhiteGrapeQuantity;
    @FXML
    public TextField RedGrapeQuantity;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            CurrentStockDao currentStockDao = new CurrentStockDao();
            WhiteGrapeField.setText(String.valueOf(currentStockDao.curWhiteGrape()));
            RedGrapeField.setText(String.valueOf(currentStockDao.curRedGrape()));
            bottle1Field.setText(String.valueOf(currentStockDao.cur187Bottles()));
            bottle2Field.setText(String.valueOf(currentStockDao.cur200Bottles()));
            bottle3Field.setText(String.valueOf(currentStockDao.cur375Bottles()));
            bottle4Field.setText(String.valueOf(currentStockDao.cur750Bottles()));
        }
        catch (Exception e)
        {

        }






    }

    public void MakeRequestOnAction() throws IOException
    {

        try
        {
            java.util.Date date=new java.util.Date();
            CurrentStockDao currentStockDao = new CurrentStockDao();
            CurrentStockEntity currentStockEntity = new CurrentStockEntity();
            currentStockEntity.setCurDate(date);
            currentStockEntity.setWarehouseId(1);
            CurrentStockEntity previous = currentStockDao.getRecordByID(currentStockDao.getLatestRecordID());
            currentStockEntity.setCurrentstockid(currentStockDao.getLatestRecordID()+1);

            if(WhiteGrapeBox.isSelected())
            {
                GrapeDao grapeDao = new GrapeDao();
                GrapeEntity grapeEntity = new GrapeEntity();
                grapeEntity.setGrapeId(grapeDao.getMaxID()+1);
                grapeEntity.setGrapetypeId(1);
                grapeEntity.setGrapequantity(Double.parseDouble(WhiteGrapeQuantity.getText()));
                grapeEntity.setDateoforder(date);
                grapeEntity.setWarehouseId(1);
                grapeDao.AddGrape(grapeEntity);
                currentStockEntity.setWhiteGrape((Double.parseDouble(WhiteGrapeQuantity.getText()))+previous.getWhiteGrape());
            }
            else
            {
                currentStockEntity.setWhiteGrape(0.0+previous.getWhiteGrape());
            }

            if(RedGrapeBox.isSelected())
            {
                GrapeDao grapeDao = new GrapeDao();
                GrapeEntity grapeEntity = new GrapeEntity();
                grapeEntity.setGrapeId(grapeDao.getMaxID()+1);
                grapeEntity.setGrapetypeId(2);
                grapeEntity.setGrapequantity(Double.parseDouble(RedGrapeQuantity.getText()));
                grapeEntity.setDateoforder(date);
                grapeEntity.setWarehouseId(1);
                grapeDao.AddGrape(grapeEntity);
                currentStockEntity.setRedGrape((Double.parseDouble(RedGrapeQuantity.getText()))+previous.getRedGrape());
            }
            else
            {
                currentStockEntity.setRedGrape(0.0+ previous.getRedGrape());
            }

            if(bottle1Box.isSelected())
            {
                BottlesDao bottlesDao = new BottlesDao();
                BottlesEntity bottlesEntity = new BottlesEntity();
                bottlesEntity.setBottlequantity(Integer.parseInt(bottle1Quantity.getText()));
                bottlesEntity.setBottlesId(bottlesDao.getBottlesMaxID()+1);
                bottlesEntity.setBottletypeId(1);
                bottlesEntity.setDateoforderB(date);
                bottlesEntity.setWarehouseId(1);
                bottlesDao.AddBottle(bottlesEntity);
                currentStockEntity.setBottles187((Integer.parseInt(bottle1Quantity.getText())+previous.getBottles187()));
            }
            else
            {
                currentStockEntity.setBottles187(0+previous.getBottles187());
            }

            if(bottle2Box.isSelected())
            {
                BottlesDao bottlesDao = new BottlesDao();
                BottlesEntity bottlesEntity = new BottlesEntity();
                bottlesEntity.setBottlequantity(Integer.parseInt(bottle2Quantity.getText()));
                bottlesEntity.setBottlesId(bottlesDao.getBottlesMaxID()+1);
                bottlesEntity.setBottletypeId(2);
                bottlesEntity.setDateoforderB(date);
                bottlesEntity.setWarehouseId(1);
                bottlesDao.AddBottle(bottlesEntity);
                currentStockEntity.setBottles200((Integer.parseInt(bottle2Quantity.getText())+previous.getBottles200()));


            }
            else
            {
                currentStockEntity.setBottles200(0+ previous.getBottles200());
            }

            if(bottle3Box.isSelected())
            {
                BottlesDao bottlesDao = new BottlesDao();
                BottlesEntity bottlesEntity = new BottlesEntity();
                bottlesEntity.setBottlequantity(Integer.parseInt(bottle3Quantity.getText()));
                bottlesEntity.setBottlesId(bottlesDao.getBottlesMaxID()+1);
                bottlesEntity.setBottletypeId(3);
                bottlesEntity.setDateoforderB(date);
                bottlesEntity.setWarehouseId(1);
                bottlesDao.AddBottle(bottlesEntity);
                currentStockEntity.setBottles375((Integer.parseInt(bottle3Quantity.getText())+previous.getBottles375()));
            }
            else
            {
                currentStockEntity.setBottles375(0+previous.getBottles375());
            }

            if(bottle4Box.isSelected())
            {
                BottlesDao bottlesDao = new BottlesDao();
                BottlesEntity bottlesEntity = new BottlesEntity();
                bottlesEntity.setBottlequantity(Integer.parseInt(bottle4Quantity.getText()));
                bottlesEntity.setBottlesId(bottlesDao.getBottlesMaxID()+1);
                bottlesEntity.setBottletypeId(4);
                bottlesEntity.setDateoforderB(date);
                bottlesEntity.setWarehouseId(1);
                bottlesDao.AddBottle(bottlesEntity);
                currentStockEntity.setBottles750((Integer.parseInt(bottle4Quantity.getText())+previous.getBottles750()));
            }
            else
            {
                currentStockEntity.setBottles750(0+ previous.getBottles750());
            }


            currentStockDao.AddRecord(currentStockEntity);
            WhiteGrapeField.setText(String.valueOf(currentStockDao.curWhiteGrape()));
            RedGrapeField.setText(String.valueOf(currentStockDao.curRedGrape()));
            bottle1Field.setText(String.valueOf(currentStockDao.cur187Bottles()));
            bottle2Field.setText(String.valueOf(currentStockDao.cur200Bottles()));
            bottle3Field.setText(String.valueOf(currentStockDao.cur375Bottles()));
            bottle4Field.setText(String.valueOf(currentStockDao.cur750Bottles()));





        }
        catch(Exception e)
        {
        e.printStackTrace();
        }


    }




}
