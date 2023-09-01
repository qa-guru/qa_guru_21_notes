package guru.qa;

import guru.qa.data.NoteRepository;
import guru.qa.data.UserFileRepository;
import guru.qa.service.Base64PasswordEncoder;
import guru.qa.service.Session;
import guru.qa.view.AuthFrontend;
import guru.qa.view.FrontendContainer;
import guru.qa.view.NotesFrontend;

import java.nio.file.Path;

public class Main {
  public static void main(String[] args) {
    new FrontendContainer(
        new AuthFrontend(
            new UserFileRepository(
                Path.of("users.csv")
            ),
            new Base64PasswordEncoder(),
            new NotesFrontend(
                new NoteRepository.MockNoteRepository()
            )
        )
    ).render(new Session.MockSession());
  }
}