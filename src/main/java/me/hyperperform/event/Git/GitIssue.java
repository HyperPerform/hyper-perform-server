package me.hyperperform.event.Git;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Git Issues POJO used to store data from github issue object. That data is then used for event processing.
 *
 * @author  Rohan
 * @version 1.0
 * @since   2016/08/25
 */
@Entity
@Table(name = "\"GitIssue\"")
public class GitIssue implements IGitEvent
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @Column(name = "issueId")
    private long issueId;

    @Column(name = "action")
    private String action;

    @Column(name = "repository")
    private String repository;

    @Column(name = "timestamp")
    private Timestamp timestamp;

    @Column(name = "assignee")
    private String assignee;

    @Column(name = "createdBy")
    private String createdBy;

    @Column(name = "url")
    private String url;

    @Column(name = "title")
    private String title;

    public GitIssue() {
    }

    /**
     * Public constructor for easy creation of object
     * @param issueId Issue Id provided by GitHub
     * @param action The type of event that has taken place i.e opened, closed or assigned
     * @param repository In which repository this event took place
     * @param timestamp Time of the event
     * @param createdBy Author of the issue
     * @param assignee Developer assigned to issue
     */
    public GitIssue(long issueId, String action, String repository, Timestamp timestamp, String createdBy, String assignee) {
        this.issueId = issueId;
        this.action = action;
        this.repository = repository;
        this.timestamp = timestamp;
        this.createdBy = createdBy;
        this.assignee = assignee;
    }

    public String getRepository() {
        return repository;
    }

    public void setRepository(String repository) {
        this.repository = repository;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getAssignee() {
        return assignee;
    }

    public void setAssignee(String assignee) {
        this.assignee = assignee;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public long getIssueId() {
        return issueId;
    }

    public void setIssueId(long issueId) {
        this.issueId = issueId;
    }

    public String getTimestamp() {
        return timestamp.toString();
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String toString()
    {
        String s = "";

        s += "issueID: " + issueId;
        s += "action: " + action;
        s += "repository: " + repository;
        s += "timestamp: " + timestamp.toString();
        s += "assignee: " + assignee;
        s += "createdBy: " + createdBy;
        s += "url: " + url;
        s += "title: " + title;

        return s;
    }
}
