package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactGroupAdditionTests extends TestBase {

  ContactData contact;
  GroupData group;
  private boolean groupCreated;
  private boolean contactCreated;
  private String newGroupName = "GroupAdditionTest";

  @BeforeMethod
  public void ensurePreconditions() {
    //проверяем наличие в БД контактов
    if (app.db().contacts().size() == 0) {
      app.goTo().homePage();
      app.contact().create(new ContactData().withFirstName("4GroupAddition").withLastName("Contact"));
      contact = app.db().contacts().iterator().next();
      contactCreated = true;
    }
    //проверяем наличие в БД групп
    if (app.db().groups().size() == 0) {
      app.goTo().groupPage();
      app.group().create(new GroupData().withName(newGroupName));
      group = app.db().groups().iterator().next();
      groupCreated = true;
    }
    //если контакт или группа не были созданы, ищем отсутствующую в БД пару контакт-группа
    if (!(contactCreated && groupCreated)) {
      for (GroupData g : app.db().groups()) {
        for (ContactData c : app.db().contacts()) {
          if (!c.getGroups().contains(g)) {
            contact = c;
            group = g;
            return;
          }
        }
      }
      //если не удалось найти, берем любой контакт из БД и создаем группу
      contact = app.db().contacts().iterator().next();
      group = new GroupData().withName(newGroupName + (int) (Math.random() * 1000));
      app.goTo().groupPage();
      app.group().create(group);
    }
  }

  @Test
  public void testContactGroupAddition() {
    Contacts before = app.db().contacts();
    ContactData contactWithAddedGroup = contact.inGroup(group);

    app.goTo().homePage();
    app.contact().addToGroup(contact.getId(), group.getName());

    Contacts after = app.db().contacts();

    assertThat(after, equalTo(before.without(contact).withAdded(contactWithAddedGroup)));
  }
}
