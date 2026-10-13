
package tracker;

// -------------------------------------------------------------------------
/**
 * Represents a note in TaskTrack.
 * Stores the note's ID, title, and text.
 *
 * @author zaraaomerr
 * @version 13 Oct 2026
 */
public class Note implements Item {

    //~ Fields ................................................................

    private int id;
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

        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException(
                "Note title cannot be empty.");
        }

        if (text == null || text.trim().isEmpty()) {
            throw new IllegalArgumentException(
                "Note text cannot be empty.");
        }

        this.id = id;
        this.title = title;
        this.text = text;
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
     * Updates the note's title and text.
     *
     * @param newTitle updated title
     * @param newText updated text
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

        title = newTitle;
        text = newText;
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
