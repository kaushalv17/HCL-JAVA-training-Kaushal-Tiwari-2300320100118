package com.abes.library;

import com.abes.library.model.*;
import com.abes.library.repository.LibraryRepository;
import com.abes.library.service.LibraryService;
import com.abes.library.exception.*;
import java.time.LocalDate;
import java.util.*;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final LibraryRepository repository = new LibraryRepository();
    private static final LibraryService service = new LibraryService(repository);

    public static void main(String[] args) {
        boolean running = true;
        while (running) {
            printMenu();
            String choice = scanner.nextLine().trim();
            try {
                switch (choice) {
                    case "1": addBook(); break;
                    case "2": addMagazine(); break;
                    case "3": addDVD(); break;
                    case "4": addMember(); break;
                    case "5": showItems(); break;
                    case "6": borrowItem(); break;
                    case "7": returnItem(); break;
                    case "8": search(); break;
                    case "9": showMembers(); break;
                    case "10": exportReport(); break;
                    case "11": showSorted(); break;
                    case "0": running = false; break;
                    default: System.out.println("Invalid choice");
                }
            } catch (LibraryException | IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            } catch (RuntimeException e) {
                System.out.println("Error: Please enter valid input");
            }
        }
        System.out.println("Goodbye");
    }

    private static void printMenu() {
        System.out.println("\nLIBRARY MANAGEMENT SYSTEM");
        System.out.println("1. Add Book");
        System.out.println("2. Add Magazine");
        System.out.println("3. Add DVD");
        System.out.println("4. Register Member");
        System.out.println("5. List Items");
        System.out.println("6. Borrow Item");
        System.out.println("7. Return Item");
        System.out.println("8. Search Items");
        System.out.println("9. List Members");
        System.out.println("10. Export Report");
        System.out.println("11. Sort Items by Year");
        System.out.println("0. Exit");
        System.out.print("Choice: ");
    }

    private static void addBook() {
        System.out.print("Title: "); String title = scanner.nextLine();
        System.out.print("Year: "); int year = Integer.parseInt(scanner.nextLine());
        System.out.print("Author: "); String author = scanner.nextLine();
        System.out.print("ISBN: "); String isbn = scanner.nextLine();
        Book book = new Book(title, year, author, isbn);
        repository.addItem(book);
        System.out.println("Added: " + book);
    }

    private static void addMagazine() {
        System.out.print("Title: "); String title = scanner.nextLine();
        System.out.print("Year: "); int year = Integer.parseInt(scanner.nextLine());
        System.out.print("Issue number: "); int issue = Integer.parseInt(scanner.nextLine());
        Magazine magazine = new Magazine(title, year, issue);
        repository.addItem(magazine);
        System.out.println("Added: " + magazine);
    }

    private static void addDVD() {
        System.out.print("Title: "); String title = scanner.nextLine();
        System.out.print("Year: "); int year = Integer.parseInt(scanner.nextLine());
        System.out.print("Duration in minutes: "); int duration = Integer.parseInt(scanner.nextLine());
        DVD dvd = new DVD(title, year, duration);
        repository.addItem(dvd);
        System.out.println("Added: " + dvd);
    }

    private static void addMember() {
        System.out.print("Member ID: "); String id = scanner.nextLine();
        System.out.print("Name: "); String name = scanner.nextLine();
        System.out.print("Email: "); String email = scanner.nextLine();
        Member member = new Member(id, name, email);
        repository.addMember(member);
        System.out.println("Member registered");
    }

    private static void showItems() {
        if (repository.getItems().isEmpty()) System.out.println("No items found");
        for (LibraryItem item : repository.getItems()) System.out.println(item);
    }

    private static void borrowItem() throws LibraryException {
        System.out.print("Member ID: "); String memberId = scanner.nextLine();
        System.out.print("Item ID: "); String itemId = scanner.nextLine();
        service.borrow(memberId, itemId);
        System.out.println("Item borrowed successfully. Standard borrowing period: 14 days");
    }

    private static void returnItem() throws LibraryException {
        System.out.print("Member ID: "); String memberId = scanner.nextLine();
        System.out.print("Item ID: "); String itemId = scanner.nextLine();
        System.out.print("Borrow date (yyyy-mm-dd): "); LocalDate borrowed = LocalDate.parse(scanner.nextLine());
        System.out.print("Return date (yyyy-mm-dd): "); LocalDate returned = LocalDate.parse(scanner.nextLine());
        double fee = service.returnItem(memberId, itemId, borrowed, returned);
        System.out.printf(Locale.ROOT, "Item returned. Late fee: %.2f%n", fee);
    }

    private static void search() {
        System.out.print("Search title, author, or item ID: ");
        List<LibraryItem> found = repository.search(scanner.nextLine());
        if (found.isEmpty()) System.out.println("No matching items");
        for (LibraryItem item : found) System.out.println(item);
    }

    private static void showMembers() {
        if (repository.getMembers().isEmpty()) System.out.println("No members found");
        for (Member member : repository.getMembers()) System.out.println(member);
    }

    private static void exportReport() throws LibraryException {
        System.out.print("Report file name (default library-report.txt): ");
        String name = scanner.nextLine().trim();
        if (name.isEmpty()) name = "library-report.txt";
        service.exportReport(name);
        System.out.println("Report exported to " + name);
    }

    private static void showSorted() {
        for (LibraryItem item : service.sortedByYear()) System.out.println(item);
    }
}
