package org.itstep.domain.dao.impl;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.itstep.domain.dao.CategoryDao;
import org.itstep.domain.entity.Category;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryDaoImpl implements CategoryDao {

    private final SessionFactory sessionFactory;

    public CategoryDaoImpl() {
        sessionFactory = new Configuration()
                .configure()
                .buildSessionFactory();
    }

    @Override
    public void save(Category... category) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        try {
            for (Category b : category) {
                Integer id = (Integer) session.save(b);
                System.out.println("Id: " + id);
            }
            tx.commit();
        } catch (Throwable ex) {
            tx.rollback();
        } finally {
            session.close();
        }
    }

    @Override
    public Category find(int id) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        Category category = null;
        try {
            category = session.get(Category.class, id);
            Hibernate.initialize(category.getCars());
            tx.commit();
        } catch (Throwable ex) {
            tx.rollback();
        } finally {
            session.close();
        }
        return category;
    }

    @Override
    public List<Category> findAll() {
        return null;
    }

    @Override
    public void update(Category category) {

    }

    @Override
    public void delete(int id) {

    }
}
