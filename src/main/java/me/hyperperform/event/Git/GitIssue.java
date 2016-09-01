package me.hyperperform.event.Git;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by rohan on 2016/08/25.
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

    public GitIssue() {
    }

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
}
