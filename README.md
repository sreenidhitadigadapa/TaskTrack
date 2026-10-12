# TaskTrack — Project 2 Prototype + Tests

## Overview
TaskTrack is a Java console application for students to create assignments and notes, search for them, mark assignments complete, and remove items. `TrackerManager` stores both `Assignment` and `Note` as `Item` objects in one generic `LinkedChain<Item>`.

## MVP and Working Stretch Goal
- Add assignments (title, description, due date, course) and notes (title, text).
- View assignments and notes; search titles and text.
- Mark an assignment complete, remove an item by ID, clear the list, and view counts.
- View incomplete assignments and save a text snapshot.
- **Stretch goal implemented in code: course filtering.** Menu option **12** calls `TrackerManager.filterByCourse(String)` to show assignments for a selected course, ignoring case and surrounding whitespace. The feature is covered by `testSearchAndCourseFilter` and `testIncompleteAndClear`; confirm the menu behavior in Eclipse before marking it verified.

## Project Structure
All files use the `tracker` package.

| File | Purpose |
|---|---|
| `Item.java` | Common interface implemented by assignments and notes |
| `Assignment.java`, `Note.java` | Data models and validation |
| `LinkedChain.java` | Generic storage (replace reference copy with exact instructor-provided version) |
| `TrackerManager.java` | App logic, course filtering, persistence |
| `Main.java` | Interactive console menu and error messages |
| `LinkedChainTest.java`, `TrackerManagerTest.java`, `AssignmentTest.java`, `NoteTest.java` | Automated tests |

## Run and Test
Use JDK 17 in Eclipse. Import the project, ensure `student.jar` is on the build path, and run `tracker.Main` as a Java Application. Run each `*Test.java` class as a JUnit Test. **Actual course-library test execution and final team integration must be verified before submission.**

## LinkedChain Reuse and Public API
`Assignment` and `Note` implement `Item` and are stored in the same `LinkedChain<Item>`; no type-specific chain copies are used. `TrackerManager` only calls public chain methods (`addToEnd`, `get`, `size`, `remove`, `clear`). It never touches `Node` or `firstNode`. `LinkedChainTest` also uses `LinkedChain<String>` to verify generic behavior. Two application item types satisfy the **Meets** tier for LinkedChain reuse; the team is not claiming Exceeds for that category.

## Big-O Analysis
For **n** stored items, `LinkedChain.get(i)` traverses from the head in O(i), so loops using repeated `get(i)` cost O(n²).

| Manager operation | Time complexity | Notes |
|---|---|---|
| `createAssignment`, `createNote` | O(n) | `addToEnd` traverses to the tail |
| `findById`, `getAllItems` | O(n²) | Repeated indexed `get` |
| `removeById`, `removeItem` | O(n²) | ID lookup then chain removal |
| `getAssignments`, `getNotes`, `search`, `filterByCourse`, `getIncompleteAssignments` | O(n²) | Build lists using `getAllItems` |
| `countAssignments`, `countNotes` | O(n²) | Call filtered getters |
| `completeAssignment` | O(n²) | Calls `findById` |
| `size`, `clear` | O(1) | Chain maintains count and head |
| `save` | O(n²) plus file I/O | Traverses all items |

These are the measured-by-code estimates for this prototype. Compare them with the estimates in the team's original specification; repeated indexed traversal may be slower than an anticipated O(n) search/filter. A chain iterator or single-pass traversal could improve this in a future revision without adding app-specific logic to `LinkedChain`.

## Validation and Tests
- Blank assignment titles and blank note titles/text: `IllegalArgumentException` from model constructors or updates; `Main` catches and prints an error.
- Invalid or nonexistent IDs: manager returns `null` or `false` and menu prints a not-found response.
- Completing a note: returns `false`.
- Blank or unknown course filter: empty list, displayed as `No items found.`
- Invalid menu choice: message and menu continues.
- Null chain additions and invalid chain indexes: exceptions from `LinkedChain`.
- Missing removals and empty collections: handled without crashes.

`TrackerManagerTest` covers creating both types, invalid creation, missing IDs, removal, completion, searching, course filtering, empty lists, clearing, and saving. `LinkedChainTest`, `AssignmentTest`, and `NoteTest` cover model and collection edge cases. **Confirm the exact invalid-input responses against the team's original scope**, and add tests for any public method not yet exercised in both applicable normal and bad-input cases.

## Reflection
**1. What changed from the specification, and why?** We organized storage in `LinkedChain<Item>` and separated input (`Main`), operations (`TrackerManager`), and models (`Assignment`, `Note`). We implemented course filtering as the first stretch goal because the existing `course` field made it feasible without changing storage. These design decisions were refined during team integration and GenAI-assisted drafting; the team must confirm final decisions.

**2. Did LinkedChain change?** We did not add TaskTrack-specific logic to `LinkedChain`. The included reference copy should be replaced with the instructor's exact implementation; the manager calls only its public API.

**3. What is still unknown?** Final compatibility with Zara's and Miguel's latest classes, the exact instructor chain source, and passing results using the course `student.jar` must be confirmed. The current search/filter loops have O(n²) behavior due to repeated `get(i)` calls.

**4. What does not work yet?** Loading previously saved data is not implemented. Actual overdue-date detection, priority levels, weekly schedule, recurring assignments, and workload summaries are not implemented. Final Eclipse/JUnit verification remains pending.

## Next Steps
| Order | Task | Owner | Status |
|---|---|---|---|
| 1 | Confirm `Assignment` and `Note` signatures and tests | Zara | Verify |
| 2 | Integrate and manually exercise menu option 12 | Miguel | Verify |
| 3 | Replace reference `LinkedChain` with instructor original; verify API | Sreenidhi | Verify |
| 4 | Run all four JUnit test classes; add missing method-level edge cases | Sreenidhi and Zara | Pending |
| 5 | Run full MVP workflow and bad-input checks | Entire team | Pending |
| 6 | Compare Big-O table to scope estimates and update reflection | Entire team | Pending |
| 7 | Confirm README attribution and submit | Entire team | Pending |

### Stretch Goal Decisions
| Original stretch goal | Decision for prototype |
|---|---|
| Course filtering | **Committed — implemented; verify in Eclipse** |
| Priority levels | Deferred |
| Overdue assignment detection | Deferred |
| Course workload summary | Deferred |
| Weekly schedule view | Deferred |
| Recurring assignments | Deferred |

### Tests to add or verify
`testSearchAndCourseFilter`, `testIncompleteAndClear`, `testMissingIdsAndRemove`, `testBadCreation`, `testSaveAndBadFilename`, plus all existing model and linked-chain tests. Add a menu-level invalid-choice test if your course test setup supports console-input simulation.

## Team Ownership and GenAI
- **Sreenidhi:** `TrackerManager`, `TrackerManagerTest`, LinkedChain integration/tests, README.
- **Zara:** `Assignment`, `Note`, and their tests.
- **Miguel:** `Item`, `Main`, menu integration, system diagram.

GenAI helped draft and revise code, tests, and documentation. Team members should review, test, and attribute their final contributions accurately before submission.
