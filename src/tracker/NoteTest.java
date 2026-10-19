
package tracker;

import student.TestCase;

// -------------------------------------------------------------------------
/**
 * Tests the Note class in TaskTrack.
 * Includes normal, invalid-input, and edge-case tests.
 *
 * @author zaraaomerr
 * @version 19 Oct 2026
 */
public class NoteTest extends TestCase {

    //~ Fields ................................................................

    private Note note;


    //~ Public Methods ........................................................

    // ----------------------------------------------------------
    /**
     * Creates a note before each test.
     */
    public void setUp() {
        note = new Note(
            1,
            "Exam Notes",
            "Study for ECE exam");
    }


    // ----------------------------------------------------------
    /**
     * Tests the constructor and getter methods.
     */
    public void testGetters() {
        assertEquals(1, note.getId());
        assertEquals("Exam Notes", note.getTitle());
        assertEquals(
            "Study for ECE exam",
            note.getText());
    }


    // ----------------------------------------------------------
    /**
     * Tests that Note implements Item.
     */
    public void testItemInterface() {
        Item item = note;

        assertEquals(1, item.getId());
        assertEquals("Exam Notes", item.getTitle());
        assertEquals(
            "Study for ECE exam",
            item.getText());
    }


    // ----------------------------------------------------------
    /**
     * Tests updating the title and text of a note.
     */
    public void testUpdate() {
        note.update(
            "Updated Notes",
            "Review linked lists");

        assertEquals("Updated Notes", note.getTitle());
        assertEquals(
            "Review linked lists",
            note.getText());
        assertEquals(1, note.getId());
    }


    // ----------------------------------------------------------
    /**
     * Tests updating a note multiple times.
     */
    public void testMultipleUpdates() {
        note.update("First Update", "First text");
        note.update("Second Update", "Second text");

        assertEquals("Second Update", note.getTitle());
        assertEquals("Second text", note.getText());
    }


    // ----------------------------------------------------------
    /**
     * Tests rejecting an empty note title.
     */
    public void testEmptyTitle() {
        try {
            new Note(2, "", "Description");

            fail("Expected an IllegalArgumentException");
        }
        catch (IllegalArgumentException e) {
            assertNotNull(e);
        }
    }


    // ----------------------------------------------------------
    /**
     * Tests rejecting a null note title.
     */
    public void testNullTitle() {
        try {
            new Note(2, null, "Description");

            fail("Expected an IllegalArgumentException");
        }
        catch (IllegalArgumentException e) {
            assertNotNull(e);
        }
    }


    // ----------------------------------------------------------
    /**
     * Tests rejecting a blank note title.
     */
    public void testBlankTitle() {
        try {
            new Note(2, "   ", "Description");

            fail("Expected an IllegalArgumentException");
        }
        catch (IllegalArgumentException e) {
            assertNotNull(e);
        }
    }


    // ----------------------------------------------------------
    /**
     * Tests rejecting empty note text.
     */
    public void testEmptyText() {
        try {
            new Note(2, "Notes", "");

            fail("Expected an IllegalArgumentException");
        }
        catch (IllegalArgumentException e) {
            assertNotNull(e);
        }
    }


    // ----------------------------------------------------------
    /**
     * Tests rejecting null note text.
     */
    public void testNullText() {
        try {
            new Note(2, "Notes", null);

            fail("Expected an IllegalArgumentException");
        }
        catch (IllegalArgumentException e) {
            assertNotNull(e);
        }
    }


    // ----------------------------------------------------------
    /**
     * Tests rejecting blank note text.
     */
    public void testBlankText() {
        try {
            new Note(2, "Notes", "   ");

            fail("Expected an IllegalArgumentException");
        }
        catch (IllegalArgumentException e) {
            assertNotNull(e);
        }
    }


    // ----------------------------------------------------------
    /**
     * Tests rejecting an empty title during update.
     */
    public void testUpdateEmptyTitle() {
        try {
            note.update("", "New text");

            fail("Expected an IllegalArgumentException");
        }
        catch (IllegalArgumentException e) {
            assertEquals("Exam Notes", note.getTitle());
            assertEquals(
                "Study for ECE exam",
                note.getText());
        }
    }


    // ----------------------------------------------------------
    /**
     * Tests rejecting a null title during update.
     */
    public void testUpdateNullTitle() {
        try {
            note.update(null, "New text");

            fail("Expected an IllegalArgumentException");
        }
        catch (IllegalArgumentException e) {
            assertEquals("Exam Notes", note.getTitle());
        }
    }


    // ----------------------------------------------------------
    /**
     * Tests rejecting a blank title during update.
     */
    public void testUpdateBlankTitle() {
        try {
            note.update("   ", "New text");

            fail("Expected an IllegalArgumentException");
        }
        catch (IllegalArgumentException e) {
            assertEquals("Exam Notes", note.getTitle());
        }
    }


    // ----------------------------------------------------------
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


    // ----------------------------------------------------------
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
            assertEquals(
                "Study for ECE exam",
                note.getText());
        }
    }


    // ----------------------------------------------------------
    /**
     * Tests rejecting blank text during update.
     */
    public void testUpdateBlankText() {
        try {
            note.update("New Title", "   ");

            fail("Expected an IllegalArgumentException");
        }
        catch (IllegalArgumentException e) {
            assertEquals("Exam Notes", note.getTitle());
            assertEquals(
                "Study for ECE exam",
                note.getText());
        }
    }


    // ----------------------------------------------------------
    /**
     * Tests allowing notes with duplicate titles and text.
     */
    public void testDuplicateNotes() {
        Note another = new Note(
            2,
            "Exam Notes",
            "Study for ECE exam");

        assertEquals(
            note.getTitle(),
            another.getTitle());

        assertEquals(
            note.getText(),
            another.getText());

        assertFalse(note.getId() == another.getId());
    }


    // ----------------------------------------------------------
    /**
     * Tests the string representation of a note.
     */
    public void testToString() {
        String expected = "ID: 1"
            + " | Title: Exam Notes"
            + " | Text: Study for ECE exam";

        assertEquals(expected, note.toString());
    }


    // ----------------------------------------------------------
    /**
     * Tests the string after updating a note.
     */
    public void testToStringAfterUpdate() {
        note.update(
            "Updated Notes",
            "Review linked lists");

        String expected = "ID: 1"
            + " | Title: Updated Notes"
            + " | Text: Review linked lists";

        assertEquals(expected, note.toString());
    }
}
