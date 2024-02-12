package simubank.bankapi.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import simubank.bankapi.models.Tpe;

@Repository
@Transactional
public interface TpeRepository extends CrudRepository<Tpe, Long> {
}