package org.itstep.domain.dao;

import org.itstep.domain.entity.*;
import java.util.List;


public interface CarDao {
    void save(Car... car);
    Car find(int id);
    List<Car> findAll();
    void update(Car car);
    void delete(int id);
}
