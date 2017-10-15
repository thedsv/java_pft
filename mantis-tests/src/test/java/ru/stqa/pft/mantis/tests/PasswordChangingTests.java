package ru.stqa.pft.mantis.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.mantis.model.MailMessage;
import ru.stqa.pft.mantis.model.UserData;
import ru.stqa.pft.mantis.model.Users;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class PasswordChangingTests extends TestBase {

  private UserData user;
  private String password = "Qwerty123";

  @BeforeMethod
  public void ensurePreconditions() throws IOException, MessagingException {
    //Собираем список пользователей, кроме администратора
    Users users = app.db().users().without(new UserData().withName(app.properties.getProperty("web.adminLogin")));
    if (users.size() == 0) { /* Если список пустой */
      //Создаем пользователя
      long now = System.currentTimeMillis();
      user = new UserData().withName(String.format("user%s", now)).withPassword("password").withEmail(String.format("user%s@localhost.localdomain", now));
      app.registration().start(user.getName(), user.getEmail());
    } else {
      user = users.iterator().next();
    }
  }

  @BeforeMethod
  public void startMailServer() {
    app.mail().start();
  }

  @Test
  public void testPasswordChanging() throws IOException, MessagingException {
    app.session().loginAsAdmin();
    app.goTo().manageUsers();
    app.userManagement().resetUserPassword(user.getName());

    List<MailMessage> mailMessages = app.mail().waitForMail(1, 10000);
    String confirmationLink = app.mail().findPasswordChangeLink(mailMessages, user.getEmail());
    app.registration().finish(confirmationLink, password);
    assertTrue(app.newSession().login(user.getName(), password));
  }

  @AfterMethod(alwaysRun = true)
  public void stopMailServer() {
    app.mail().stop();
  }
}
