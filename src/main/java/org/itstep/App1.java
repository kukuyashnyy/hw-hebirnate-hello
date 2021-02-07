package org.itstep;

import org.itstep.domain.dao.CarDao;
import org.itstep.domain.dao.CategoryDao;
import org.itstep.domain.entity.Car;
import org.itstep.domain.entity.Category;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.math.BigDecimal;
import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App1
{
    public static void main( String[] args )
    {
        ApplicationContext context = new ClassPathXmlApplicationContext("application.xml");
        CarDao carDao = context.getBean(CarDao.class);
        CategoryDao categoryDao = context.getBean(CategoryDao.class);

        Category category = new Category("Category1");
        categoryDao.save(category);

        Car car = new Car("Model1", BigDecimal.valueOf(100_000), false);

        car.setCategory(category);

        carDao.save(car);
        System.out.println(carDao.findAll());
    }
}
