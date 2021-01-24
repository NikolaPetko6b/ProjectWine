package Controllers;

import Dao.UsersDao;
import org.junit.Test;
import tables.UsersEntity;

import static org.junit.Assert.*;

public class SampleControllerTest {
UsersDao usersDao = new UsersDao();


    @Test
    public void loginValidation() {

        usersDao = new UsersDao();
        UsersEntity result = null;
        String username = " ";
        try {
            result = usersDao.getUserByUsername(username);
            usersDao.setLoggedid(result.getUserId());
        }
        catch (Exception e)
        {
        System.out.println("Properly wrong user name!");
        }
        
    }
}