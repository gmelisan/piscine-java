package school21.spring.service.application;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import school21.spring.service.models.User;
import school21.spring.service.repositories.UsersRepository;

import javax.sql.DataSource;

public class Main {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
        UsersRepository usersRepository = context.getBean("usersRepositoryJdbc", UsersRepository.class);
        System.out.println("Test with usersRepositoryJdbc:");
        System.out.println(usersRepository.findAll());
        test(usersRepository);
        System.out.println("--------------------------");
        usersRepository = context.getBean("usersRepositoryJdbcTemplate", UsersRepository.class);
        System.out.println("Test with usersRepositoryJdbcTemplate:");
        System.out.println(usersRepository.findAll());
        test(usersRepository);
    }

    private static void test(UsersRepository usersRepository) {
        User user = new User(null, "a1");
        usersRepository.save(user);
        System.out.println(usersRepository.findById(user.getId()));
        user.setEmail("a2");
        usersRepository.update(user);
        System.out.println(usersRepository.findByEmail("a2"));
        usersRepository.delete(user.getId());
        System.out.println(usersRepository.findAll());
    }
}
