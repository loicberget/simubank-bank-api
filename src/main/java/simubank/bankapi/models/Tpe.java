package simubank.bankapi.models;

import jakarta.persistence.*;

@Entity
@Table(name = "tpe")
public class Tpe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="account_id", referencedColumnName = "id")
    private Account account;

    public Tpe(){}

    public void setAccount(Account account){
        this.account = account;
    }

    @Override
    public String toString() {
    return "Tpe{" +
            "id=" + id +
            ", account=" + account + // Notez que cela va appeler la méthode toString() de Account si elle est définie
            '}';
    }
}
