
package tracker;

// -------------------------------------------------------------------------
/**
 * Represents a note in TaskTrack.
 * Stores a note's ID, title, and text.
 *
 * @author zaraaomerr
 * @version 19 Oct 2026
 */
public class Note implements Item {

    //~ Fields ................................................................

    private final int id;
    private String title;
    private String text;


    //~ Constructors ..........................................................

    // ----------------------------------------------------------
    /**
     * Creates a new note.
     *
     * @param id note ID
     * @param title note title
     * @param text note content
     * @throws IllegalArgumentException if title or text is empty
     */
    public Note(int id, String title, String text) {
        this.id = id;
        update(title, text);
    }


    //~ Public Methods ........................................................

    // ----------------------------------------------------------
    /**
     * Returns the note ID.
     *
     * @return note ID
     */
    @Override
    public int getId() {
        return id;
    }


    // ----------------------------------------------------------
    /**
     * Returns the note title.
     *
     * @return note title
     */
    @Override
    public String getTitle() {
        return title;
    }


    // ----------------------------------------------------------
    /**
     * Returns the note text.
     *
     * @return note text
     */
    @Override
    public String getText() {
        return text;
    }


    // ----------------------------------------------------------
    /**
     * Updates the title and text of the note.
     *
     * @param newTitle updated note title
     * @param newText updated note text
     * @throws IllegalArgumentException if title or text is empty
     */
    public void update(String newTitle, String newText) {

        if (newTitle == null || newTitle.trim().isEmpty()) {
            throw new IllegalArgumentException(
                "Note title cannot be empty.");
        }

        if (newText == null || newText.trim().isEmpty()) {
            throw new IllegalArgumentException(
                "Note text cannot be empty.");
        }

        title = newTitle.trim();
        text = newText.trim();
    }


    // ----------------------------------------------------------
    /**
     * Returns a readable description of the note.
     *
     * @return note details as a string
     */
    @Override
    public String toString() {
        return "ID: " + id
            + " | Title: " + title
            + " | Text: " + text;
    }
}
