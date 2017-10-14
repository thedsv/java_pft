package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactGroupDeletionTests extends TestBase {

  private ContactData contact;
  private GroupData group;
  private boolean contactCreated;
  private boolean groupCreated;

  @BeforeMethod
  public void ensurePreconditions() {
    //Если в БД отсутствуют контакты, создаем новый
    if (app.db().contacts().size() == 0) {
      app.goTo().homePage();
      app.contact().create(new ContactData().withFirstName("4GroupDeletion").withLastName("Contact"));
      contactCreated = true;
    }
    //Если в БД отсутствуют группы, создаем новую
    if (app.db().groups().size() == 0) {
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("group4Deletion"));
      groupCreated = true;
    }
    //Если контакт или группа не были созданы, ищем в БД контакт в группе
    if (!(contactCreated && groupCreated)) {
      for (ContactData c : app.db().contacts()) {
        if (c.getGroups().size() != 0) {
          contact = c;
          group = c.getGroups().iterator().next();
          return;
        }
      }
    }
    contact = app.db().contacts().iterator().next();
    group = app.db().groups().iterator().next();
    app.goTo().homePage();
    app.contact().addToGroup(contact.getId(), group.getName());
  }

  @Test
  public void testContactGroupDeletion() {
    Contacts before = app.db().contacts();
    ContactData contactWithoutGroup = contact.outOfGroup(group);

    app.goTo().homePage();
    app.contact().removeContactFromGroup(contact.getId(), group.getName());

    Contacts after = app.db().contacts();

    assertThat(after, equalTo(before.without(contact).withAdded(contactWithoutGroup)));
  }
}
