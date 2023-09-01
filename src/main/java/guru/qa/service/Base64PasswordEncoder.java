package guru.qa.service;

import java.util.Base64;

import static java.nio.charset.StandardCharsets.UTF_8;

public class Base64PasswordEncoder implements PasswordEncoder {
  @Override
  public String encode(String source) {
    return new String(Base64.getEncoder().encode(source.getBytes(UTF_8)));
  }

  @Override
  public String decode(String source) {
    return new String(Base64.getDecoder().decode(source.getBytes(UTF_8)));
  }
}
