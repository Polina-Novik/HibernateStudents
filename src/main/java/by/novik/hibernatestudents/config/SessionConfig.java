package by.novik.hibernatedemo.config;

import by.novik.hibernatedemo.entity.Order;
import by.novik.hibernatedemo.entity.OrderLine;
import by.novik.hibernatedemo.entity.Product;
import by.novik.hibernatedemo.entity.User;
import by.novik.hibernatedemo.entity.student.Person;
import by.novik.hibernatedemo.entity.student.Student;
import by.novik.hibernatedemo.entity.student.Subject;
import lombok.RequiredArgsConstructor;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.AvailableSettings;
import org.hibernate.service.ServiceRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

@Configuration
@RequiredArgsConstructor
public class SessionConfig {
    private final HibernateConfig config;
    @Bean
    public SessionFactory getSessionFactory() {
        try { //раньше делали люди через эксемл
            org.hibernate.cfg.Configuration configuration = new org.hibernate.cfg.Configuration();
            Properties settings = getProperties();

            configuration.setProperties(settings);
            configuration.addAnnotatedClass(Product.class); //чтобы отображались и отслеживалисб действия с табл
//построй сервиз след и ретурн строчки дефолтные) просим у фабрики сессиб для работы с базой данных
            configuration.addAnnotatedClass(User.class);
            configuration.addAnnotatedClass(Person.class);
            configuration.addAnnotatedClass(Student.class);
            configuration.addAnnotatedClass(Subject.class);
            configuration.addAnnotatedClass(Order.class);
            configuration.addAnnotatedClass(OrderLine.class);
            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties()).build();

            return configuration.buildSessionFactory(serviceRegistry);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public Properties getProperties() {
        Properties settings = new Properties();
        //главные настройки кот всегда должны быть, соедиение  с базой
        settings.put(AvailableSettings.DRIVER, config.getDriver());
        settings.put(AvailableSettings.URL, config.getUrl());
        settings.put(AvailableSettings.USER, config.getUser());
        settings.put(AvailableSettings.PASS, config.getPassword());
        settings.put(AvailableSettings.DIALECT, config.getDialect()); //версия хибернейта
//далбше все необязателньо, дебаг цели, чтобы было показаны какие запросы в базу данных
        settings.put(AvailableSettings.SHOW_SQL, "true"); //покажет голый скл
        settings.put(AvailableSettings.FORMAT_SQL, "true"); //покажет красиво, не одна большая строка
       // settings.put(AvailableSettings.AUTOCOMMIT, "true"); по сут вообще не надо ща :) по дефолту нет автосохр в кибернейте

        settings.put(AvailableSettings.CURRENT_SESSION_CONTEXT_CLASS, "thread");

        settings.put(AvailableSettings.HBM2DDL_AUTO, "update"); //update, none create-drop - убрали на посл занятии с оредерами
        //оч важная штука!!!!!! когда запустим приложение, создадутся все таблицы по аналогии с классами чо у нас есть. но как только выключим приложение. таблицы из базы удалятся(((
        //креат - создал, дроп - удалил в конце
        //апдейт - данные сохраняются, дополняются, классы синхронизируются, лучше чем то что у нас сейчас
        //ноне - будет у нас на работе, если не писать эту строку,это дефолт. обычно этим загимаются другие люди а не хибернате
        return settings;
    }
}
