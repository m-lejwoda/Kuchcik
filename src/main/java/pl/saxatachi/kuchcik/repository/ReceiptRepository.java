package pl.saxatachi.kuchcik.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.saxatachi.kuchcik.model.Receipt;
@Repository
public interface ReceiptRepository extends JpaRepository<Receipt,Long> {
    Receipt getOne(Long id);
}
