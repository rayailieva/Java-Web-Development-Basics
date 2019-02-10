package registerapp.repository;

import java.util.List;
import java.util.Optional;

public interface GenericRepository<Entity, Id> {

    Entity save(Entity entity);

    List<Entity> findAll();

    Entity findById(Id id);

    void remove(Id id);
}
