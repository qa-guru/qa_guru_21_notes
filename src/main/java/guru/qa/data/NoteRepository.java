package guru.qa.data;

import guru.qa.domain.Note;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public interface NoteRepository {

  List<Note> getAllByUsername(String username);

  void saveNote(Note note);

  class MockNoteRepository implements NoteRepository {
    private final List<Note> stored = new ArrayList<>(
        List.of(
            new Note("dima", "first mock note"),
            new Note("dima", "second mock note")
        )
    );

    @Override
    public List<Note> getAllByUsername(String username) {
      if ("dima".equals(username)) {
        return stored;
      }
      return Collections.emptyList();
    }

    @Override
    public void saveNote(Note note) {
      if ("dima".equals(note.getUsername())) {
        stored.add(note);
      }
    }
  }
}
