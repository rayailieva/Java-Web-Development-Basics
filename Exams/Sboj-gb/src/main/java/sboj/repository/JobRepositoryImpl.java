package sboj.repository;

import sboj.domain.entities.JobApplication;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

public class JobRepositoryImpl implements JobRepository {

    private final EntityManager entityManager;

    @Inject
    public JobRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public JobApplication save(JobApplication entity) {
        this.entityManager.getTransaction().begin();
        this.entityManager.persist(entity);
        this.entityManager.getTransaction().commit();

        return entity;
    }

    @Override
    public JobApplication findById(String s) {
        this.entityManager.getTransaction().begin();
        JobApplication jobApplication = this.entityManager.createQuery("" +
                "SELECT j " +
                "FROM JobApplication j " +
                "WHERE j.id = :id ", JobApplication.class)
                .setParameter("id", s)
                .getSingleResult();
        this.entityManager.getTransaction().commit();

        return jobApplication;
    }

    @Override
    public List<JobApplication> findAll() {
        this.entityManager.getTransaction().begin();
        List<JobApplication> jobApplications = this.entityManager
                .createQuery("" +
                        "SELECT j " +
                        "FROM JobApplication j ", JobApplication.class)
                .getResultList();
        this.entityManager.getTransaction().commit();

        return jobApplications;
    }

    @Override
    public Long size() {
        this.entityManager.getTransaction().begin();
        Long size = this.entityManager
                .createQuery("" +
                        "SELECT count(j) " +
                        "FROM JobApplication j " +
                        "", Long.class)
                .getSingleResult();
        this.entityManager.getTransaction().commit();

        return size;
    }

    @Override
    public void delete(String id) {
        this.entityManager.getTransaction().begin();
        this.entityManager.
                 createNativeQuery("DELETE FROM job_applications WHERE id='" + id + "'")
                .executeUpdate();
        this.entityManager.getTransaction().commit();
    }
}
