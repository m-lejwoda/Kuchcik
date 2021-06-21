package pl.saxatachi.kuchcik.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.saxatachi.kuchcik.model.ElementofReceipt;
import pl.saxatachi.kuchcik.model.Tag;

public interface TagRepository extends JpaRepository<Tag,Long> {
}
