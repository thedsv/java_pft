package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;

public class UserHelper extends BaseHelper {

  public UserHelper(ApplicationManager app) {
    super(app);
  }

  public void resetUserPassword(String username) {
    click(By.linkText(username));
    click(By.cssSelector("input[value='Reset Password']"));
  }
}
