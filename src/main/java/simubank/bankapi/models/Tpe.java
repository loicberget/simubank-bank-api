package simubank.bankapi.models;

import jakarta.persistence.*;

@Entity
@Table(name = "tpe")
public class Tpe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="account_id", referencedColumnName = "id")
    private Account account;

    public Tpe(){}

    public void setAccount(Account account){
        this.account = account;
    }

    public Long getId(){
        return id;
    }

    public Account getAccount(){
        return account;
    }
    
}
