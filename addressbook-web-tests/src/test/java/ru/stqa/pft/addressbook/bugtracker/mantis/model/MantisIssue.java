package ru.stqa.pft.addressbook.bugtracker.mantis.model;

public class MantisIssue {

  private int id;
  private String summary;
  private String description;
  private MantisProject project;

  public int getId() {
    return id;
  }

  public MantisIssue withId(int id) {
    this.id = id;
    return this;
  }

  public String getSummary() {
    return summary;
  }

  public MantisIssue withSummary(String summary) {
    this.summary = summary;
    return this;
  }

  public String getDescription() {
    return description;
  }

  public MantisIssue withDescription(String description) {
    this.description = description;
    return this;
  }

  public MantisProject getProject() {
    return project;
  }

  public MantisIssue withProject(MantisProject project) {
    this.project = project;
    return this;
  }
}
