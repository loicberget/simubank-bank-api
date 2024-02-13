package simubank.bankapi.models;

import java.sql.Timestamp;

import jakarta.persistence.*;

@Entity
@Table(name = "card")
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    private String firstName;
    private String lastName;
    private String cardNumber;
    private int pin;
    private boolean oppose;
    private Timestamp date;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "accound_id", referencedColumnName = "id")
    private Account account;

    public Card() {
    }

    public Card(String firstname, String lastname, String cardnumber, int pin, boolean oppose, Timestamp date) {
        this.firstName = firstname;
        this.lastName = lastname;
        this.cardNumber = cardnumber;
        this.pin = pin;
        this.oppose = oppose;
        this.date = date;
    }

    public Long getId() {
        return id;
    }
    public boolean isOpposed() {
        return oppose;
    }
    public Timestamp getDate() {
        return date;
    }
    public Account getAccount(){
        return account;
    }
    

    public void setAccount(Account account) {
        this.account = account;
    }


}
