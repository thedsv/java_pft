package ru.stqa.pft.mantis.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.mantis.model.MailMessage;
import ru.stqa.pft.mantis.model.UserData;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class RegistrationTests extends TestBase {

  @BeforeMethod
  public void startMailServer() {
    app.mail().start();
  }

  @Test
  public void testRegistration() throws IOException, MessagingException {
    long now = System.currentTimeMillis();
    UserData user = new UserData().withName(String.format("user%s", now)).withPassword("password").withEmail(String.format("user%s@localhost.localdomain", now));
    app.registration().start(user.getName(), user.getEmail());
    List<MailMessage> mailMessages = app.mail().waitForMail(2, 10000);
    String confirmationLink = app.mail().findConfirmationLink(mailMessages, user.getEmail());
    app.registration().finish(confirmationLink, user.getPassword());
    assertTrue(app.newSession().login(user.getName(), user.getPassword()));
  }

  @AfterMethod(alwaysRun = true)
  public void stopMailServer() {
    app.mail().stop();
  }

}
