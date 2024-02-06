package simubank.bankapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import simubank.bankapi.models.Account;
import simubank.bankapi.repositories.AccountRepository;
import simubank.bankapi.view.Dashboard;

@SpringBootApplication
@EnableJpaRepositories
public class Bank {
    @Autowired
    AccountRepository accounts;

    public static void main(String[] args) {
        SpringApplication.run(Bank.class, args);
        Dashboard.launch(Dashboard.class, args);
    }

    @Bean
    public CommandLineRunner run() {
        return (args) -> {
            accounts.deleteAll();

        };
    }
}
