package me.hyperperform.event;

import me.hyperperform.event.Git.GitPush;
import me.hyperperform.event.Travis.TravisEvent;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.ArrayList;

/**
 * Created by rohan on 2016/08/22.
 */
public class DatabasePopulatorTest
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

    @Ignore
    @Test
    public void databasePopTest()
    {
        ArrayList<GitPush> gitPushes = new ArrayList<GitPush>();
        ArrayList<TravisEvent> travisEvents = new ArrayList<TravisEvent>();

        travisEvents.add(new TravisEvent("hyperperform", "develop", "Rohan", "Passed", "2016-01-01 11:11:11"));
        travisEvents.add(new TravisEvent("hyperperform", "feature/Git", "Rohan", "Failed", "2016-01-01 16:10:00"));
        travisEvents.add(new TravisEvent("hyperperform", "feature/Git", "Rohan", "Passed", "2016-01-04 11:11:11"));
        travisEvents.add(new TravisEvent("hyperperform", "develop", "Rohan", "Passed", "2016-03-05 11:11:11"));
        travisEvents.add(new TravisEvent("hyperperform", "develop", "Rohan", "Passed", "2016-03-07 11:11:11"));
        travisEvents.add(new TravisEvent("UbuntuScript", "master", "Rohan", "Passed", "2016-04-03 11:11:11"));
        travisEvents.add(new TravisEvent("hyperperform", "develop", "Rohan", "Passed", "2016-04-06 11:11:11"));
        travisEvents.add(new TravisEvent("UbuntuScript", "master", "Rohan", "Failed", "2016-04-10 11:11:11"));
        travisEvents.add(new TravisEvent("hyperperform", "develop", "Rohan", "Passed", "2016-04-11 11:11:11"));

        travisEvents.add(new TravisEvent("hyperperform", "develop", "Jason", "Passed", "2016-01-01 11:11:11"));
        travisEvents.add(new TravisEvent("hyperperform", "feature/Git", "Jason", "Passed", "2016-01-01 16:10:00"));
        travisEvents.add(new TravisEvent("hyperperform", "feature/Git", "Jason", "Passed", "2016-01-04 10:11:11"));
        travisEvents.add(new TravisEvent("hyperperform", "develop", "Jason", "Passed", "2016-03-05 11:11:15"));
        travisEvents.add(new TravisEvent("hyperperform", "develop", "Jason", "Passed", "2016-03-07 11:13:11"));
        travisEvents.add(new TravisEvent("UbuntuScript", "master", "Jason", "Failed", "2016-04-03 11:18:11"));
        travisEvents.add(new TravisEvent("hyperperform", "develop", "Jason", "Passed", "2016-04-06 13:11:11"));
        travisEvents.add(new TravisEvent("UbuntuScript", "master", "Jason", "Failed", "2016-04-10 12:11:11"));
        travisEvents.add(new TravisEvent("hyperperform", "develop", "Jason", "Passed", "2016-04-11 06:11:11"));

        travisEvents.add(new TravisEvent("hyperperform", "develop", "Avinash", "Passed", "2016-01-01 11:11:11"));
        travisEvents.add(new TravisEvent("hyperperform", "feature/Git", "Avinash", "Failed", "2016-01-01 16:10:00"));
        travisEvents.add(new TravisEvent("hyperperform", "feature/Git", "Avinash", "Passed", "2016-01-04 11:11:11"));
        travisEvents.add(new TravisEvent("hyperperform", "develop", "Avinash", "Failed", "2016-03-05 11:11:11"));
        travisEvents.add(new TravisEvent("hyperperform", "develop", "Avinash", "Passed", "2016-03-07 11:11:11"));
        travisEvents.add(new TravisEvent("UbuntuScript", "master", "Avinash", "Passed", "2016-04-03 11:11:11"));
        travisEvents.add(new TravisEvent("hyperperform", "develop", "Avinash", "Passed", "2016-04-06 11:11:11"));
        travisEvents.add(new TravisEvent("UbuntuScript", "master", "Avinash", "Failed", "2016-04-10 11:11:11"));
        travisEvents.add(new TravisEvent("hyperperform", "develop", "Avinash", "Passed", "2016-04-11 11:11:11"));

        travisEvents.add(new TravisEvent("hyperperform", "develop", "Claudio", "Passed", "2016-01-01 11:11:11"));
        travisEvents.add(new TravisEvent("hyperperform", "feature/Git", "Claudio", "Failed", "2016-01-01 16:10:00"));
        travisEvents.add(new TravisEvent("hyperperform", "feature/Git", "Claudio", "Passed", "2016-01-04 11:11:11"));
        travisEvents.add(new TravisEvent("hyperperform", "develop", "Claudio", "Passed", "2016-03-05 11:11:11"));
        travisEvents.add(new TravisEvent("hyperperform", "develop", "Claudio", "Failed", "2016-03-07 11:11:11"));
        travisEvents.add(new TravisEvent("UbuntuScript", "master", "Claudio", "Passed", "2016-04-03 11:11:11"));
        travisEvents.add(new TravisEvent("hyperperform", "develop", "Claudio", "Failed", "2016-04-06 11:11:11"));
        travisEvents.add(new TravisEvent("UbuntuScript", "master", "Claudio", "Failed", "2016-04-10 11:11:11"));
        travisEvents.add(new TravisEvent("hyperperform", "develop", "Claudio", "Passed", "2016-04-11 11:11:11"));

        gitPushes.add(new GitPush("hyperperform", "2016-04-11 11:11:11", "Rohan", 10));
        gitPushes.add(new GitPush("hyperperform", "2016-04-11 11:11:11", "Rohan", 12));
        gitPushes.add(new GitPush("hyperperform", "2016-04-11 11:11:11", "Rohan", 6));
        gitPushes.add(new GitPush("hyperperform", "2016-04-11 11:11:11", "Rohan", 1));
        gitPushes.add(new GitPush("hyperperform", "2016-04-11 11:11:11", "Rohan", 6));
        gitPushes.add(new GitPush("hyperperform", "2016-04-11 11:11:11", "Rohan", 2));
        gitPushes.add(new GitPush("hyperperform", "2016-04-11 11:11:11", "Rohan", 9));
        gitPushes.add(new GitPush("hyperperform", "2016-04-11 11:11:11", "Rohan", 12));
        gitPushes.add(new GitPush("hyperperform", "2016-04-11 11:11:11", "Rohan", 11));
        gitPushes.add(new GitPush("hyperperform", "2016-04-11 11:11:11", "Rohan", 14));
        gitPushes.add(new GitPush("hyperperform", "2016-04-11 11:11:11", "Rohan", 7));
        gitPushes.add(new GitPush("hyperperform", "2016-04-11 11:11:11", "Rohan", 3));
        gitPushes.add(new GitPush("hyperperform", "2016-04-11 11:11:11", "Rohan", 1));

        gitPushes.add(new GitPush("hyperperform", "2016-04-11 11:11:11", "Jason", 10));
        gitPushes.add(new GitPush("hyperperform", "2016-04-11 11:11:11", "Jason", 12));
        gitPushes.add(new GitPush("hyperperform", "2016-04-11 11:11:11", "Jason", 6));
        gitPushes.add(new GitPush("hyperperform", "2016-04-11 11:11:11", "Jason", 1));
        gitPushes.add(new GitPush("hyperperform", "2016-04-11 11:11:11", "Jason", 6));
        gitPushes.add(new GitPush("hyperperform", "2016-04-11 11:11:11", "Jason", 2));
        gitPushes.add(new GitPush("hyperperform", "2016-04-11 11:11:11", "Jason", 9));
        gitPushes.add(new GitPush("hyperperform", "2016-04-11 11:11:11", "Jason", 12));
        gitPushes.add(new GitPush("hyperperform", "2016-04-11 11:11:11", "Jason", 11));
        gitPushes.add(new GitPush("hyperperform", "2016-04-11 11:11:11", "Jason", 14));
        gitPushes.add(new GitPush("hyperperform", "2016-04-11 11:11:11", "Jason", 7));
        gitPushes.add(new GitPush("hyperperform", "2016-04-11 11:11:11", "Jason", 3));
        gitPushes.add(new GitPush("hyperperform", "2016-04-11 11:11:11", "Jason", 1));

        gitPushes.add(new GitPush("hyperperform", "2016-04-11 11:11:11", "Avinash", 10));
        gitPushes.add(new GitPush("hyperperform", "2016-04-11 11:11:11", "Avinash", 12));
        gitPushes.add(new GitPush("hyperperform", "2016-04-11 11:11:11", "Avinash", 6));
        gitPushes.add(new GitPush("hyperperform", "2016-04-11 11:11:11", "Avinash", 1));
        gitPushes.add(new GitPush("hyperperform", "2016-04-11 11:11:11", "Avinash", 6));
        gitPushes.add(new GitPush("hyperperform", "2016-04-11 11:11:11", "Avinash", 2));
        gitPushes.add(new GitPush("hyperperform", "2016-04-11 11:11:11", "Avinash", 9));
        gitPushes.add(new GitPush("hyperperform", "2016-04-11 11:11:11", "Avinash", 12));
        gitPushes.add(new GitPush("hyperperform", "2016-04-11 11:11:11", "Avinash", 11));
        gitPushes.add(new GitPush("hyperperform", "2016-04-11 11:11:11", "Avinash", 14));
        gitPushes.add(new GitPush("hyperperform", "2016-04-11 11:11:11", "Avinash", 7));
        gitPushes.add(new GitPush("hyperperform", "2016-04-11 11:11:11", "Avinash", 3));
        gitPushes.add(new GitPush("hyperperform", "2016-04-11 11:11:11", "Avinash", 1));

        gitPushes.add(new GitPush("hyperperform", "2016-04-11 11:11:11", "Claudio", 10));
        gitPushes.add(new GitPush("hyperperform", "2016-04-11 11:11:11", "Claudio", 12));
        gitPushes.add(new GitPush("hyperperform", "2016-04-11 11:11:11", "Claudio", 6));
        gitPushes.add(new GitPush("hyperperform", "2016-04-11 11:11:11", "Claudio", 1));
        gitPushes.add(new GitPush("hyperperform", "2016-04-11 11:11:11", "Claudio", 6));
        gitPushes.add(new GitPush("hyperperform", "2016-04-11 11:11:11", "Claudio", 2));
        gitPushes.add(new GitPush("hyperperform", "2016-04-11 11:11:11", "Claudio", 9));
        gitPushes.add(new GitPush("hyperperform", "2016-04-11 11:11:11", "Claudio", 12));
        gitPushes.add(new GitPush("hyperperform", "2016-04-11 11:11:11", "Claudio", 11));
        gitPushes.add(new GitPush("hyperperform", "2016-04-11 11:11:11", "Claudio", 14));
        gitPushes.add(new GitPush("hyperperform", "2016-04-11 11:11:11", "Claudio", 7));
        gitPushes.add(new GitPush("hyperperform", "2016-04-11 11:11:11", "Claudio", 3));
        gitPushes.add(new GitPush("hyperperform", "2016-04-11 11:11:11", "Claudio", 1));

        entityTransaction.begin();

        for (int k = 0; k < gitPushes.size(); k++)
            entityManager.persist(gitPushes.get(k));

        for (int k = 0; k < travisEvents.size(); k++)
            entityManager.persist(travisEvents.get(k));

        entityTransaction.commit();
    }

}
