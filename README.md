
# TaskTrack

## Project Overview

TaskTrack is a Java application designed to help students organize their academic responsibilities. The application allows students to manage assignments and notes, track assignment completion, search for items, and remove items they no longer need.

TaskTrack uses an instructor-provided generic `LinkedChain<T>` data structure to store different types of items. The project demonstrates object-oriented programming, generics, linked data structures, input validation, unit testing, and team collaboration.

## Features

The application is designed to support the following features:

- Add assignments with a title, description, due date, and course
- Add notes with a title and text
- View all assignments and notes
- Search for items by title or text
- Mark assignments as completed
- Remove assignments or notes by ID
- Clear all stored items
- Count assignments, notes, and total items
- View incomplete assignments
- Save item information to a text file

The core manager functionality has been implemented. Integration with the complete command-line menu is part of the remaining project work.

## Project Structure

Java source files are organized in the `src/tracker` package.

| File | Description |
|------|-------------|
| `Item.java` | Interface defining shared methods for assignments and notes |
| `Assignment.java` | Represents an assignment with its details and completion status |
| `Note.java` | Represents a note with a title and text |
| `LinkedChain.java` | Instructor-provided generic linked data structure |
| `TrackerManager.java` | Manages creating, searching, removing, and completing items |
| `Main.java` | Command-line menu and user interaction |
| `LinkedChainTest.java` | Unit tests for the LinkedChain class |
| `TrackerManagerTest.java` | Unit tests for TrackerManager |
| `AssignmentTest.java` | Unit tests for Assignment |
| `NoteTest.java` | Unit tests for Note |

The test classes for `Assignment` and `Note` are part of the planned testing coverage and should be included in the repository once completed.

## Data Structure

TaskTrack uses the instructor-provided `LinkedChain<T>` implementation.

The `TrackerManager` stores items in a `LinkedChain<Item>`. Both `Assignment` and `Note` implement the `Item` interface, allowing different item types to be stored in the same collection.

The manager interacts with the linked chain using its public methods, including:

- `add()` - Adds an item to the chain
- `remove()` - Removes an item
- `get()` - Retrieves an item by index
- `size()` - Returns the number of items
- `clear()` - Removes all items

The application does not directly access or modify the private nodes of the linked chain.

The generic linked chain is also tested with `String` objects, demonstrating that it supports multiple element types.

## How to Run the Application

### Requirements

- Java JDK
- Eclipse IDE
- `student.jar` for unit testing

### Setup Instructions

1. Clone the TaskTrack GitHub repository.
2. Open Eclipse.
3. Select **File > Import > Existing Projects into Workspace**.
4. Select the TaskTrack project folder.
5. Confirm that the Java source files are in the `tracker` package.
6. Make sure the project has a compatible JDK configured.
7. Run `Main.java` as a Java Application after the command-line menu has been integrated.

The application manager can also be tested independently before the full menu is completed.

## How to Run Tests

TaskTrack uses the `student.TestCase` testing framework.

1. Open the project in Eclipse.
2. Right-click the project and select **Build Path > Configure Build Path**.
3. Open the **Libraries** tab.
4. Add `student.jar` if it is not already configured.
5. Open a test class.
6. Right-click and select **Run As > JUnit Test**.
7. Review the test results in Eclipse.

### Test Classes

| Test Class | Testing Focus |
|------------|---------------|
| `LinkedChainTest.java` | Adding, removing, retrieving, counting, and clearing entries |
| `TrackerManagerTest.java` | Item creation, searching, completion, removal, and validation |
| `AssignmentTest.java` | Assignment fields, completion status, updates, and applicable invalid inputs |
| `NoteTest.java` | Note fields, updates, and applicable invalid inputs |

### Testing Strategy

Our testing approach includes normal operations, boundary cases, and invalid inputs.

Examples include:

- Adding items to an empty collection
- Removing existing and nonexistent items
- Handling duplicate entries
- Searching for matching and missing items
- Handling invalid item IDs
- Rejecting null or blank required inputs
- Marking assignments as completed
- Preventing notes from being marked as completed
- Clearing populated and empty collections
- Accessing invalid linked-chain indexes

The team will run the complete test suite after integrating all application classes and resolve any remaining failures.

## Input Validation

TaskTrack includes validation to handle unexpected user input.

- Assignment titles cannot be null, empty, or whitespace-only.
- Note text cannot be null, empty, or whitespace-only.
- Invalid or nonexistent item IDs are handled without crashing.
- Notes cannot be marked as completed.
- Removing a nonexistent item returns `false`.
- The linked chain rejects null entries.
- Invalid linked-chain indexes throw `IndexOutOfBoundsException`.

Additional input validation will be reviewed during integration of the command-line menu.

## Reflection

One of the main challenges of this project was integrating a generic linked data structure with multiple application classes. We needed to ensure that assignments and notes could be stored together while still supporting their individual functionality.

Using the `Item` interface allowed both item types to share a common structure. Separating the data structure, application logic, and user interface made the project easier to organize and test.

Another challenge was handling invalid inputs and edge cases. Writing unit tests helped us think about situations such as empty collections, duplicate items, and invalid IDs. Working as a team also emphasized the importance of consistent method names, communication, and regularly integrating changes through GitHub.

## Next Steps

Before the next project milestone, our team plans to:

- Integrate the final `Assignment`, `Note`, `TrackerManager`, and `Main` classes.
- Connect the command-line menu to the manager's methods.
- Complete and run `AssignmentTest.java` and `NoteTest.java`.
- Run all unit tests against the integrated application.
- Add tests for any public methods and edge cases not yet covered.
- Review and improve input validation.
- Resolve compilation errors or integration issues.
- Improve due-date handling to identify overdue assignments accurately.
- Consider adding the ability to load previously saved data.

## Team Contributions

| Team Member | Responsibilities |
|-------------|------------------|
| Sreenidhi | LinkedChain integration, TrackerManager implementation, LinkedChainTest, TrackerManagerTest, and README documentation |
| Zara | Assignment, Note, AssignmentTest, and NoteTest |
| Miguel | Item interface, Main class, command-line menu, and system diagram |

### Integration Plan

Each team member develops and tests their assigned classes before integrating them into the shared GitHub repository.

The `Assignment` and `Note` classes implement the `Item` interface, allowing the `TrackerManager` to store both types in the generic linked chain.

The `Main` class will handle user interaction and call the appropriate `TrackerManager` methods.

After integration, the team will compile the complete project, run the unit tests, and resolve any issues caused by differences between class interfaces or method signatures.

## Generative AI Usage

Generative AI was used to assist with drafting portions of the Java implementation, developing initial unit tests, troubleshooting Eclipse and Git configuration, and organizing project documentation.

AI-generated suggestions were reviewed and adapted to the project requirements. The instructor-provided `LinkedChain<T>` data structure was intended to be retained without changes to its application-independent behavior.

The team is responsible for reviewing, testing, and verifying the final submitted code.

## Known Limitations

- The current `overdueAssignments()` method returns incomplete assignments rather than comparing due dates with the current date.
- The `save()` method writes item information to a text file, but loading saved data is not implemented.
- The complete command-line menu still requires integration and testing.
- Final test coverage must be verified after all team members' classes are combined.
