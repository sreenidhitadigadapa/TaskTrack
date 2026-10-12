package tracker;

import student.TestCase;
import java.nio.file.Files;
import java.nio.file.Path;
// -------------------------------------------------------------------------
/**
 * Tests the manager, two item types, course filtering, and invalid inputs.
 *
 * @author sreenidhitadigadapa
 * @version 11 Oct 2026
 */
public class TrackerManagerTest extends TestCase {
    private TrackerManager manager;
    /**
     * Sets up test fixtures before each test.
     */
    public void setUp() { manager = new TrackerManager(); }
    /**
     * Tests create and two types.
     */
    public void testCreateAndTwoTypes() {
        Assignment a = manager.createAssignment("Project", "Code", "2026-10-23", "CS 2114");
        Note n = manager.createNote("Reminder", "Review");
        assertEquals(2, manager.size());
        assertEquals(1, manager.countAssignments());
        assertEquals(1, manager.countNotes());
        assertEquals(a, manager.findById("1"));
        assertEquals(n, manager.findById("2"));
        assertEquals(2, manager.getAllItems().size());
        assertEquals(1, manager.getAssignments().size());
        assertEquals(1, manager.getNotes().size());
    }
    /**
     * Tests bad creation.
     */
    public void testBadCreation() {
        try { manager.createAssignment(" ", "", "", ""); fail("Expected exception"); }
        catch (IllegalArgumentException e) { assertEquals(0, manager.size()); }
        try { manager.createNote("Title", " "); fail("Expected exception"); }
        catch (IllegalArgumentException e) { assertEquals(0, manager.size()); }
        assertEquals(1, manager.createNote("Valid", "Text").getId());
    }
    /**
     * Tests missing ids and remove.
     */
    public void testMissingIdsAndRemove() {
        manager.createNote("Note", "Text");
        assertNull(manager.findById(null)); assertNull(manager.findById("abc"));
        assertNull(manager.findById("999")); assertNull(manager.findById(""));
        assertFalse(manager.removeById("999")); assertFalse(manager.removeById(null));
        assertTrue(manager.removeItem("1")); assertFalse(manager.removeItem("1"));
        assertEquals(0, manager.size());
    }
    /**
     * Tests complete assignment.
     */
    public void testCompleteAssignment() {
        Assignment a = manager.createAssignment("Project", "Code", "", "CS");
        manager.createNote("N", "T");
        assertTrue(manager.completeAssignment("1"));
        assertTrue(a.isCompleted());
        assertFalse(manager.completeAssignment("2"));
        assertFalse(manager.completeAssignment("bad"));
        assertFalse(manager.completeAssignment("99"));
    }
    /**
     * Tests search and course filter.
     */
    public void testSearchAndCourseFilter() {
        manager.createAssignment("Java Project", "Code", "", "CS 2114");
        manager.createAssignment("Recursion", "Study", "", "cs 2114");
        manager.createAssignment("Graph Quiz", "Study", "", "MATH 3134");
        manager.createNote("Review", "java");
        assertEquals(2, manager.search("JAVA").size());
        assertEquals(2, manager.filterByCourse(" CS 2114 ").size());
        assertEquals(0, manager.filterByCourse("PHYS").size());
        assertEquals(0, manager.filterByCourse(null).size());
        assertEquals(0, manager.filterByCourse(" ").size());
        assertEquals(0, manager.search(null).size());
        assertEquals(0, manager.search(" ").size());
        assertEquals(0, manager.search("missing").size());
    }
    /**
     * Tests incomplete and clear.
     */
    public void testIncompleteAndClear() {
        assertEquals(0, manager.getIncompleteAssignments().size());
        assertEquals(0, manager.getAllItems().size());
        assertEquals(0, manager.getNotes().size());
        assertEquals(0, manager.filterByCourse("CS").size());
        Assignment a = manager.createAssignment("A", "", "", "CS");
        manager.createAssignment("B", "", "", "CS");
        a.markComplete(); assertEquals(1, manager.getIncompleteAssignments().size());
        manager.clear(); manager.clear(); assertEquals(0, manager.size());
        assertEquals(0, manager.countAssignments()); assertEquals(0, manager.countNotes());
    }
    /**
     * Tests save and bad filename.
     */
    public void testSaveAndBadFilename() throws Exception {
        manager.createNote("Reminder", "Review");
        Path path = Files.createTempFile("tasktrack-", ".txt");
        try {
            manager.save(path.toString());
            assertTrue(Files.size(path) > 0);
        }
        finally { Files.deleteIfExists(path); }
        try { manager.save(" "); fail("Expected exception"); }
        catch (IllegalArgumentException e) { assertEquals(1, manager.size()); }
        try { manager.save(null); fail("Expected exception"); }
        catch (IllegalArgumentException e) { assertEquals(1, manager.size()); }
    }
    /**
     * Tests default save.
     */
    public void testDefaultSave() throws Exception {
        Path path = Path.of("tasktrack.txt");
        byte[] original = Files.exists(path) ? Files.readAllBytes(path) : null;
        try { manager.save(); assertTrue(Files.exists(path)); }
        finally {
            if (original == null) Files.deleteIfExists(path);
            else Files.write(path, original);
        }
    }
}
