package simubank.bankapi;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.net.URI;

import simubank.bankapi.models.Account;
import simubank.bankapi.models.Card;
import simubank.bankapi.models.Tpe;
import simubank.bankapi.repositories.AccountRepository;
import simubank.bankapi.repositories.CardRepository;
import simubank.bankapi.repositories.TpeRepository;


@SpringBootApplication
@EnableJpaRepositories
public class Bank extends Application {
    private ConfigurableApplicationContext springContext;
    private Parent root;

    @Autowired
    AccountRepository accounts;
    @Autowired
    CardRepository cards;
    @Autowired
    TpeRepository tpeRepository;

    public static void main(String[] args) {
        Application.launch(Bank.class, args);
    }

    @Override
    public void init() throws Exception {
        springContext = SpringApplication.run(Bank.class);
        FXMLLoader loader = new FXMLLoader(new URI("file:src/main/resources/dashboard.fxml").toURL());
        loader.setControllerFactory(springContext::getBean);
        root = loader.load();
    }

    @Bean
    public CommandLineRunner run() {
        return (args) -> {
            accounts.deleteAll();
            cards.deleteAll();
            // tpeRepository.deleteAll();
            Card testCard = new Card("Jo","Rock","131543412313421",2332,false,null);

            Account test = new Account("Jo", Account.Type.CREDIT, 100);

            test.setCard(testCard);
            testCard.setAccount(test);
            accounts.save(test);

            // Tpe testTpe = new Tpe();
            // tpeRepository.save(testTpe);

            // Affichage des données de la base de données
            System.out.println("#######################################\n######################################\nListe des cartes :");
            cards.findAll().forEach(System.out::println);
            System.out.println("#######################################\n######################################\nListe des comptes :");
            accounts.findAll().forEach(System.out::println);
            // System.out.println("#######################################\n######################################\nListe des tpe :");
            // tpeRepository.findAll().forEach(System.out::println);
        };
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setScene(new Scene(root));

        stage.setMaximized(true);
        stage.setResizable(true);
        stage.setTitle("SimuBank");
        stage.show();

        stage.setOnCloseRequest((event) -> {
            System.exit(0);
        });
    }

    @Override
    public void stop() throws Exception {
        springContext.close();
    }
}
