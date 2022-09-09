package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.CarService;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.util.Assert;

import java.util.List;

public class MainApp {
   public static void main(String[] args) {
      User user;

      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);
      CarService carService = context.getBean(CarService.class);

      userService.add(new User("User1", "Lastname1", "user1@mail.ru"));

      for (int i=0; i < 3; i++) {
         user = new User("User5"+i, "Lastname5"+i, "user5@mail.ru"+i);
         Car car = new Car("mers"+i, 10+i);
         user.setCar(car);
         userService.add(user);
         User usr = userService.findUserByCar("mers"+i, 10+i );
         System.out.println("user by car = " + usr);
      }
      user = userService.findUserByCar("123", 0 );
      Assert.isNull(user, "user by car (123, 0) must not be found.");

      List<User> users = userService.listUsers();
      for (User usr : users) {
         System.out.println(usr);
      }

      context.close();
   }
}
