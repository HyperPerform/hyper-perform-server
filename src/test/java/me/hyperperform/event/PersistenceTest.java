package me.hyperperform.event;

import me.hyperperform.event.Git.GitIssue;
import me.hyperperform.event.Git.GitPush;
import me.hyperperform.event.Travis.TravisEvent;
import org.junit.*;

import javax.persistence.*;
import java.util.List;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * hyperperform-system
 * Group: CodusMaximus
 * Date: 2016/07/19
 * Feature:
 */

//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(classes = PersistenceTestConfig.class)
public class PersistenceTest
{
    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;
    private EntityTransaction entityTransaction;
    private TravisEvent t;
    private GitIssue gitIssue;
    private GitPush g;

    @Before
    public void init()
    {
        entityManagerFactory = Persistence.createEntityManagerFactory("test");
        entityManager = entityManagerFactory.createEntityManager();
        entityTransaction = entityManager.getTransaction();
    }

    @Test
    public void createTravisEventTest()
    {
        System.out.println("Running Create Travis Event test...");
        t = new TravisEvent();
        t.setCommiter("Sven Fuchs");
        t.setBranch("master");
        t.setStatus("Passed");
        t.setTimestamp("2011-11-11 11:11:11.0");
        t.setRepo("minimal");

        entityManager.getTransaction().begin();
        entityManager.persist(t);
        entityManager.getTransaction().commit();
    }

    @Test
    public void createGitIssueTest()
    {
        System.out.println("Running Create Git Issue test...");
        gitIssue = new GitIssue();

        gitIssue.setIssueId(73464126);
        gitIssue.setAction("opened");
        gitIssue.setRepository("public-repo");

//        gitIssue.setCreatedAt("2016-07-28 22:42:44");
//        gitIssue.setClosedAt(null);

        gitIssue.setTimestamp("2016-07-28 22:42:44");

        gitIssue.setAssignee("Sven Fuchs");
        gitIssue.setCreatedBy("baxterthehacker");

        entityManager.getTransaction().begin();
        entityManager.persist(gitIssue);
        entityManager.getTransaction().commit();
    }

    @Test
    public void createGitPushTest()
    {
        System.out.println("Running Create Git Push test ...");

        g = new GitPush("baxterthehacker/public-repo", "2015-05-05 19:40:15.0", "baxterthehacker", 2);

        entityTransaction.begin();

		entityManager.persist(g);

		entityTransaction.commit();
    }

    @Test
    public void travisEventPojoTest()
    {
        System.out.println("Running Travis POJO test...");

        Assert.assertEquals("Not the same committer", "Sven Fuchs", t.getCommiter());
        Assert.assertEquals("Not the same branch", "master", t.getBranch());
        Assert.assertEquals("Not the same status", "Passed", t.getStatus());
        Assert.assertEquals("Not the same timestamp", "2011-11-11 11:11:11.0", t.getTimestamp());
        Assert.assertEquals("Not the same repo name", "minimal", t.getRepo());
    }

    @Test
    public void gitIssuePojoTest()
    {
        System.out.println("Running Git Issue POJO test...");

        Assert.assertEquals("Not the same issueID", 73464126, gitIssue.getIssueId());
        Assert.assertEquals("Not the same action", "opened", gitIssue.getAction());
        Assert.assertEquals("Not the same repo name", "public-repo", gitIssue.getRepository());
        Assert.assertNotNull("Timestamp cannot be null", gitIssue.getTimestamp());
        Assert.assertEquals("Not the same timestamp", "2016-07-28 22:42:44", gitIssue.getTimestamp());
        Assert.assertEquals("Not the same assignee", "Sven Fuchs", gitIssue.getAssignee());
        Assert.assertEquals("Not the same creator", "baxterthehacker", gitIssue.getCreatedBy());
    }

    @Test
    public void gitPushPojoTest()
    {
        System.out.println("Running Git Push POJO test...");

        Assert.assertEquals("Not the same repo path", "baxterthehacker/public-repo", g.getRepoName());
        Assert.assertEquals("Not the same timestamp", "2015-05-05 19:40:15.0", g.getTimestamp());
        Assert.assertEquals("Not the same git username", "baxterthehacker", g.getUsername());
        Assert.assertNotEquals("Commit size cannot be zero", 0, g.getCommitSize());
        Assert.assertEquals("Not the same commit size", 2, g.getCommitSize());
    }


    @Test
    public void gitPushQueryTest()
    {
        System.out.println("Running Git Push QueryTest ...");

        // GitPush g = new GitPush("baxterthehacker/public-repo", "2015-05-05 19:40:15.0", "baxterthehacker", 0);

        // entityTransaction.begin();

        // entityManager.persist(g);

        // entityTransaction.commit();

        Query query = entityManager.createQuery("FROM GitPush", GitPush.class);
        List<GitPush> result = query.getResultList();
        Assert.assertNotEquals(0, result.size());
        Assert.assertEquals(g.getRepository(), result.get(result.size()-1).getRepository());
        Assert.assertEquals(g.getDate(), result.get(result.size()-1).getDate());
        Assert.assertEquals(g.getUsername(), result.get(result.size()-1).getUsername());
        Assert.assertEquals(g.getCommitSize(), result.get(result.size()-1).getCommitSize());
    }

    @After
    public void closeManager()
    {
        entityManager.close();
        entityManagerFactory.close();
    }
}
