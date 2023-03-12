package s9.apr.giftservices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "s9.apr")
public class GiftServicesApplication {
    /*
    private EntityManager entityManager;

    public GiftServicesApplication(EntityManager entityManager) {
    this.entityManager = entityManager;

    create();
    }

    public void create() {
    Tutor t = new Tutor();

    Student s = new Student();
    s.setTutor(t);

    entityManager.persist(t);
    entityManager.persist(s);
    entityManager.flush();
    }
    */

    public static void main(String[] args) {
        SpringApplication.run(GiftServicesApplication.class, args);
    }

}
