package simubank.bankapi.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Entity
@Table(name = "account")
public class Account {
    public enum Type {
        CREDIT,
        DEBIT
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    private double balance;
    private String name;
    private String type;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="cards_id", referencedColumnName = "id")
    private Card card;

    @OneToOne(mappedBy = "account")
    private Tpe tpe;

    public Account(){

    }

    public Account(String name, Type type, double balance) {
        this.name = name;
        this.type = type.name();
        this.balance = balance;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setCard(Card card){
        this.card = card;
    }

    @Override
    public String toString() {
    return "Account{" +
            "id=" + id +
            ", balance=" + balance +
            ", name='" + name + '\'' +
            ", type='" + type + '\'' +
            ", card=" + card + // Notez que cela va appeler la méthode toString() de Card si elle est définie
            '}';
    }

}
