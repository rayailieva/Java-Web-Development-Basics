package exam.repository;

import exam.domain.entities.Document;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

public class DocumentRepositoryImpl implements DocumentRepository {

    private final EntityManager entityManager;

    @Inject
    public DocumentRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Document save(Document entity) {
        this.entityManager.getTransaction().begin();
        this.entityManager.persist(entity);
        this.entityManager.getTransaction().commit();

        return entity;
    }

    @Override
    public Document findById(String s) {
        this.entityManager.getTransaction().begin();
        try {
            Document document = this.entityManager
                    .createQuery("SELECT d FROM Document d WHERE d.id = :id", Document.class)
                    .setParameter("id", s)
                    .getSingleResult();

            this.entityManager.getTransaction().commit();
            return document;
        } catch (Exception e) {
            this.entityManager.getTransaction().rollback();
            return null;
        }
    }

    @Override
    public List<Document> findAll() {
        this.entityManager.getTransaction().begin();
        List<Document> documents = this.entityManager
                .createQuery("SELECT d FROM Document d ", Document.class)
                .getResultList();
        this.entityManager.getTransaction().commit();

        return documents;
    }

    @Override
    public void delete(String id) {
        this.entityManager.getTransaction().begin();
        this.entityManager
                .createNativeQuery("DELETE FROM exodia_db.documents WHERE id='" + id + "'")
                .executeUpdate();
        this.entityManager.getTransaction().commit();
    }
}
