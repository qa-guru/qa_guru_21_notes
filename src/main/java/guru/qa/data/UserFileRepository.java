package guru.qa.data;

import com.opencsv.CSVReader;
import guru.qa.domain.User;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

public class UserFileRepository implements UserRepository {

  private final Path path;

  public UserFileRepository(Path path) {
    this.path = path;
  }

  @Override
  public Optional<User> findByUsername(String username) {
    try (InputStream is = Files.newInputStream(path);
         CSVReader reader = new CSVReader(new InputStreamReader(is))) {

      String[] userRow = reader.readAll().stream()
          .filter(row -> row[0].equals(username))
          .findFirst()
          .orElse(null);

      if (userRow != null) {
        return Optional.of(new User(userRow[0], userRow[1]));
      } else {
        return Optional.empty();
      }
    } catch (Exception e) {
      System.out.println("Error while reading file");
      return Optional.empty();
    }
  }
}
