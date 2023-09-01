package guru.qa.service;

public interface PasswordEncoder {

  String encode(String source);

  String decode(String source);

  class MockPasswordEncoder implements PasswordEncoder {
    @Override
    public String encode(String source) {
      return source;
    }

    @Override
    public String decode(String source) {
      return source;
    }
  }
}
