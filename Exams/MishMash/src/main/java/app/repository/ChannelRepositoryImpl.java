package app.repository;

import app.domain.entities.Channel;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

public class ChannelRepositoryImpl implements ChannelRepository {

    private final EntityManager entityManager;

    @Inject
    public ChannelRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Channel save(Channel entity) {
        this.entityManager.getTransaction().begin();
        this.entityManager.persist(entity);
        this.entityManager.getTransaction().commit();

        return entity;
    }

    @Override
    public Channel findById(String s) {
        this.entityManager.getTransaction().begin();
        Channel channel = this.entityManager.createQuery("" +
                "SELECT c " +
                "FROM Channel c " +
                "WHERE c.id = :id ", Channel.class)
                .setParameter("id", s)
                .getSingleResult();
        this.entityManager.getTransaction().commit();

        return channel;
    }

    @Override
    public List<Channel> findAll() {
        this.entityManager.getTransaction().begin();
        List<Channel> channels = this.entityManager
                .createQuery("" +
                        "SELECT c " +
                        "FROM Channel c ", Channel.class)
                .getResultList();
        this.entityManager.getTransaction().commit();

        return channels;
    }

    @Override
    public Long size() {
        this.entityManager.getTransaction().begin();
        Long size = this.entityManager
                .createQuery("" +
                        "SELECT count(c) " +
                        "FROM Channel c " +
                        "", Long.class)
                .getSingleResult();
        this.entityManager.getTransaction().commit();

        return size;
    }

    @Override
    public Channel update(Channel entity) {
        this.entityManager.getTransaction().begin();
        try {
            Channel updatedChannel = this.entityManager.merge(entity);
            this.entityManager.getTransaction().commit();

            return updatedChannel;
        }catch (Exception e) {
            this.entityManager.getTransaction().rollback();

            return null;
        }
    }

}
