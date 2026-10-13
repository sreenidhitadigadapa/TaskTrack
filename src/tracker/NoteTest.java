
package tracker;

import student.TestCase;

/**
 * Tests the Note class.
 *
 * 
 */
public class NoteTest extends TestCase {

    private Note note;

    /**
     * Creates a note before each test.
     */
    public void setUp() {
        note = new Note(
            1,
            "Exam Notes",
            "Study for ECE exam");
    }

    /**
     * Tests the note constructor and getters.
     */
    public void testGetters() {
        assertEquals(1, note.getId());
        assertEquals("Exam Notes", note.getTitle());
        assertEquals(
            "Study for ECE exam",
            note.getText());
    }

    /**
     * Tests updating note information.
     */
    public void testUpdate() {
        note.update(
            "Updated Notes",
            "Review circuits and resistors");

        assertEquals("Updated Notes", note.getTitle());
        assertEquals(
            "Review circuits and resistors",
            note.getText());
        assertEquals(1, note.getId());
    }

    /**
     * Tests updating a note multiple times.
     */
    public void testMultipleUpdates() {
        note.update("Math Notes", "Study derivatives");
        note.update("CS Notes", "Study linked lists");

        assertEquals("CS Notes", note.getTitle());
        assertEquals(
            "Study linked lists",
            note.getText());
    }

    /**
     * Tests rejecting empty note content.
     */
    public void testEmptyText() {
        try {
            new Note(2, "Empty Note", "");
            fail("Expected an IllegalArgumentException");
        }
        catch (IllegalArgumentException e) {
            assertNotNull(e);
        }
    }

    /**
     * Tests rejecting null note content.
     */
    public void testNullText() {
        try {
            new Note(2, "Null Note", null);
            fail("Expected an IllegalArgumentException");
        }
        catch (IllegalArgumentException e) {
            assertNotNull(e);
        }
    }

    /**
     * Tests rejecting blank note content.
     */
    public void testBlankText() {
        try {
            new Note(2, "Blank Note", "   ");
            fail("Expected an IllegalArgumentException");
        }
        catch (IllegalArgumentException e) {
            assertNotNull(e);
        }
    }

    /**
     * Tests rejecting empty text during update.
     */
    public void testUpdateEmptyText() {
        try {
            note.update("New Title", "");
            fail("Expected an IllegalArgumentException");
        }
        catch (IllegalArgumentException e) {
            assertEquals("Exam Notes", note.getTitle());
            assertEquals(
                "Study for ECE exam",
                note.getText());
        }
    }

    /**
     * Tests rejecting null text during update.
     */
    public void testUpdateNullText() {
        try {
            note.update("New Title", null);
            fail("Expected an IllegalArgumentException");
        }
        catch (IllegalArgumentException e) {
            assertEquals("Exam Notes", note.getTitle());
        }
    }

    /**
     * Tests allowing notes with identical content.
     */
    public void testDuplicateNotes() {
        Note another = new Note(
            2,
            "Exam Notes",
            "Study for ECE exam");

        assertEquals(note.getTitle(), another.getTitle());
        assertEquals(note.getText(), another.getText());
        assertFalse(note.getId() == another.getId());
    }
}
