package springbootmongodb.mongo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.test.context.junit4.SpringRunner;
import springbootmongodb.mongo.dao.BlueGlassDao;
import springbootmongodb.mongo.dao.UserDao;
import springbootmongodb.mongo.entity.BlueGlass;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MongoApplicationTests {

    @Autowired
    BlueGlassDao userDao;

    @Autowired
    UserDao usersDao;

    @Test
    public void contextLoads() {
    }


    @Test
    public void saveUser() {
        BlueGlass user = new BlueGlass();
        for (int i = 0; i < 100; i++) {
            user.setId((long) (100 + i));
            user.setBlueGlassName("hollysys_" + i);
            user.setPassword("123456a?");
            userDao.saveUser(user);
            System.out.println("insert:" + i);
        }
    }

    @Test
    public void findUserByUserName() {
        BlueGlass user = userDao.findUserByUserName("hollysys_1");
        System.out.println(user);
    }

    @Test
    public void updateUser() {
        BlueGlass user = new BlueGlass();
        user.setId(1L);
        user.setBlueGlassName("hollysys");
        user.setPassword("123456a?");
        userDao.updateUser(user);

    }

    @Test
    public void deleteUser() {
        userDao.deleteUserById(1L);
    }

    @Test
    public void findUserPagination() {
        Page<BlueGlass> userPages = userDao.findUserPagination(1, 5, "id");
        userPages.forEach(u -> {
            System.out.println(u);
        });
    }


    @Test
    public void setUser() {
        usersDao.update();
    }

    @Test
    public void delete() {
        usersDao.delete();
    }
}
