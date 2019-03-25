package exam.repository;

import exam.domain.entities.Document;

public interface DocumentRepository extends GenericRepository<Document, String> {

    void delete(String id);
}
