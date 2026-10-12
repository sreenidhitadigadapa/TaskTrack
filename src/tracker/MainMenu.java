package tracker;

import java.util.Scanner;

// -------------------------------------------------------------------------
/**
 * Runs the TaskTrack console menu and connects TrackerManager and Item
 * objects into one program.
 *
 * @author miguel33
 * @version 11 Oct 2026
 */
public class MainMenu {

    //~ Fields ................................................................

    private TrackerManager manager;
    private Scanner input;


    //~ Constructors ..........................................................

    // ----------------------------------------------------------
    /**
     * Creates a MainMenu that reads the user's typing from the given Scanner.
     *
     * @param input where the user's typing comes from
     */
    public MainMenu(Scanner input) {
        this.input = input;
        this.manager = new TrackerManager();
    }


    //~ Public Methods ........................................................

    // ----------------------------------------------------------
    /**
     * Starts the program.
     *
     * @param args not used
     */
    public static void main(String[] args) {
        MainMenu menu = new MainMenu(new Scanner(System.in));
        menu.run();
    }


    // ----------------------------------------------------------
    /**
     * Shows the menu until the user quits.
     */
    public void run() {
        System.out.println("===== Welcome to TaskTrack =====");

        boolean running = true;
        while (running) {
            printMenu();
            String choice = readLine("Choose an option: ");

            // No more input, stop instead of crashing
            if (choice == null) {
                return;
            }

            running = handleChoice(choice);
        }
    }


    //~ Private Methods .......................................................

    // ----------------------------------------------------------
    /**
     * Runs the menu option the user picked.
     *
     * @param choice what the user typed
     * @return false when the user wants to quit
     */
    private boolean handleChoice(String choice) {
        if (choice.equals("1")) {
            handleAddAssignment();
        }
        else if (choice.equals("2")) {
            handleAddNote();
        }
        else if (choice.equals("3")) {
            handleCompleteAssignment();
        }
        else if (choice.equals("4")) {
            handleViewOverdue();
        }
        else if (choice.equals("0")) {
            System.out.println("Goodbye!");
            return false;
        }
        else {
            System.out.println("\"" + choice + "\" is not an option. "
                + "Please select a number from the menu.");
        }
        return true;
    }


    // ----------------------------------------------------------
    /**
     * Prints the main menu options.
     */
    private void printMenu() {
        System.out.println();
        System.out.println("===== TaskTrack Menu =====");
        System.out.println(" 1. Add Assignment");
        System.out.println(" 2. Add Note");
        System.out.println(" 3. Complete Assignment");
        System.out.println(" 4. View Overdue Assignments");
        System.out.println(" 0. Quit");
    }


    // ----------------------------------------------------------
    /**
     * Collects details to add a new assignment.
     */
    private void handleAddAssignment() {
        String title = readLine("Title: ");
        if (title == null) return;

        String text = readLine("Description: ");
        if (text == null) return;

        String dueDate = readLine("Due Date (YYYY-MM-DD): ");
        if (dueDate == null) return;

        String className = readLine("Class Name: ");
        if (className == null) return;

        manager.createAssignment(title, text, dueDate, className);
        System.out.println("Assignment added successfully!");
    }


    // ----------------------------------------------------------
    /**
     * Collects details to add a new note.
     */
    private void handleAddNote() {
        String title = readLine("Title: ");
        if (title == null) return;

        String text = readLine("Text: ");
        if (text == null) return;

        manager.createNote(title, text);
        System.out.println("Note added successfully!");
    }


    // ----------------------------------------------------------
    /**
     * Marks an assignment complete by ID.
     */
    private void handleCompleteAssignment() {
        String id = readLine("Enter Assignment ID to mark complete: ");
        if (id == null) return;

        boolean success = manager.completeAssignment(id);
        if (success) {
            System.out.println("Assignment marked as completed.");
        }
        else {
            System.out.println("Assignment ID not found.");
        }
    }


    // ----------------------------------------------------------
    /**
     * Displays all overdue assignments.
     */
    private void handleViewOverdue() {
        System.out.println("\n--- Overdue Assignments ---");
        var overdue = manager.overdueAssignments();
        if (overdue == null || overdue.isEmpty()) {
            System.out.println("No overdue assignments found.");
        }
        else {
            for (Object item : overdue) {
                System.out.println("  " + item);
            }
        }
    }


    // ----------------------------------------------------------
    /**
     * Shows a prompt and reads one line.
     *
     * @param prompt the question to show
     * @return the line, or null if there is no more input
     */
    private String readLine(String prompt) {
        System.out.print(prompt);

        if (!input.hasNextLine()) {
            System.out.println();
            return null;
        }
        return cleanInput(input.nextLine());
    }


    // ----------------------------------------------------------
    /**
     * Cleans hidden character input artifacts.
     *
     * @param text the line typed
     * @return cleaned text
     */
    private String cleanInput(String text) {
        String cleaned = "";
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if (Character.isDigit(c)) {
                cleaned = cleaned + Character.getNumericValue(c);
            }
            else if (Character.isSpaceChar(c) || Character.isWhitespace(c)) {
                cleaned = cleaned + " ";
            }
            else if (Character.getType(c) != Character.FORMAT) {
                cleaned = cleaned + c;
            }
        }
        return cleaned.trim();
    }
}
