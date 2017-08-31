package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase{

  @Test
  public void testContactCreation() {
    app.getNavigationHelper().goToHomePage();
    app.getContactHelper().createContact(new ContactData("firstname", "lastname", "address", "homephone", "mobilephone", "workphone", "fax", "email1", "email2", "email3", "test1"));
  }

}
