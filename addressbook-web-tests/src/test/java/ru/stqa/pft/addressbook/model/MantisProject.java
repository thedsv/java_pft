package ru.stqa.pft.addressbook.model;

public class MantisProject {

  private int id;
  private String name;

  public int getId() {
    return id;
  }

  public MantisProject withId(int id) {
    this.id = id;
    return this;
  }

  public String getName() {
    return name;
  }

  public MantisProject withName(String name) {
    this.name = name;
    return this;
  }
}
