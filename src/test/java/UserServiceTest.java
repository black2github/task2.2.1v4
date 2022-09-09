import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class UserServiceTest {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @org.junit.Before
    public void setUp() throws Exception {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        userService = context.getBean(UserService.class);
    }

    @org.junit.After
    public void tearDown() throws Exception {
    }

    @org.junit.Test
    public void findUserByCar() {
        String model = "model123";
        int series = 123;
        User user = new User("first_Name", "last_Name", "user@domen.ru");
        Car car = new Car(model, series);
        user.setCar(car);
        userService.add(user);
        User user2 = userService.findUserByCar(model, series);
        Assert.assertTrue("User by car model and series not found.", user.equals(user2) );
    }
}