package org.example;

import org.example.configuration.MyConfig;
import org.example.entity.User;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) {
        AnnotationConfigApplicationContext applicationContext =
                new AnnotationConfigApplicationContext(MyConfig.class);

        Communication communication = applicationContext.getBean("communication", Communication.class);

       List<User> allUsers = communication.getAllUsers();
        System.out.println(allUsers);

        User user = new User((long)3,"James", "Brown", (byte) 24);
        User userUpdate = new User((long)3,"Thomas", "Shelby", (byte) 24);

        communication.saveUser(user);

        communication.updateUser(userUpdate);

        communication.deleteUser(3);
    }
}
