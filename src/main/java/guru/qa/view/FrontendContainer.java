package guru.qa.view;

import guru.qa.service.Session;

public class FrontendContainer implements Frontend {

  private final Frontend[] frontends;

  public FrontendContainer(Frontend... frontends) {
    this.frontends = frontends;
  }

  @Override
  public void render(Session session) {
    for (Frontend frontend : frontends) {
      frontend.render(session);
    }
  }
}
