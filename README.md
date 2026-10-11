
# TaskTrack - CS 2114 Project 2

## Project Description

TaskTrack is a Java application that helps students keep track of their assignments and notes. Users can add assignments, create notes, view their items, search for items, mark assignments as complete, and remove items.

The program uses a generic `LinkedChain<T>` to store both assignments and notes. This allows us to use the same data structure for different types of items.

## How to Compile and Run the Program

### Requirements
- Java JDK 17
- Eclipse IDE
- student.jar
- All Java project files

### Running the Program

1. Open Eclipse.
2. Create a new Java project called `TaskTrack`, or import the existing project.
3. Add all Java files to the `src` folder under the `tasktrack` package.
4. Make sure all files have `package tasktrack;` at the top.
5. Go to `Project > Build Automatically` and make sure it is enabled.
6. Open `Main.java`.
7. Right-click and select `Run As > Java Application`.
8. Use the menu in the Console to interact with the program.

The menu allows users to add assignments and notes, view items, search, mark assignments complete, remove items, clear the list, and view item counts.

## How to Run the Tests

We use `student.TestCase` to test our Java classes.

### Setting Up Tests

1. Open the project in Eclipse.
2. Right-click the project and select `Build Path > Configure Build Path`.
3. Under Libraries, add `student.jar` if it is not already included.
4. Make sure `LinkedChainTest.java` and `TrackerManagerTest.java` are in the project.

### Running Tests

1. Open `LinkedChainTest.java`.
2. Right-click and select `Run As > JUnit Test`.
3. Repeat for `TrackerManagerTest.java`.
4. Check the JUnit results to see which tests passed or failed.

Our tests are designed to check:
- Adding and removing items.
- Searching for existing and nonexistent items.
- Marking assignments as complete.
- Handling empty or invalid inputs.
- Counting items and allowing duplicates.
- Clearing the list.
- Handling empty lists without crashing.

**Testing Status:** The tests still need to be run and verified in Eclipse after integrating the final versions of all classes.

---

## Reflection

### What Changed From Our Specification?

Our original specification planned for us to build `LinkedChain<T>` ourselves, but the instructor provided this class for P2. Because of this, we focused more on using the linked chain in our application.

We also added methods to `TrackerManager` for removing items, clearing the list, counting items, and searching. We kept validation in `TrackerManager` and used the menu to display error messages. We also added more tests for invalid inputs and edge cases.

### What Changed in LinkedChain?

For the prototype, we used a temporary version of `LinkedChain` based on our specification. We still need to replace it with the instructor-provided version.

Our goal is to use the provided class through its public methods without changing its internal code.

### What Happened to Our Unknowns?

One thing we figured out was how to store both assignments and notes using the same linked chain. We used the `Item` interface so both types could be stored in a `LinkedChain<Item>`.

We also decided to use unique IDs so items with the same title can still be identified separately.

We still need to figure out how to load saved data and check whether assignments are overdue.

### What Doesn't Work Yet?

The current prototype supports adding, viewing, searching, completing, and removing items.

However, we still need to combine everyone's final classes and run all the tests in Eclipse. Loading saved data and checking overdue assignments are not finished yet.

We also need to make sure all invalid inputs are handled correctly.

---

## Next Steps

### What Is Left to Finish the MVP?

1. Replace the temporary `LinkedChain` with the instructor-provided version.
2. Combine everyone's classes into one working project.
3. Test all menu options and make sure they work correctly.
4. Fix any problems with invalid inputs.
5. Run all JUnit tests and fix any failures.
6. Test the complete application before submitting.

### Stretch Goals

After finishing the MVP, we plan to work on:

- **Saving and loading data:** Allow users to save assignments and notes between sessions.
- **Overdue assignments:** Show assignments that are past their due dates and not completed.

We will focus on finishing the MVP first. Other stretch goals will be postponed unless we have extra time.

### Additional Tests Needed

We plan to add tests for:

- Adding duplicate assignments and notes.
- Searching for items that do not exist.
- Removing items from an empty list.
- Marking an assignment complete more than once.
- Trying to mark a note as complete.
- Entering empty assignment titles or notes.
- Clearing an already empty list.
- Entering invalid menu options.
- Saving and loading data.
- Checking overdue assignments.

### Team Responsibilities

- **Sreenidhi:** Integrate `LinkedChain`, finish `TrackerManager`, and test both classes.
- **Zara:** Finish `Assignment` and `Note` and test their methods.
- **Miguel:** Finish the `Item` interface and main menu, including input validation.
- **All Members:** Combine the code, test the complete program, fix errors, and work on stretch goals if time allows.

---

## Attribution

| File | Team Member | Contribution |
|---|---|---|
| `LinkedChain.java` | Instructor / Sreenidhi | Instructor-provided class; Sreenidhi is responsible for integrating and testing it. |
| `LinkedChainTest.java` | Sreenidhi | GenAI-assisted tests for linked chain methods and edge cases. |
| `TrackerManager.java` | Sreenidhi | GenAI-assisted implementation of methods for managing assignments and notes. |
| `TrackerManagerTest.java` | Sreenidhi | GenAI-assisted tests for manager methods, validation, and edge cases. |
| `Assignment.java` | Zara | Responsible for assignment fields and methods. |
| `Note.java` | Zara | Responsible for note fields and methods. |
| `Item.java` | Miguel | Responsible for the interface shared by assignments and notes. |
| `Main.java` | Miguel | Responsible for the menu and user input handling. |

### GenAI Use

We used ChatGPT to help write parts of our Java code, create test cases, and organize our README. It helped us understand how to structure methods, handle invalid inputs, and identify edge cases. We are responsible for reviewing, understanding, and testing the code before submitting it.
