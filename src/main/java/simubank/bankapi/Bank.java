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
import simubank.bankapi.repositories.AccountRepository;

import java.net.URI;

@SpringBootApplication
@EnableJpaRepositories
public class Bank extends Application {
    private ConfigurableApplicationContext springContext;
    private Parent root;

    @Autowired
    AccountRepository accounts;

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
