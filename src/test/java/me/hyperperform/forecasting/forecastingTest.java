package me.hyperperform.forecasting;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.*;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

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
        Query query = entityManager.createQuery("FROM ForecastData ", ForecastData.class);
        List<ForecastData> result = query.getResultList();

        if (result.size() == 0)
        {
            ClassLoader classLoader = getClass().getClassLoader();
            InputStream inputStream = classLoader.getResourceAsStream("forecasting.json");

            JSONObject forecastObject = (JSONObject) (new JSONParser()).parse(new InputStreamReader(inputStream));
            ForecastData forecastData = new ForecastData(forecastObject.toString());

//        System.out.println(forecastData.getData());

            entityTransaction.begin();
            entityManager.persist(forecastData);
            entityTransaction.commit();
        }
    }
}
