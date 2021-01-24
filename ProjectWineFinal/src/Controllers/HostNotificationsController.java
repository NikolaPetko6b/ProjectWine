package Controllers;



import Dao.AES;
import Dao.HibernateUtil;
import Dao.NotificationsDao;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Callback;
import org.hibernate.Session;
import org.hibernate.persister.entity.AbstractEntityPersister;
import tables.NotificationsEntity;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class HostNotificationsController implements Initializable {
    @FXML
    public TableView NTable;
    @FXML
    public TableColumn Date;
    @FXML
    public TableColumn Message;



    public void DeleteRowOnAction() throws IOException {
        /*
        NTable.getItems().removeAll(
                NTable.getSelectionModel().getSelectedItems());

*/
        NotificationsEntity result = (NotificationsEntity) NTable.getSelectionModel().getSelectedItem();
        NotificationsDao notificationsDao = new NotificationsDao();
        result.setStatus("Deleted");
        notificationsDao.UpdateNotification(result);
        refreshTable();
    }


    public void ClearRowsOnAction() throws IOException {
        NotificationsDao notificationsDao = new NotificationsDao();
        List<NotificationsEntity> temp = notificationsDao.getNotifications();
        NotificationsEntity result = (NotificationsEntity) NTable.getSelectionModel().getSelectedItem();
        for (NotificationsEntity n:temp
        ) {n.setStatus("Deleted");
            notificationsDao.UpdateNotification(n);

        }
        refreshTable();




    }

    public void refreshTable()
    {
        NotificationsDao notificationsDao = new NotificationsDao();
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Object[]> list = session.createQuery("from NotificationsEntity n where status like 'NEW'").getResultList();
        session.close();


        ObservableList<Object[]> listObs = FXCollections.observableArrayList(list);

        Date.setCellValueFactory(new PropertyValueFactory<NotificationsEntity, java.util.Date>("Date"));
        Message.setCellValueFactory(new PropertyValueFactory<NotificationsEntity, String>("Message"));

        NTable.setItems(listObs);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        NotificationsDao notificationsDao = new NotificationsDao();
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Object[]> list = session.createQuery("from NotificationsEntity n where status like 'NEW'").getResultList();
        session.close();


        ObservableList<Object[]> listObs = FXCollections.observableArrayList(list);

        Date.setCellValueFactory(new PropertyValueFactory<NotificationsEntity, java.util.Date>("Date"));
        Message.setCellValueFactory(new PropertyValueFactory<NotificationsEntity, String>("Message"));


        NTable.setItems(listObs);









    }
}
