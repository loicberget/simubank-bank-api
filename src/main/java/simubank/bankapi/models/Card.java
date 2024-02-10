package simubank.bankapi.models;

import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="card")
public class Card {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;
    private String firstName;
    private String lastName;
    private String cardNumber;
    private int pin;
    private boolean oppose;
    private Timestamp date;

    @OneToOne(mappedBy = "card")
    private Account account;

    public Card(){
    }
    public Card(String firstname, String lastname, String cardnumber, int pin, boolean oppose, Timestamp date){
        this.firstName = firstname;
        this.lastName = lastname;
        this.cardNumber = cardnumber;
        this.pin = pin;
        this.oppose = oppose;
        this.date = date;
    }

    public void setId(Long id){
        this.id = id;
    }
    public Long getId(){
        return id;
    }

    public void setAccount(Account account){
        this.account = account;
    }
    
    @Override
    public String toString() {
    return "Card{" +
            "id=" + id +
            ", firstName='" + firstName + '\'' +
            ", lastName='" + lastName + '\'' +
            ", cardNumber='" + cardNumber + '\'' +
            ", pin=" + pin +
            ", oppose=" + oppose +
            ", date=" + date +
            '}';
    }

}
