package chushka.repository;

import chushka.domain.entities.Product;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

public class ProductRepositoryImpl implements ProductRepository {

    private final EntityManager entityManager;

    @Inject
    public ProductRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Product save(Product entity) {
        this.entityManager.getTransaction().begin();
        this.entityManager.persist(entity);
        this.entityManager.getTransaction().commit();

        return entity;
    }

    @Override
    public Product findById(String s) {
        this.entityManager.getTransaction().begin();
        Product product = this.entityManager.createQuery("" +
                "SELECT p " +
                "FROM Product p " +
                "WHERE p.id = :id ", Product.class)
                .setParameter("id", s)
                .getSingleResult();
        this.entityManager.getTransaction().commit();

        return product;
    }

    @Override
    public List<Product> findAll() {
        this.entityManager.getTransaction().begin();
        List<Product> products = this.entityManager
                .createQuery("SELECT p FROM Product p ", Product.class)
                .getResultList();
        this.entityManager.getTransaction().commit();

        return products;
    }

    @Override
    public Long size() {
        this.entityManager.getTransaction().begin();
        Long size = this.entityManager
                .createQuery("" +
                        "SELECT count(p) " +
                        "FROM Product p " +
                        "", Long.class)
                .getSingleResult();
        this.entityManager.getTransaction().commit();

        return size;
    }

    @Override
    public void delete(String id) {

        this.entityManager.getTransaction().begin();
        this.entityManager
                .createNativeQuery("DELETE FROM products WHERE id='" + id + "'")
                .executeUpdate();
        this.entityManager.getTransaction().commit();
    }
}
