package pl.saxatachi.kuchcik.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.saxatachi.kuchcik.model.ElementofReceipt;
import pl.saxatachi.kuchcik.model.Tag;

import java.util.List;

public interface TagRepository extends JpaRepository<Tag,Long> {
    public List<Tag> findTagByName(String name);
}
