package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() {
    app.goTo().homePage();
    Contacts before = app.contact().all();
    ContactData contact = new ContactData()
            .withLastName("Иванов").withFirstName("Иван").withAddress("г. Москва, ул. Победы, д.10, кв.15")
            .withHomePhone("23-44-22").withMobilePhone("+7(999) 233-33-22").withWorkPhone("33 14 55")
            .withEmail1("email1@abc.ru").withEmail2("email2@cba.ru").withEmail3("email3@zxc.com").withGroup("test1");

    app.contact().create(contact);
    assertThat(app.contact().count(), equalTo(before.size() + 1));
    Contacts after = app.contact().all();
    assertThat(after, equalTo(
            before.withAdded(contact.withId(after.stream().mapToInt(ContactData::getId).max().getAsInt()))));
  }

}
