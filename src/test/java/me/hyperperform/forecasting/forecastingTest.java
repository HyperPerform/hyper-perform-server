package me.hyperperform.forecasting;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by rohan on 2016/10/02.
 */
public class forecastingTest
{
    EntityManagerFactory entityManagerFactory;
    EntityManager entityManager;
    EntityTransaction entityTransaction;

    @Before
    public void initConnection()
    {
        entityManagerFactory = Persistence.createEntityManagerFactory("PostgreJPA");
        entityManager = entityManagerFactory.createEntityManager();
        entityTransaction = entityManager.getTransaction();
    }

    @After
    public void disconnect()
    {
        entityManager.close();
        entityManagerFactory.close();
    }

    @Test
    public void AddJsonToDBTest() throws Exception
    {
        ClassLoader classLoader = getClass().getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream("forecasting.json");

        JSONObject forecastObject = (JSONObject)(new JSONParser()).parse(new InputStreamReader(inputStream));
        ForecastData forecastData = new ForecastData(forecastObject.toString());

//        System.out.println(forecastData.getData());

        entityTransaction.begin();
        entityManager.persist(forecastData);
        entityTransaction.commit();
    }
}
