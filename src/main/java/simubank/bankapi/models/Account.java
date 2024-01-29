package simubank.bankapi.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Entity
@Table(name = "accounts")
public class Account {
    public enum Type {
        CREDIT,
        DEBIT
    }


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double balance;
    private String name;
    private String type;
    public Account() {
    }
    public Account (String name, String type, double balance) {
        this.name = name;
        this.type = type;
        this.balance = balance;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }


}
