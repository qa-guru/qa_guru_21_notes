package guru.qa.view;

import guru.qa.data.UserRepository;
import guru.qa.domain.User;
import guru.qa.service.PasswordEncoder;
import guru.qa.service.Session;
import guru.qa.service.UserSession;

import javax.swing.*;
import java.util.Optional;

public class AuthFrontend implements Frontend {

  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;
  private final Frontend[] onAuthRenderFrontends;

  public AuthFrontend(UserRepository userRepository, PasswordEncoder passwordEncoder, Frontend... onAuthRenderFrontends) {
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
    this.onAuthRenderFrontends = onAuthRenderFrontends;
  }

  @Override
  public void render(Session session) {
    String username = JOptionPane.showInputDialog("Login:");
    String password = JOptionPane.showInputDialog("Password:");

    Optional<User> optionalUser = userRepository.findByUsername(username);
    if (optionalUser.isEmpty()) {
      showAuthError();
      render(session);
    } else {
      User user = optionalUser.get();
      String encodedPassword = passwordEncoder.encode(password);
      if (!user.isPasswordEquals(encodedPassword)) {
        showAuthError();
        render(session);
      }
      Session userSession = new UserSession(user);
      for (Frontend onAuthRenderFrontend : onAuthRenderFrontends) {
        onAuthRenderFrontend.render(userSession);
      }
    }
  }

  private void showAuthError() {
    JOptionPane.showMessageDialog(
        null,
        "Bad credentials",
        "Error",
        JOptionPane.ERROR_MESSAGE
    );
  }
}
