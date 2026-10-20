
package tracker;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

// -------------------------------------------------------------------------
/**
 * Provides the console menu for TaskTrack.
 * Allows users to manage assignments and notes.
 *
 * @author miguel33
 * @version 19 Oct 2026
 */
public class MainMenu {

    //~ Fields ................................................................

    private final TrackerManager manager;
    private final Scanner scanner;


    //~ Constructors ..........................................................

    // ----------------------------------------------------------
    /**
     * Creates a menu with a new tracker manager.
     */
    public MainMenu() {
        this(new TrackerManager(), new Scanner(System.in));
    }


    // ----------------------------------------------------------
    /**
     * Creates a menu using a manager and scanner.
     *
     * @param manager tracker manager
     * @param scanner scanner for user input
     */
    public MainMenu(
        TrackerManager manager,
        Scanner scanner) {

        if (manager == null || scanner == null) {
            throw new IllegalArgumentException(
                "Manager and scanner cannot be null.");
        }

        this.manager = manager;
        this.scanner = scanner;
    }


    //~ Public Methods ........................................................

    // ----------------------------------------------------------
    /**
     * Starts the TaskTrack application.
     *
     * @param args command-line arguments
     */
    public static void main(String[] args) {
        MainMenu menu = new MainMenu();
        menu.run();
    }


    // ----------------------------------------------------------
    /**
     * Runs the main menu until the user exits.
     * Handles invalid input without crashing the application.
     */
    public void run() {

        while (true) {

            showMenu();

            String choice;

            try {
                choice = readLine("Choose an option");
            }
            catch (IllegalStateException e) {
                return;
            }

            try {
                switch (choice) {

                    case "1":
                        addAssignment();
                        break;

                    case "2":
                        addNote();
                        break;

                    case "3":
                        completeAssignment();
                        break;

                    case "4":
                        showList(
                            "All Items",
                            manager.getAllItems());
                        break;

                    case "5":
                        showList(
                            "Overdue Assignments",
                            manager.overdueAssignments());
                        break;

                    case "6":
                        showList(
                            "Assignments",
                            manager.getAssignments());
                        break;

                    case "7":
                        showList(
                            "Notes",
                            manager.getNotes());
                        break;

                    case "8":
                        searchItems();
                        break;

                    case "9":
                        removeItem();
                        break;

                    case "10":
                        clearItems();
                        break;

                    case "11":
                        showCounts();
                        break;

                    case "12":
                        editAssignment();
                        break;

                    case "13":
                        editNote();
                        break;

                    case "14":
                        filterCourse();
                        break;

                    case "15":
                        showCourseWorkload();
                        break;

                    case "16":
                        showWeeklySchedule();
                        break;

                    case "0":
                        System.out.println("Goodbye!");
                        return;

                    default:
                        System.out.println(
                            "Invalid menu option. Try again.");
                        break;
                }
            }
            catch (IllegalArgumentException e) {
                System.out.println(
                    "Invalid input: " + e.getMessage());
            }
            catch (IllegalStateException e) {
                return;
            }
        }
    }


    //~ Private Methods .......................................................

    // ----------------------------------------------------------
    /**
     * Displays the available menu options.
     */
    private void showMenu() {

        System.out.println();
        System.out.println("========== TaskTrack ==========");
        System.out.println("1.  Add Assignment");
        System.out.println("2.  Add Note");
        System.out.println("3.  Complete Assignment");
        System.out.println("4.  Show All Items");
        System.out.println("5.  View Overdue Assignments");
        System.out.println("6.  View Assignments");
        System.out.println("7.  View Notes");
        System.out.println("8.  Search Items");
        System.out.println("9.  Remove Item");
        System.out.println("10. Clear All Items");
        System.out.println("11. Show Item Counts");
        System.out.println("12. Edit Assignment");
        System.out.println("13. Edit Note");
        System.out.println("14. Filter by Course");
        System.out.println("15. Course Workload Summary");
        System.out.println("16. Weekly Schedule");
        System.out.println("0.  Exit");
        System.out.println("===============================");
    }


    // ----------------------------------------------------------
    /**
     * Reads a line of input from the user.
     *
     * @param message prompt displayed to the user
     * @return trimmed user input
     * @throws IllegalStateException if input is closed
     */
    private String readLine(String message) {

        System.out.print(message + ": ");

        if (!scanner.hasNextLine()) {
            throw new IllegalStateException(
                "Input is no longer available.");
        }

        return scanner.nextLine().trim();
    }


    // ----------------------------------------------------------
    /**
     * Displays a list of stored items.
     *
     * @param heading title displayed above the list
     * @param values items to display
     */
    private void showList(
        String heading,
        List<?> values) {

        System.out.println();
        System.out.println("=== " + heading + " ===");

        if (values.isEmpty()) {
            System.out.println("No matching items found.");
            return;
        }

        for (Object item : values) {
            System.out.println(item);
        }
    }


    // ----------------------------------------------------------
    /**
     * Prompts the user to create an assignment.
     */
    private void addAssignment() {

        String title = readLine("Assignment title");
        String text = readLine("Description");
        String dueDate = readLine("Due date (YYYY-MM-DD)");
        String course = readLine("Course");

        Assignment assignment = manager.createAssignment(
            title, text, dueDate, course);

        System.out.println(
            "Assignment added with ID: "
                + assignment.getId());
    }


    // ----------------------------------------------------------
    /**
     * Prompts the user to create a note.
     */
    private void addNote() {

        String title = readLine("Note title");
        String text = readLine("Note text");

        Note note = manager.createNote(title, text);

        System.out.println(
            "Note added with ID: " + note.getId());
    }


    // ----------------------------------------------------------
    /**
     * Marks an assignment as complete.
     */
    private void completeAssignment() {

        String id = readLine("Assignment ID");

        if (manager.completeAssignment(id)) {
            System.out.println("Assignment completed.");
        }
        else {
            System.out.println("Assignment not found.");
        }
    }


    // ----------------------------------------------------------
    /**
     * Searches assignments and notes using a keyword.
     */
    private void searchItems() {

        String keyword = readLine("Search keyword");

        showList(
            "Search Results",
            manager.search(keyword));
    }


    // ----------------------------------------------------------
    /**
     * Removes an item using its unique ID.
     */
    private void removeItem() {

        String id = readLine("Item ID");

        if (manager.removeById(id)) {
            System.out.println("Item removed.");
        }
        else {
            System.out.println("Item not found.");
        }
    }


    // ----------------------------------------------------------
    /**
     * Clears the tracker after user confirmation.
     */
    private void clearItems() {

        String confirmation =
            readLine("Type YES to clear all items");

        if (confirmation.equals("YES")) {
            manager.clear();
            System.out.println("All items cleared.");
        }
        else {
            System.out.println("Clear cancelled.");
        }
    }


    // ----------------------------------------------------------
    /**
     * Displays assignment, note, and total item counts.
     */
    private void showCounts() {

        System.out.println(
            "Assignments: " + manager.countAssignments());

        System.out.println(
            "Notes: " + manager.countNotes());

        System.out.println(
            "Total items: " + manager.size());
    }


    // ----------------------------------------------------------
    /**
     * Updates an existing assignment.
     */
    private void editAssignment() {

        String id = readLine("Assignment ID");

        if (!(manager.findById(id) instanceof Assignment)) {
            System.out.println("Assignment not found.");
            return;
        }

        String title = readLine("New title");
        String text = readLine("New description");
        String date = readLine("New due date (YYYY-MM-DD)");
        String course = readLine("New course");

        if (manager.updateAssignment(
            id, title, text, date, course)) {

            System.out.println("Assignment updated.");
        }
        else {
            System.out.println("Assignment not found.");
        }
    }


    // ----------------------------------------------------------
    /**
     * Updates an existing note.
     */
    private void editNote() {

        String id = readLine("Note ID");

        if (!(manager.findById(id) instanceof Note)) {
            System.out.println("Note not found.");
            return;
        }

        String title = readLine("New title");
        String text = readLine("New text");

        if (manager.updateNote(id, title, text)) {
            System.out.println("Note updated.");
        }
        else {
            System.out.println("Note not found.");
        }
    }


    // ----------------------------------------------------------
    /**
     * Displays assignments for a selected course.
     */
    private void filterCourse() {

        String course = readLine("Course name");

        showList(
            "Course Assignments",
            manager.filterByCourse(course));
    }


    // ----------------------------------------------------------
    /**
     * Displays the number of assignments in each course.
     */
    private void showCourseWorkload() {

        Map<String, Integer> workload =
            manager.courseWorkload();

        System.out.println();
        System.out.println("=== Course Workload ===");

        if (workload.isEmpty()) {
            System.out.println("No assignments found.");
            return;
        }

        for (Map.Entry<String, Integer> entry
            : workload.entrySet()) {

            System.out.println(
                entry.getKey() + ": "
                    + entry.getValue() + " assignments");
        }
    }


    // ----------------------------------------------------------
    /**
     * Displays assignments due during a selected week.
     */
    private void showWeeklySchedule() {

        String input = readLine(
            "Week start (YYYY-MM-DD, blank for today)");

        LocalDate start;

        if (input.isEmpty()) {
            start = LocalDate.now();
        }
        else {
            try {
                start = LocalDate.parse(input);
            }
            catch (DateTimeParseException e) {
                throw new IllegalArgumentException(
                    "Enter a valid date in YYYY-MM-DD format.");
            }
        }

        showList(
            "Weekly Assignments",
            manager.weeklySchedule(start));
    }
}
