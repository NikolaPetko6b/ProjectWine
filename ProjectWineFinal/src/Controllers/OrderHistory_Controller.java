package Controllers;

import Dao.GrapeDao;
import Dao.HibernateUtil;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import org.hibernate.Session;
import tables.BottlesEntity;
import tables.GrapeEntity;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class OrderHistory_Controller implements Initializable {

    @FXML
    public DatePicker fromDate;

    @FXML
    public DatePicker toDate;
    @FXML
    public ChoiceBox stockType;

    @FXML
    public ListView orderhistory;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        stockType.setItems(FXCollections.observableArrayList(
                "Grape","Bottles")
        );

    }


    public void SeeHistoryOnAction(){
        orderhistory.getItems().clear();

        try(Session session = HibernateUtil.getSessionFactory().openSession()) {

            if (stockType.getSelectionModel().getSelectedItem() == "Grape") {
                List<GrapeEntity> result1 = null;
                result1 = session.createQuery("from GrapeEntity  where dateoforder >= '" + fromDate.getValue() + "' and dateoforder <= '" + toDate.getValue() + "'").list();
                for (int i = 0; i < result1.size(); i++) {
                    orderhistory.getItems().add("ID: "+result1.get(i).getGrapeId()+" Quantity: " + result1.get(i).getGrapequantity() + " DateOfOrder: " + result1.get(i).getDateoforder());
                }
            } else if (stockType.getSelectionModel().getSelectedItem() == "Bottles") {
                List<BottlesEntity> result2 = null;
                result2 = session.createQuery("from BottlesEntity  where dateoforderB >= '" + fromDate.getValue() + "' and dateoforderB <= '" + toDate.getValue() + "'").list();
                for (int i = 0; i < result2.size(); i++) {
                    orderhistory.getItems().add("ID:"+result2.get(i).getBottlesId()+" Quantity: " + result2.get(i).getBottlequantity() + " DateOfOrder: " + result2.get(i).getDateoforderB());
                }
            }


        }
        catch (Exception e)
        {
            e.printStackTrace();
        }


    }

    public void BackOnAction(javafx.event.ActionEvent event) throws IOException {
        Parent HostView = FXMLLoader.load(getClass().getResource("/Scenes/Host.fxml"));
        Scene Host = new Scene(HostView);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(Host);
        window.show();

    }
}
