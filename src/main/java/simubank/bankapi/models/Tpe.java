package simubank.bankapi.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tpe")
public class Tpe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
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
