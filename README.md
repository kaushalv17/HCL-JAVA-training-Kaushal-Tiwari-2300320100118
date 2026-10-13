# Library Management System-Kaushal Tiwari,2300320100118

Core Java console application for managing books, magazines, DVDs, members, borrowing, returns, late fees, waitlists, searching, and report export.

## Requirements
- JDK 8 or newer
- No external libraries

## Compile
From the `JAVA API ASSESMENT` folder:

```bash
javac -d out src/com/abes/library/Main.java src/com/abes/library/model/*.java src/com/abes/library/exception/*.java src/com/abes/library/repository/*.java src/com/abes/library/service/*.java src/com/abes/library/util/*.java
```

## Run
```bash
java -cp out com.abes.library.Main
```

## Features
- Books, magazines, and DVDs with validated fields
- Borrow and return items with late-fee calculation
- Three-item borrowing limit
- Waitlist notification when an item is returned
- Search by title, author, or item ID
- Sorting by year and title
- Text report export
- Custom exceptions and generic collection utilities

## Quick test cases
1. Add an item and search for it by ID.
2. Add a member and borrow an available item.
3. Try borrowing the same item again.
4. Borrow more than three items for one member.
5. Return an item and verify its status and waitlist notification.
6. Export a report and check `library-report.txt`.
