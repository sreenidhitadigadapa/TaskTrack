
# TaskTrack

## Project Overview

TaskTrack is a Java-based application designed to help students organize their academic responsibilities. Users can create assignments and notes, track assignment completion, search for items, and remove items they no longer need.

The application uses the instructor-provided generic `LinkedChain<T>` data structure to store and manage different types of items. This project demonstrates object-oriented programming, generics, linked data structures, input validation, unit testing, and team collaboration.

## Features

The prototype supports the following features:

- Add assignments with a title, description, due date, and course
- Add notes with a title and text
- View assignments and notes
- Search for items by title or text
- Mark assignments as completed
- Remove items by ID
- Clear all stored items
- View assignment, note, and total item counts
- View incomplete assignments
- Save item information to a text file

## Project Structure

All Java source files are located in the `src/tracker` package.

| File | Description |
|------|-------------|
| `Item.java` | Common interface for assignments and notes |
| `Assignment.java` | Stores assignment details and completion status |
| `Note.java` | Stores note details |
| `LinkedChain.java` | Instructor-provided generic linked data structure |
| `TrackerManager.java` | Handles application logic and item management |
| `Main.java` | Command-line interface for user interaction |
| `LinkedChainTest.java` | Unit tests for the generic linked chain |
| `TrackerManagerTest.java` | Unit tests for application management |
| `AssignmentTest.java` | Unit tests for assignment functionality |
| `NoteTest.java` | Unit tests for note functionality |

## Working Application Slice

The minimum viable product (MVP) allows users to create, view, search, complete, and remove items.

The application is organized into separate components:

- `Main` handles user input and displays results.
- `TrackerManager` handles application logic and validation.
- `Assignment` and `Note` represent the items being tracked.
- `Item` provides a common interface.
- `LinkedChain` stores the items.

The command-line menu provides a way to interact with the manager without directly accessing the underlying data structure.

## LinkedChain Reuse

TaskTrack uses the instructor-provided `LinkedChain<T>` implementation.

Both `Assignment` and `Note` implement the `Item` interface. The manager stores them together using:

`LinkedChain<Item>`

This allows two different element types to be stored and retrieved through the same generic collection without creating separate type-specific linked chains.

The generic linked chain is also tested using `String` objects to demonstrate that it works with different element types.

### Public API Usage

Application classes interact with `LinkedChain` only through its public methods, including:

- `add()` and `addToEnd()` - Insert entries
- `remove()` - Remove entries
- `get()` - Retrieve entries
- `size()` - Count entries
- `clear()` - Remove all entries

The application does not access the chain's private nodes or internal references. Application-specific operations, such as searching by title and completing assignments, are handled by `TrackerManager`.

## How to Run

### Requirements

- Java JDK 17 or later
- Eclipse IDE
- `student.jar` for unit testing

### Instructions

1. Clone the TaskTrack GitHub repository.
2. Open Eclipse.
3. Select **File > Import > Existing Projects into Workspace**.
4. Select the TaskTrack project folder.
5. Confirm that the Java source files are in the `tracker` package.
6. Open `Main.java`.
7. Right-click and select **Run As > Java Application**.
8. Use the command-line menu to interact with TaskTrack.

### Menu Options

| Option | Action |
|--------|--------|
| 1 | Add assignment |
| 2 | Add note |
| 3 | View assignments |
| 4 | View notes |
| 5 | Search items |
| 6 | Complete assignment |
| 7 | Remove item |
| 8 | View counts |
| 9 | View incomplete assignments |
| 10 | Clear all items |
| 11 | Save items |
| 0 | Exit |

## Unit Tests

TaskTrack uses the `student.TestCase` testing framework.

### How to Run Tests

1. Open the project in Eclipse.
2. Add `student.jar` to the Java Build Path if necessary.
3. Open a test class.
4. Right-click and select **Run As > JUnit Test**.
5. Review the results in the JUnit window.

### Test Coverage

| Test Class | Tests |
|------------|-------|
| `LinkedChainTest` | Adding, removing, retrieving, counting, clearing, duplicates, null values, and invalid indexes |
| `TrackerManagerTest` | Creating items, searching, completion, removal, counts, saving, and invalid inputs |
| `AssignmentTest` | Constructor, getters, completion, updates, and invalid assignment titles |
| `NoteTest` | Constructor, getters, updates, and invalid note fields |

The test suite is designed to cover normal behavior and applicable invalid-input cases for public application methods.

Additional edge cases include:

- Operations on an empty collection
- Removing items that do not exist
- Adding duplicate entries
- Searching for nonexistent items
- Invalid and missing IDs
- Null or blank required fields
- Invalid linked-chain indexes
- Completing an assignment more than once
- Attempting to complete a note

## Bad Input Handling

TaskTrack includes validation to prevent unexpected crashes and handle invalid user input.

| Invalid Input | Expected Behavior | Handled By |
|---------------|-------------------|------------|
| Empty assignment title | Throw `IllegalArgumentException` | `Assignment` |
| Empty note title | Throw `IllegalArgumentException` | `Note` |
| Empty note text | Throw `IllegalArgumentException` | `Note` |
| Non-numeric item ID | Return `null` or `false` | `TrackerManager` |
| Nonexistent item ID | Return `null` or `false` | `TrackerManager` |
| Attempt to complete a note | Return `false` | `TrackerManager` |
| Remove nonexistent item | Return `false` | `TrackerManager` |
| Add null to LinkedChain | Throw `IllegalArgumentException` | `LinkedChain` |
| Invalid LinkedChain index | Throw `IndexOutOfBoundsException` | `LinkedChain` |
| Invalid menu option | Display an error message and continue | `Main` |
| Empty save filename | Throw `IllegalArgumentException` | `TrackerManager` |

The command-line interface catches expected application exceptions and displays readable messages instead of terminating unexpectedly.

## Reflection

### What changed from our original specification, and why?

Our original specification focused on creating a simple application for managing assignments and notes. During implementation, we organized the application into separate classes for the user interface, item management, and data storage.

Using an `Item` interface allowed both assignments and notes to be stored in the same generic linked chain. We also focused on completing the core functionality before adding more advanced features.

### What challenges did we encounter?

One challenge was integrating different item types with the generic `LinkedChain`. We needed to make sure assignments and notes could be stored together while still supporting their individual methods.

Another challenge was coordinating method names and constructors across classes developed by different team members. For example, `TrackerManager` depends on the methods provided by `Assignment` and `Note`, so keeping these interfaces consistent was important.

### Did we change the LinkedChain?

We kept the linked data structure separate from application-specific logic. The `TrackerManager` uses the public methods of `LinkedChain` rather than modifying or accessing its internal nodes.

This allows the same generic data structure to support different element types without requiring separate implementations.

### What is working, and what is still unknown?

The prototype implementation includes item creation, searching, completion tracking, removal, counting, and basic saving functionality.

The remaining work is to verify the complete application after integrating all team members' classes, confirm that all tests pass, and review any missing edge cases.

### How did we use Generative AI?

Generative AI was used to assist with drafting portions of the implementation, generating initial test cases, troubleshooting Eclipse and Git, and organizing documentation.

The team reviewed and adapted these suggestions to the project requirements. Final code and tests must be checked by the team before submission.

## Next Steps

The following tasks are listed in priority order for the next milestone.

| Priority | Task | Owner | Status |
|----------|------|-------|--------|
| 1 | Finalize Assignment and Note implementations | Zara | Verify |
| 2 | Complete AssignmentTest and NoteTest | Zara | Verify |
| 3 | Integrate Main with TrackerManager | Miguel | Verify |
| 4 | Confirm LinkedChain and manager compatibility | Sreenidhi | Verify |
| 5 | Run LinkedChainTest and TrackerManagerTest | Sreenidhi | Verify |
| 6 | Test the complete command-line workflow | Entire team | Planned |
| 7 | Check every bad-input case from the scope | Entire team | Planned |
| 8 | Fix integration errors and failing tests | Entire team | Planned |
| 9 | Review final documentation and submission | Entire team | Planned |

### Stretch Goals

The following enhancements are being considered for future development:

| Stretch Goal | Status |
|--------------|--------|
| Identify overdue assignments using actual date comparisons | Not implemented |
| Load previously saved TaskTrack data | Not implemented |
| Improve searching and filtering | Not committed |

### Additional Tests

For the next milestone, the team plans to:

- Verify normal and invalid-input coverage for all public application methods
- Test the full command-line menu
- Test invalid menu selections
- Test operations on empty collections
- Test removing nonexistent entries
- Test integration between all application classes

## Team Contributions

| Team Member | Responsibilities |
|-------------|------------------|
| Sreenidhi | LinkedChain, TrackerManager, LinkedChainTest, TrackerManagerTest |
| Zara | Assignment, Note, AssignmentTest, NoteTest |
| Miguel | Item interface, Main class, command-line menu |

### Integration Process

Each team member develops and tests their assigned components before pushing changes to the shared GitHub repository.

Team members pull the latest changes before integrating new work. After integration, the team compiles the complete application, runs unit tests, and resolves any errors.

## Known Limitations

- The current `overdueAssignments()` method returns incomplete assignments rather than comparing due dates with the current date.
- Saving writes item information to a text file, but loading saved data is not implemented.
- Final integration and test results must be verified before submission.
