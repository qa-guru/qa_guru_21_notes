package guru.qa.view;

import guru.qa.service.Session;

public interface Frontend {

  void render(Session session);

  class MockFrontend implements Frontend {
    @Override
    public void render(Session session) {
      System.out.println("Mock frontend!");
    }
  }
}
