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
import java.sql.Timestamp;

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
    AccountRepository accountRepository;
    @Autowired
    CardRepository cardRepository;
    @Autowired
    TpeRepository tpeRepository;

    public static void main(String[] args) {
        Application.launch(Bank.class, args);
    }

    @Override
    public void init() throws Exception {
        springContext = SpringApplication.run(Bank.class);
        FXMLLoader loader = new FXMLLoader(new URI("file:src/main/java/simubank/bankapi/views/dashboard.fxml").toURL());
        loader.setControllerFactory(springContext::getBean);
        root = loader.load();
    }

    @Bean
    public CommandLineRunner run() {
        return (args) -> {
            // cardRepository.deleteAll();
            // tpeRepository.deleteAll();
            // accountRepository.deleteAll();
            
            
            //On crée trois carte
            Timestamp validityDate =  Timestamp.valueOf("2022-06-12 00:00:00");
            Card testCard = new Card("Jo","Rock","131543412313423",2332,false, validityDate);
            Timestamp validityDate2 =  Timestamp.valueOf("2024-03-14 00:00:00");
            Card testCard2 = new Card("hugo","brisset","131543412313466",1111,false, validityDate2);
            Timestamp validityDate3 =  Timestamp.valueOf("2026-06-12 00:00:00");
            Card testCard3 = new Card("loic","berger","131543412313499",2222,true, validityDate3);
            //on les ajoute a la bdd
            cardRepository.save(testCard);
            cardRepository.save(testCard2);
            cardRepository.save(testCard3);
            //On crée trois compte
            Account account1 = new Account("hugo brisset", 100);
            Account account2 = new Account("loic berger", 100);
            Account account3 = new Account("Paul Bougle", 300);
            //on les ajoute a la bdd
            accountRepository.save(account1);
            accountRepository.save(account2);
            accountRepository.save(account3);
            //On crée trois tpe
            Tpe tpe1 = new Tpe();
            Tpe tpe2 = new Tpe();
            Tpe tpe3 = new Tpe();
            //on les ajoute a la bdd
            tpeRepository.save(tpe1);
            tpeRepository.save(tpe2);
            tpeRepository.save(tpe3);
            //On lie les cartes a chaque compte
            testCard.setAccount(account1);
            testCard2.setAccount(account2);
            testCard3.setAccount(account3);
            //On sauvegarde les changeent dans la bdd
            cardRepository.save(testCard);
            cardRepository.save(testCard2);
            cardRepository.save(testCard3);
            //On lie les tpe et les comptes
            tpe1.setAccount(account1);
            tpe2.setAccount(account2);
            tpe3.setAccount(account3);
            //On sauvegarde dans la bdd
            tpeRepository.save(tpe1);
            tpeRepository.save(tpe2);
            tpeRepository.save(tpe3);

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
