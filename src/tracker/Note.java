
package tracker;

/**
 * Represents a note in TaskTrack.
 * Stores the note's ID, title, and text.
 *
 * 
 */
public class Note implements Item {

    private int id;
    private String title;
    private String text;

    /**
     * Creates a new note.
     *
     * @param id note ID
     * @param title note title
     * @param text note content
     */
    public Note(int id, String title, String text) {

        if (text == null || text.trim().isEmpty()) {
            throw new IllegalArgumentException(
                "Note text cannot be empty.");
        }

        this.id = id;
        this.title = title;
        this.text = text;
    }

    /**
     * Returns the note ID.
     *
     * @return note ID
     */
    public int getId() {
        return id;
    }

    /**
     * Returns the note title.
     *
     * @return note title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Returns the note text.
     *
     * @return note text
     */
    public String getText() {
        return text;
    }

    /**
     * Updates the note's title and text.
     *
     * @param newTitle updated title
     * @param newText updated text
     */
    public void update(String newTitle, String newText) {

        if (newText == null
            || newText.trim().isEmpty()) {
            throw new IllegalArgumentException(
                "Note text cannot be empty.");
        }

        title = newTitle;
        text = newText;
    }
}
