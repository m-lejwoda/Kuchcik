package pl.saxatachi.kuchcik.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.saxatachi.kuchcik.model.Address;

public interface AddressRepository extends JpaRepository<Address,Long> {
}
