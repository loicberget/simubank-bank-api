package simubank.bankapi.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import simubank.bankapi.models.Card;

@Repository
@Transactional
public interface CardRepository extends CrudRepository<Card, Long> {
    Card findByCardNumber(String cardNumber);
}
