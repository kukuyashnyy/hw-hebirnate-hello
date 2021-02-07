package org.itstep.domain.dao.impl;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.itstep.domain.dao.CarDao;
import org.itstep.domain.entity.Car;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarDaoImpl implements CarDao {

    private final SessionFactory sessionFactory;

    public CarDaoImpl() {
        this.sessionFactory = new Configuration()
        .configure()
        .buildSessionFactory();
    }

    @Override
    public void save(Car... car) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        try {
            for (Car c : car) {
                Integer id = (Integer) session.save(c);
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
    public Car find(int id) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        Car car = null;
        try {
            car = session.get(Car.class, id);
            Hibernate.initialize(car.getCategory());
            tx.commit();
        } catch (Throwable ex) {
            tx.rollback();
        } finally {
            session.close();
        }
        return car;
    }

    @Override
    public List<Car> findAll() {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        List<Car> car = null;
        try {
            car = session.createQuery("from Car", Car.class).list();
            tx.commit();
        } catch (Throwable ex) {
            tx.rollback();
        } finally {
            session.close();
        }
        return car;
    }

    @Override
    public void update(Car car) {

    }

    @Override
    public void delete(int id) {

    }
}
