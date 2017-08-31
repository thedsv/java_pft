package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactModificationTests extends TestBase {

  @Test
  public void testContactModification() {

    app.getNavigationHelper().goToHomePage();
    if (!app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createContact(new ContactData("firstname", "lastname", "address", "homephone", "mobilephone", "workphone", "fax", "email1", "email2", "email3", "test1"));
    }
    app.getContactHelper().initContactModification();
    app.getContactHelper().fillContactForm(new ContactData("firstnameModificated", "lastnameModificated", "addressModificated", "homephoneModificated", "mobilephoneModificated", "workphoneModificated", "faxModificated", "email1Modificated", "email2Modificated", "email3Modificated", null), false);
    app.getContactHelper().submitContactModification();
    app.getNavigationHelper().goToHomePage();
  }
}
