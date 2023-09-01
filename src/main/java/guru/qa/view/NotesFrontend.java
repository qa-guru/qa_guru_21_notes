package guru.qa.view;

import guru.qa.data.NoteRepository;
import guru.qa.domain.Note;
import guru.qa.service.Session;

import javax.swing.*;
import java.util.List;

public class NotesFrontend implements Frontend {

  private final NoteRepository noteRepository;

  public NotesFrontend(NoteRepository noteRepository) {
    this.noteRepository = noteRepository;
  }

  @Override
  public void render(Session session) {
    String username = session.unwrap().getUsername();
    List<Note> notesFroCurrentUser = noteRepository.getAllByUsername(username);
    JOptionPane.showMessageDialog(
        null,
        "Current notes: " + notesFroCurrentUser,
        "Notes",
        JOptionPane.INFORMATION_MESSAGE
    );

    String text = JOptionPane.showInputDialog("New note:");
    noteRepository.saveNote(new Note(username, text));

    int proceed = JOptionPane.showConfirmDialog(
        null,
        "Continue?"
    );
    if (proceed == 0) {
      render(session);
    } else {
      System.exit(0);
    }
  }
}
