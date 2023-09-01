package guru.qa.service;

import guru.qa.domain.User;

public interface Session {

  User unwrap();

  class MockSession implements Session {
    @Override
    public User unwrap() {
      return null;
    }
  }
}
