package ru.stqa.pft.addressbook.bugtracker.bugify.appmanager;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;
import org.apache.http.message.BasicNameValuePair;
import ru.stqa.pft.addressbook.appmanager.ApplicationManager;
import ru.stqa.pft.addressbook.bugtracker.bugify.model.BugifyIssue;

import java.io.IOException;
import java.util.Set;

public class BugifyHelper {

  private final ApplicationManager app;

  public BugifyHelper(ApplicationManager app) {
    this.app = app;
  }

  public Set<BugifyIssue> getIssues() throws IOException {
    String json = getExecutor().execute(Request.Get("http://demo.bugify.com/api/issues.json"))
            .returnContent().asString();
    JsonElement parsed = new JsonParser().parse(json);
    JsonElement issues = parsed.getAsJsonObject().get("issues");
    return new Gson().fromJson(issues, new TypeToken<Set<BugifyIssue>>() {
    }.getType());
  }

  public String getIssueStatus(int issueId) throws IOException {
    String json = getExecutor().execute(Request.Get(String.format("http://demo.bugify.com/api/issues/%s.json", issueId)))
            .returnContent().asString();
    JsonElement parsed = new JsonParser().parse(json);
//    return parsed.getAsJsonObject().get("state_name").getAsString();
    return parsed.getAsJsonObject().get("issues")
            .getAsJsonArray().get(0)
            .getAsJsonObject().get("state_name").getAsString();
  }

  private Executor getExecutor() {
    return Executor.newInstance().auth("28accbe43ea112d9feb328d2c00b3eed", "");
  }

  public int createIssue(BugifyIssue newBugifyIssue) throws IOException {
    String json = getExecutor().execute(Request.Post("http://demo.bugify.com/api/issues.json")
            .bodyForm(new BasicNameValuePair("subject", newBugifyIssue.getSubject()),
                    new BasicNameValuePair("description", newBugifyIssue.getDescription())))
            .returnContent().asString();
    JsonElement parsed = new JsonParser().parse(json);
    return parsed.getAsJsonObject().get("issue_id").getAsInt();
  }
}
