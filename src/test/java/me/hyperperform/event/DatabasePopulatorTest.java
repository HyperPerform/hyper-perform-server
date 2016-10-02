package me.hyperperform.event;

import me.hyperperform.event.EntryExit.AccessEvent;
import me.hyperperform.event.Git.GitIssue;
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
import java.sql.Timestamp;
import java.util.ArrayList;

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
        ArrayList<GitIssue> issueEvents = new ArrayList<GitIssue>();
        ArrayList<AccessEvent> accessEvents = new ArrayList<AccessEvent>();

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
        gitPushes.add(new GitPush("hyperperform", "2016-02-15 11:11:11", "Rohan", 12));
        gitPushes.add(new GitPush("UbuntuScript", "2016-12-18 11:11:11", "Rohan", 6));
        gitPushes.add(new GitPush("hyperperform", "2016-07-07 11:11:11", "Rohan", 1));
        gitPushes.add(new GitPush("hyperperform", "2016-05-19 11:11:11", "Rohan", 6));
        gitPushes.add(new GitPush("hyperperform", "2016-01-11 11:11:11", "Rohan", 2));
        gitPushes.add(new GitPush("UbuntuScript", "2016-07-20 11:11:11", "Rohan", 9));
        gitPushes.add(new GitPush("hyperperform", "2016-05-22 11:11:11", "Rohan", 12));
        gitPushes.add(new GitPush("hyperperform", "2016-04-11 11:11:11", "Rohan", 11));
        gitPushes.add(new GitPush("hyperperform", "2016-01-04 11:11:11", "Rohan", 14));
        gitPushes.add(new GitPush("UbuntuScript", "2016-07-25 11:11:11", "Rohan", 7));
        gitPushes.add(new GitPush("hyperperform", "2016-01-11 11:11:11", "Rohan", 3));
        gitPushes.add(new GitPush("hyperperform", "2016-08-11 11:11:11", "Rohan", 1));

        gitPushes.add(new GitPush("hyperperform", "2016-04-11 11:11:11", "Jason", 10));
        gitPushes.add(new GitPush("hyperperform", "2016-04-11 11:11:11", "Jason", 12));
        gitPushes.add(new GitPush("UbuntuScript", "2016-04-11 11:11:11", "Jason", 6));
        gitPushes.add(new GitPush("hyperperform", "2016-04-11 11:11:11", "Jason", 1));
        gitPushes.add(new GitPush("hyperperform", "2016-04-11 11:11:11", "Jason", 6));
        gitPushes.add(new GitPush("UbuntuScript", "2016-04-11 11:11:11", "Jason", 2));
        gitPushes.add(new GitPush("hyperperform", "2016-04-11 11:11:11", "Jason", 9));
        gitPushes.add(new GitPush("hyperperform", "2016-04-11 11:11:11", "Jason", 12));
        gitPushes.add(new GitPush("hyperperform", "2016-04-11 11:11:11", "Jason", 11));
        gitPushes.add(new GitPush("UbuntuScript", "2016-04-11 11:11:11", "Jason", 14));
        gitPushes.add(new GitPush("hyperperform", "2016-04-11 11:11:11", "Jason", 7));
        gitPushes.add(new GitPush("hyperperform", "2016-04-11 11:11:11", "Jason", 3));
        gitPushes.add(new GitPush("hyperperform", "2016-04-11 11:11:11", "Jason", 1));

        gitPushes.add(new GitPush("hyperperform", "2016-04-11 11:11:11", "Avinash", 10));
        gitPushes.add(new GitPush("UbuntuScript", "2016-04-11 11:11:11", "Avinash", 12));
        gitPushes.add(new GitPush("hyperperform", "2016-04-11 11:11:11", "Avinash", 6));
        gitPushes.add(new GitPush("UbuntuScript", "2016-04-11 11:11:11", "Avinash", 1));
        gitPushes.add(new GitPush("hyperperform", "2016-04-11 11:11:11", "Avinash", 6));
        gitPushes.add(new GitPush("hyperperform", "2016-04-11 11:11:11", "Avinash", 2));
        gitPushes.add(new GitPush("hyperperform", "2016-04-11 11:11:11", "Avinash", 9));
        gitPushes.add(new GitPush("hyperperform", "2016-04-11 11:11:11", "Avinash", 12));
        gitPushes.add(new GitPush("UbuntuScript", "2016-04-11 11:11:11", "Avinash", 11));
        gitPushes.add(new GitPush("hyperperform", "2016-04-11 11:11:11", "Avinash", 14));
        gitPushes.add(new GitPush("hyperperform", "2016-04-11 11:11:11", "Avinash", 7));
        gitPushes.add(new GitPush("hyperperform", "2016-04-11 11:11:11", "Avinash", 3));
        gitPushes.add(new GitPush("hyperperform", "2016-04-11 11:11:11", "Avinash", 1));

        gitPushes.add(new GitPush("hyperperform", "2016-04-11 11:11:11", "Claudio", 10));
        gitPushes.add(new GitPush("hyperperform", "2016-04-11 11:11:11", "Claudio", 12));
        gitPushes.add(new GitPush("UbuntuScript", "2016-04-11 11:11:11", "Claudio", 6));
        gitPushes.add(new GitPush("hyperperform", "2016-04-11 11:11:11", "Claudio", 1));
        gitPushes.add(new GitPush("hyperperform", "2016-04-11 11:11:11", "Claudio", 6));
        gitPushes.add(new GitPush("hyperperform", "2016-04-11 11:11:11", "Claudio", 2));
        gitPushes.add(new GitPush("UbuntuScript", "2016-04-11 11:11:11", "Claudio", 9));
        gitPushes.add(new GitPush("hyperperform", "2016-04-11 11:11:11", "Claudio", 12));
        gitPushes.add(new GitPush("hyperperform", "2016-04-11 11:11:11", "Claudio", 11));
        gitPushes.add(new GitPush("hyperperform", "2016-04-11 11:11:11", "Claudio", 14));
        gitPushes.add(new GitPush("hyperperform", "2016-04-11 11:11:11", "Claudio", 7));
        gitPushes.add(new GitPush("hyperperform", "2016-04-11 11:11:11", "Claudio", 3));
        gitPushes.add(new GitPush("UbuntuScript", "2016-04-11 11:11:11", "Claudio", 1));

        issueEvents.add(new GitIssue(1, "assigned", "hyperperform", Timestamp.valueOf("2016-04-11 11:11:11"), "Jack", "Rohan"));
        issueEvents.add(new GitIssue(1, "closed", "hyperperform", Timestamp.valueOf("2016-04-11 11:11:11"), "Jack", "Rohan"));
        issueEvents.add(new GitIssue(2, "assigned", "hyperperform", Timestamp.valueOf("2016-04-11 11:11:11"), "Jack", "Rohan"));
        issueEvents.add(new GitIssue(3, "assigned", "hyperperform", Timestamp.valueOf("2016-04-11 11:11:11"), "Jack", "Rohan"));
        issueEvents.add(new GitIssue(4, "assigned", "ubuntu", Timestamp.valueOf("2016-04-11 11:11:11"), "Jack", "Rohan"));
        issueEvents.add(new GitIssue(5, "assigned", "hyperperform", Timestamp.valueOf("2016-04-11 11:11:11"), "Jack", "Rohan"));
        issueEvents.add(new GitIssue(6, "assigned", "ubuntu", Timestamp.valueOf("2016-04-11 11:11:11"), "Jack", "Rohan"));
        issueEvents.add(new GitIssue(7, "assigned", "hyperperform", Timestamp.valueOf("2016-04-11 11:11:11"), "Jack", "Rohan"));
        issueEvents.add(new GitIssue(8, "assigned", "ubuntu", Timestamp.valueOf("2016-04-11 11:11:11"), "Jack", "Rohan"));
        issueEvents.add(new GitIssue(9, "assigned", "hyperperform", Timestamp.valueOf("2016-04-11 11:11:11"), "Jack", "Rohan"));
        issueEvents.add(new GitIssue(2, "closed", "hyperperform", Timestamp.valueOf("2016-04-11 11:11:11"), "Jack", "Rohan"));
        issueEvents.add(new GitIssue(10, "assigned", "hyperperform", Timestamp.valueOf("2016-04-11 11:11:11"), "Jack", "Rohan"));
        issueEvents.add(new GitIssue(11, "assigned", "hyperperform", Timestamp.valueOf("2016-04-11 11:11:11"), "Jack", "Rohan"));
        issueEvents.add(new GitIssue(11, "closed", "hyperperform", Timestamp.valueOf("2016-04-11 11:11:11"), "Jack", "Rohan"));
        issueEvents.add(new GitIssue(12, "assigned", "hyperperform", Timestamp.valueOf("2016-04-11 11:11:11"), "Jack", "Rohan"));
        issueEvents.add(new GitIssue(13, "assigned", "ubuntu", Timestamp.valueOf("2016-04-11 11:11:11"), "Jack", "Rohan"));
        issueEvents.add(new GitIssue(14, "assigned", "hyperperform", Timestamp.valueOf("2016-04-11 11:11:11"), "Jack", "Rohan"));
        issueEvents.add(new GitIssue(14, "closed", "hyperperform", Timestamp.valueOf("2016-04-11 11:11:11"), "Jack", "Rohan"));
        issueEvents.add(new GitIssue(15, "assigned", "hyperperform", Timestamp.valueOf("2016-04-11 11:11:11"), "Jack", "Rohan"));
        issueEvents.add(new GitIssue(16, "assigned", "hyperperform", Timestamp.valueOf("2016-04-11 11:11:11"), "Jack", "Rohan"));

        accessEvents.add(new AccessEvent("rohan.chhipa@live.com", "rohanc", "ComboSmart", "Rohan", "Chhipa", "2016-01-02 08:10:10.0", (long) 0));
        accessEvents.add(new AccessEvent("rohan.chhipa@live.com", "rohanc", "ComboSmart", "Rohan", "Chhipa", "2016-01-02 11:10:10.0", (long) 0));
        accessEvents.add(new AccessEvent("rohan.chhipa@live.com", "rohanc", "ComboSmart", "Rohan", "Chhipa", "2016-01-02 12:10:10.0", (long) 0));
        accessEvents.add(new AccessEvent("rohan.chhipa@live.com", "rohanc", "ComboSmart", "Rohan", "Chhipa", "2016-01-02 18:10:10.0", (long) 0));
        accessEvents.add(new AccessEvent("rohan.chhipa@live.com", "rohanc", "ComboSmart", "Rohan", "Chhipa", "2016-03-23 10:10:10.0", (long) 0));
        accessEvents.add(new AccessEvent("rohan.chhipa@live.com", "rohanc", "ComboSmart", "Rohan", "Chhipa", "2016-03-23 19:10:10.0", (long) 0));
        accessEvents.add(new AccessEvent("rohan.chhipa@live.com", "rohanc", "ComboSmart", "Rohan", "Chhipa", "2016-05-29 07:10:10.0", (long) 0));
        accessEvents.add(new AccessEvent("rohan.chhipa@live.com", "rohanc", "ComboSmart", "Rohan", "Chhipa", "2016-05-29 22:10:10.0", (long) 0));
        accessEvents.add(new AccessEvent("rohan.chhipa@live.com", "rohanc", "ComboSmart", "Rohan", "Chhipa", "2016-06-15 10:10:10.0", (long) 0));
        accessEvents.add(new AccessEvent("rohan.chhipa@live.com", "rohanc", "ComboSmart", "Rohan", "Chhipa", "2016-06-15 23:10:10.0", (long) 0));
        accessEvents.add(new AccessEvent("rohan.chhipa@live.com", "rohanc", "ComboSmart", "Rohan", "Chhipa", "2016-06-29 06:10:10.0", (long) 0));
        accessEvents.add(new AccessEvent("rohan.chhipa@live.com", "rohanc", "ComboSmart", "Rohan", "Chhipa", "2016-06-29 16:10:10.0", (long) 0));
        accessEvents.add(new AccessEvent("rohan.chhipa@live.com", "rohanc", "ComboSmart", "Rohan", "Chhipa", "2016-09-23 10:10:10.0", (long) 0));
        accessEvents.add(new AccessEvent("rohan.chhipa@live.com", "rohanc", "ComboSmart", "Rohan", "Chhipa", "2016-09-23 12:10:10.0", (long) 0));
        accessEvents.add(new AccessEvent("rohan.chhipa@live.com", "rohanc", "ComboSmart", "Rohan", "Chhipa", "2016-09-23 13:10:10.0", (long) 0));
        accessEvents.add(new AccessEvent("rohan.chhipa@live.com", "rohanc", "ComboSmart", "Rohan", "Chhipa", "2016-09-23 16:10:10.0", (long) 0));
        accessEvents.add(new AccessEvent("rohan.chhipa@live.com", "rohanc", "ComboSmart", "Rohan", "Chhipa", "2016-10-23 10:10:10.0", (long) 0));
        accessEvents.add(new AccessEvent("rohan.chhipa@live.com", "rohanc", "ComboSmart", "Rohan", "Chhipa", "2016-10-23 13:10:10.0", (long) 0));
        accessEvents.add(new AccessEvent("rohan.chhipa@live.com", "rohanc", "ComboSmart", "Rohan", "Chhipa", "2016-10-29 10:10:10.0", (long) 0));
        accessEvents.add(new AccessEvent("rohan.chhipa@live.com", "rohanc", "ComboSmart", "Rohan", "Chhipa", "2016-10-29 12:10:10.0", (long) 0));
        accessEvents.add(new AccessEvent("rohan.chhipa@live.com", "rohanc", "ComboSmart", "Rohan", "Chhipa", "2016-12-23 11:10:10.0", (long) 0));
        accessEvents.add(new AccessEvent("rohan.chhipa@live.com", "rohanc", "ComboSmart", "Rohan", "Chhipa", "2016-12-23 12:10:10.0", (long) 0));
        accessEvents.add(new AccessEvent("rohan.chhipa@live.com", "rohanc", "ComboSmart", "Rohan", "Chhipa", "2016-12-29 19:10:10.0", (long) 0));
        accessEvents.add(new AccessEvent("rohan.chhipa@live.com", "rohanc", "ComboSmart", "Rohan", "Chhipa", "2016-12-29 20:10:10.0", (long) 0));

        accessEvents.add(new AccessEvent("avinash.singh@gmail.com", "avinash", "ComboSmart", "Avinash", "Singh", "2016-01-02 08:10:10.0", (long) 0));
        accessEvents.add(new AccessEvent("avinash.singh@gmail.com", "avinash", "ComboSmart", "Avinash", "Singh", "2016-01-02 11:10:10.0", (long) 0));
        accessEvents.add(new AccessEvent("avinash.singh@gmail.com", "avinash", "ComboSmart", "Avinash", "Singh", "2016-01-02 12:10:10.0", (long) 0));
        accessEvents.add(new AccessEvent("avinash.singh@gmail.com", "avinash", "ComboSmart", "Avinash", "Singh", "2016-01-02 18:10:10.0", (long) 0));
        accessEvents.add(new AccessEvent("avinash.singh@gmail.com", "avinash", "ComboSmart", "Avinash", "Singh", "2016-03-23 10:10:10.0", (long) 0));
        accessEvents.add(new AccessEvent("avinash.singh@gmail.com", "avinash", "ComboSmart", "Avinash", "Singh", "2016-03-23 19:10:10.0", (long) 0));
        accessEvents.add(new AccessEvent("avinash.singh@gmail.com", "avinash", "ComboSmart", "Avinash", "Singh", "2016-05-29 07:10:10.0", (long) 0));
        accessEvents.add(new AccessEvent("avinash.singh@gmail.com", "avinash", "ComboSmart", "Avinash", "Singh", "2016-05-29 22:10:10.0", (long) 0));
        accessEvents.add(new AccessEvent("avinash.singh@gmail.com", "avinash", "ComboSmart", "Avinash", "Singh", "2016-06-15 10:10:10.0", (long) 0));
        accessEvents.add(new AccessEvent("avinash.singh@gmail.com", "avinash", "ComboSmart", "Avinash", "Singh", "2016-06-15 23:10:10.0", (long) 0));
        accessEvents.add(new AccessEvent("avinash.singh@gmail.com", "avinash", "ComboSmart", "Avinash", "Singh", "2016-06-29 06:10:10.0", (long) 0));
        accessEvents.add(new AccessEvent("avinash.singh@gmail.com", "avinash", "ComboSmart", "Avinash", "Singh", "2016-06-29 16:10:10.0", (long) 0));
        accessEvents.add(new AccessEvent("avinash.singh@gmail.com", "avinash", "ComboSmart", "Avinash", "Singh", "2016-09-23 10:10:10.0", (long) 0));
        accessEvents.add(new AccessEvent("avinash.singh@gmail.com", "avinash", "ComboSmart", "Avinash", "Singh", "2016-09-23 12:10:10.0", (long) 0));
        accessEvents.add(new AccessEvent("avinash.singh@gmail.com", "avinash", "ComboSmart", "Avinash", "Singh", "2016-09-23 13:10:10.0", (long) 0));
        accessEvents.add(new AccessEvent("avinash.singh@gmail.com", "avinash", "ComboSmart", "Avinash", "Singh", "2016-09-23 16:10:10.0", (long) 0));
        accessEvents.add(new AccessEvent("avinash.singh@gmail.com", "avinash", "ComboSmart", "Avinash", "Singh", "2016-10-23 10:10:10.0", (long) 0));
        accessEvents.add(new AccessEvent("avinash.singh@gmail.com", "avinash", "ComboSmart", "Avinash", "Singh", "2016-10-23 13:10:10.0", (long) 0));
        accessEvents.add(new AccessEvent("avinash.singh@gmail.com", "avinash", "ComboSmart", "Avinash", "Singh", "2016-10-29 10:10:10.0", (long) 0));
        accessEvents.add(new AccessEvent("avinash.singh@gmail.com", "avinash", "ComboSmart", "Avinash", "Singh", "2016-10-29 12:10:10.0", (long) 0));
        accessEvents.add(new AccessEvent("avinash.singh@gmail.com", "avinash", "ComboSmart", "Avinash", "Singh", "2016-12-23 11:10:10.0", (long) 0));
        accessEvents.add(new AccessEvent("avinash.singh@gmail.com", "avinash", "ComboSmart", "Avinash", "Singh", "2016-12-23 12:10:10.0", (long) 0));
        accessEvents.add(new AccessEvent("avinash.singh@gmail.com", "avinash", "ComboSmart", "Avinash", "Singh", "2016-12-29 19:10:10.0", (long) 0));
        accessEvents.add(new AccessEvent("avinash.singh@gmail.com", "avinash", "ComboSmart", "Avinash", "Singh", "2016-12-29 20:10:10.0", (long) 0));

        accessEvents.add(new AccessEvent("jason.gordon@gmail.com", "Jason", "ComboSmart", "Jason", "Gordon", "2016-01-02 08:10:10.0", (long) 0));
        accessEvents.add(new AccessEvent("jason.gordon@gmail.com", "Jason", "ComboSmart", "Jason", "Gordon", "2016-01-02 11:10:10.0", (long) 0));
        accessEvents.add(new AccessEvent("jason.gordon@gmail.com", "Jason", "ComboSmart", "Jason", "Gordon", "2016-01-02 12:10:10.0", (long) 0));
        accessEvents.add(new AccessEvent("jason.gordon@gmail.com", "Jason", "ComboSmart", "Jason", "Gordon", "2016-01-02 18:10:10.0", (long) 0));
        accessEvents.add(new AccessEvent("jason.gordon@gmail.com", "Jason", "ComboSmart", "Jason", "Gordon", "2016-03-23 10:10:10.0", (long) 0));
        accessEvents.add(new AccessEvent("jason.gordon@gmail.com", "Jason", "ComboSmart", "Jason", "Gordon", "2016-03-23 19:10:10.0", (long) 0));
        accessEvents.add(new AccessEvent("jason.gordon@gmail.com", "Jason", "ComboSmart", "Jason", "Gordon", "2016-05-29 07:10:10.0", (long) 0));
        accessEvents.add(new AccessEvent("jason.gordon@gmail.com", "Jason", "ComboSmart", "Jason", "Gordon", "2016-05-29 22:10:10.0", (long) 0));
        accessEvents.add(new AccessEvent("jason.gordon@gmail.com", "Jason", "ComboSmart", "Jason", "Gordon", "2016-06-15 10:10:10.0", (long) 0));
        accessEvents.add(new AccessEvent("jason.gordon@gmail.com", "Jason", "ComboSmart", "Jason", "Gordon", "2016-06-15 23:10:10.0", (long) 0));
        accessEvents.add(new AccessEvent("jason.gordon@gmail.com", "Jason", "ComboSmart", "Jason", "Gordon", "2016-06-29 06:10:10.0", (long) 0));
        accessEvents.add(new AccessEvent("jason.gordon@gmail.com", "Jason", "ComboSmart", "Jason", "Gordon", "2016-06-29 16:10:10.0", (long) 0));
        accessEvents.add(new AccessEvent("jason.gordon@gmail.com", "Jason", "ComboSmart", "Jason", "Gordon", "2016-09-23 10:10:10.0", (long) 0));
        accessEvents.add(new AccessEvent("jason.gordon@gmail.com", "Jason", "ComboSmart", "Jason", "Gordon", "2016-09-23 12:10:10.0", (long) 0));
        accessEvents.add(new AccessEvent("jason.gordon@gmail.com", "Jason", "ComboSmart", "Jason", "Gordon", "2016-09-23 13:10:10.0", (long) 0));
        accessEvents.add(new AccessEvent("jason.gordon@gmail.com", "Jason", "ComboSmart", "Jason", "Gordon", "2016-09-23 16:10:10.0", (long) 0));
        accessEvents.add(new AccessEvent("jason.gordon@gmail.com", "Jason", "ComboSmart", "Jason", "Gordon", "2016-10-23 10:10:10.0", (long) 0));
        accessEvents.add(new AccessEvent("jason.gordon@gmail.com", "Jason", "ComboSmart", "Jason", "Gordon", "2016-10-23 13:10:10.0", (long) 0));
        accessEvents.add(new AccessEvent("jason.gordon@gmail.com", "Jason", "ComboSmart", "Jason", "Gordon", "2016-10-29 10:10:10.0", (long) 0));
        accessEvents.add(new AccessEvent("jason.gordon@gmail.com", "Jason", "ComboSmart", "Jason", "Gordon", "2016-10-29 12:10:10.0", (long) 0));
        accessEvents.add(new AccessEvent("jason.gordon@gmail.com", "Jason", "ComboSmart", "Jason", "Gordon", "2016-12-23 11:10:10.0", (long) 0));
        accessEvents.add(new AccessEvent("jason.gordon@gmail.com", "Jason", "ComboSmart", "Jason", "Gordon", "2016-12-23 12:10:10.0", (long) 0));
        accessEvents.add(new AccessEvent("jason.gordon@gmail.com", "Jason", "ComboSmart", "Jason", "Gordon", "2016-12-29 19:10:10.0", (long) 0));
        accessEvents.add(new AccessEvent("jason.gordon@gmail.com", "Jason", "ComboSmart", "Jason", "Gordon", "2016-12-29 20:10:10.0", (long) 0));

        accessEvents.add(new AccessEvent("claudio.da.silva@live.com", "Claudio", "ComboSmart", "Claudio", "da Silva", "2016-01-02 08:10:10.0", (long) 0));
        accessEvents.add(new AccessEvent("claudio.da.silva@live.com", "Claudio", "ComboSmart", "Claudio", "da Silva", "2016-01-02 11:10:10.0", (long) 0));
        accessEvents.add(new AccessEvent("claudio.da.silva@live.com", "Claudio", "ComboSmart", "Claudio", "da Silva", "2016-01-02 12:10:10.0", (long) 0));
        accessEvents.add(new AccessEvent("claudio.da.silva@live.com", "Claudio", "ComboSmart", "Claudio", "da Silva", "2016-01-02 18:10:10.0", (long) 0));
        accessEvents.add(new AccessEvent("claudio.da.silva@live.com", "Claudio", "ComboSmart", "Claudio", "da Silva", "2016-03-23 10:10:10.0", (long) 0));
        accessEvents.add(new AccessEvent("claudio.da.silva@live.com", "Claudio", "ComboSmart", "Claudio", "da Silva", "2016-03-23 19:10:10.0", (long) 0));
        accessEvents.add(new AccessEvent("claudio.da.silva@live.com", "Claudio", "ComboSmart", "Claudio", "da Silva", "2016-05-29 07:10:10.0", (long) 0));
        accessEvents.add(new AccessEvent("claudio.da.silva@live.com", "Claudio", "ComboSmart", "Claudio", "da Silva", "2016-05-29 22:10:10.0", (long) 0));
        accessEvents.add(new AccessEvent("claudio.da.silva@live.com", "Claudio", "ComboSmart", "Claudio", "da Silva", "2016-06-15 10:10:10.0", (long) 0));
        accessEvents.add(new AccessEvent("claudio.da.silva@live.com", "Claudio", "ComboSmart", "Claudio", "da Silva", "2016-06-15 23:10:10.0", (long) 0));
        accessEvents.add(new AccessEvent("claudio.da.silva@live.com", "Claudio", "ComboSmart", "Claudio", "da Silva", "2016-06-29 06:10:10.0", (long) 0));
        accessEvents.add(new AccessEvent("claudio.da.silva@live.com", "Claudio", "ComboSmart", "Claudio", "da Silva", "2016-06-29 16:10:10.0", (long) 0));
        accessEvents.add(new AccessEvent("claudio.da.silva@live.com", "Claudio", "ComboSmart", "Claudio", "da Silva", "2016-09-23 10:10:10.0", (long) 0));
        accessEvents.add(new AccessEvent("claudio.da.silva@live.com", "Claudio", "ComboSmart", "Claudio", "da Silva", "2016-09-23 12:10:10.0", (long) 0));
        accessEvents.add(new AccessEvent("claudio.da.silva@live.com", "Claudio", "ComboSmart", "Claudio", "da Silva", "2016-09-23 13:10:10.0", (long) 0));
        accessEvents.add(new AccessEvent("claudio.da.silva@live.com", "Claudio", "ComboSmart", "Claudio", "da Silva", "2016-09-23 16:10:10.0", (long) 0));
        accessEvents.add(new AccessEvent("claudio.da.silva@live.com", "Claudio", "ComboSmart", "Claudio", "da Silva", "2016-10-23 10:10:10.0", (long) 0));
        accessEvents.add(new AccessEvent("claudio.da.silva@live.com", "Claudio", "ComboSmart", "Claudio", "da Silva", "2016-10-23 13:10:10.0", (long) 0));
        accessEvents.add(new AccessEvent("claudio.da.silva@live.com", "Claudio", "ComboSmart", "Claudio", "da Silva", "2016-10-29 10:10:10.0", (long) 0));
        accessEvents.add(new AccessEvent("claudio.da.silva@live.com", "Claudio", "ComboSmart", "Claudio", "da Silva", "2016-10-29 12:10:10.0", (long) 0));
        accessEvents.add(new AccessEvent("claudio.da.silva@live.com", "Claudio", "ComboSmart", "Claudio", "da Silva", "2016-12-23 11:10:10.0", (long) 0));
        accessEvents.add(new AccessEvent("claudio.da.silva@live.com", "Claudio", "ComboSmart", "Claudio", "da Silva", "2016-12-23 12:10:10.0", (long) 0));
        accessEvents.add(new AccessEvent("claudio.da.silva@live.com", "Claudio", "ComboSmart", "Claudio", "da Silva", "2016-12-29 19:10:10.0", (long) 0));
        accessEvents.add(new AccessEvent("claudio.da.silva@live.com", "Claudio", "ComboSmart", "Claudio", "da Silva", "2016-12-29 20:10:10.0", (long) 0));

        entityTransaction.begin();

        for (int k = 0; k < gitPushes.size(); k++)
        {
            gitPushes.get(k).setUrl("https://github.com/HyperPerform/hyper-perform-web-application");
            entityManager.persist(gitPushes.get(k));
        }
        for (int k = 0; k < travisEvents.size(); k++)
            entityManager.persist(travisEvents.get(k));

        for (int k = 0; k < issueEvents.size(); k++)
        {
            issueEvents.get(k).setUrl("https://github.com/HyperPerform/hyper-perform-server/issues");
            issueEvents.get(k).setTitle("<<Mock Title Here>>");
            entityManager.persist(issueEvents.get(k));
        }
        for (int k = 0; k < accessEvents.size(); k++)
            entityManager.persist(accessEvents.get(k));

        entityTransaction.commit();
    }

}