package ru.stqa.pft.addressbook.tests;

import org.openqa.selenium.remote.BrowserType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import ru.stqa.pft.addressbook.appmanager.ApplicationManager;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import javax.xml.rpc.ServiceException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class TestBase {

  Logger logger = LoggerFactory.getLogger(TestBase.class);

  protected static final ApplicationManager app
          = new ApplicationManager(System.getProperty("browser", BrowserType.CHROME));

  @BeforeSuite
  public void setUp() throws Exception {
    app.init();
  }

  @AfterSuite(alwaysRun = true)
  public void tearDown() {
    app.stop();
  }

  @BeforeMethod
  public void logTestStart(Method m, Object[] p) {
    logger.info("Start test " + m.getName() + " with parameters " + Arrays.asList(p));
  }

  @AfterMethod(alwaysRun = true)
  public void logTestStop(Method m) {
    logger.info("Stop test " + m.getName());
  }

  public void verifyGroupListInUI() {
    if (Boolean.getBoolean("verifyUI")) {
      Groups dbGroups = app.db().groups();
      Groups uiGroups = app.group().all();
      assertThat(uiGroups, equalTo(dbGroups.stream()
              .map((g) -> new GroupData().withId(g.getId()).withName(g.getName()))
              .collect(Collectors.toSet())));
    }
  }

  public void verifyContactListInUI() {
    if (Boolean.getBoolean("verifyUI")) {
      Contacts dbContacts = app.db().contacts();
      Contacts uiContacts = app.contact().all();
      assertThat(uiContacts, equalTo(dbContacts.stream()
              .map((c) -> new ContactData().withId(c.getId()).withFirstName(c.getFirstName()).withLastName(c.getLastName()).withAddress(c.getAddress())
                      .withAllEmails(c.getEmail1() + "\n" + c.getEmail2() + "\n" + c.getEmail3())
                      .withAllPhones(c.getHomePhone() + "\n" + c.getMobilePhone() + "\n" + c.getWorkPhone()))
              .collect(Collectors.toSet())));
    }
  }

  public boolean isMantisIssueOpen(int issueId) throws RemoteException, ServiceException, MalformedURLException {
    String issueStatus = app.mantis().getIssueStatus(issueId);
    return !issueStatus.equals("resolved") && !issueStatus.equals("closed");
  }

  private boolean isBugifyIssueOpen(int issueId) throws IOException {
    String issueStatus = app.bugify().getIssueStatus(issueId);
    return !issueStatus.equals("Resolved") && !issueStatus.equals("Closed");
  }

  public void skipIfNotFixed(String bugtracker, int issueId) throws IOException, ServiceException {
    switch (bugtracker) {
      case "mantis":
        if (isMantisIssueOpen(issueId)) {
          throw new SkipException("Ignored because of MantisBT issue " + issueId);
        }
        break;
      case "bugify":
        if (isBugifyIssueOpen(issueId)) {
          throw new SkipException("Ignored because of Bugify issue " + issueId);
        }
        break;
    }
  }
}
