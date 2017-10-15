package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;

public class SessionHelper extends BaseHelper {

  public SessionHelper(ApplicationManager app) {
    super(app);
  }

  public void loginAsAdmin() {
    login(app.properties.getProperty("web.adminLogin"), app.properties.getProperty("web.adminPassword"));
  }

  public void login(String username, String password) {
    wd.get(app.getProperty("web.baseUrl") + "/login_page.php");
    type(By.name("username"), username);
    type(By.name("password"), password);
    click(By.cssSelector("input[value='Login']"));
  }
}
