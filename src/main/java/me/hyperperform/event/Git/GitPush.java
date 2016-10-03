package me.hyperperform.event.Git;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Git Push
 *
 * @author  CodusMaximus
 * @version 1.0
 * @since   2016/07/02
 */


@Entity
@Table(name = "\"GitPush\"")
public class GitPush implements IGitEvent
{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @Column(name = "repository")
    String repository;

    @Column(name = "username")
    String username;

    @Column(name = "timestamp")
    Timestamp timestamp;

    @Column(name = "commitSize")
    int commitSize;

    @Column(name = "url")
    String url;

    @Column(name = "message")
    String message;

    public void setUsername(String username) {
        this.username = username;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public String getUrl() {
        return url;

    }

    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * Overloaded constructor for initialize the object
     *
     * @param repoName initialize the repository name
     * @param date initialize the date the event was triggered
     * @param user initialize the user who triggered the event
     * @param commit initialize the number of commits
     */
    public GitPush(String repoName, String date, String user, int commit)
    {
        setRepoName(repoName);
        setDate(Timestamp.valueOf(date));
        setUser(user);
        setCommitSize(commit);
    }

    public GitPush()
    {
    }

    public int getCommitSize() {
        return commitSize;
    }

    public void setCommitSize(int commitSize) {
        this.commitSize = commitSize;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getRepository()
    {
        return repository;
    }

    public void setRepository(String repository)
    {
        this.repository = repository;
    }

    public void setRepoName(String name)
    {
        repository = name;
    }

    public void setDate(Timestamp timestamp)
    {
        this.timestamp = timestamp;
    }

    public String getUsername()
    {
        return username;
    }

    public void setUser(String userName)
    {
        username = userName;
    }

    public String getDate()
    {
        return timestamp.toString();
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String toString()
    {
        String s = "";

        s += "Repo Name: " + repository + "\n";
        s += "Date: " + getDate() + "\n";
        s += "Pusher: " + username;

        return s;
    }


}
