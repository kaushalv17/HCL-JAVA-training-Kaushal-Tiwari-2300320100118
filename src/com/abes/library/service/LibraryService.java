package com.abes.library.service;

import com.abes.library.model.*;
import com.abes.library.repository.LibraryRepository;
import com.abes.library.exception.*;
import java.io.*;
import java.nio.file.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class LibraryService {
    private final LibraryRepository repository;

    public LibraryService(LibraryRepository repository) { this.repository = repository; }
    public LibraryRepository getRepository() { return repository; }

    public void borrow(String memberId, String itemId) throws LibraryException {
        Member member = repository.getMember(memberId);
        LibraryItem item = repository.getItem(itemId);
        if (member.getBorrowedItems().size() >= 3) throw new BorrowLimitExceededException("A member can borrow at most 3 items");
        if (!item.isAvailable()) {
            repository.addToWaitlist(itemId, member);
            throw new ItemNotAvailableException("Item is unavailable. Member added to waitlist.");
        }
        item.borrowItem();
        member.addBorrowedItem(item);
    }

    public double returnItem(String memberId, String itemId, LocalDate borrowedOn, LocalDate returnedOn) throws LibraryException {
        Member member = repository.getMember(memberId);
        LibraryItem item = repository.getItem(itemId);
        if (!member.getBorrowedItems().contains(item)) throw new ItemNotAvailableException("This member has not borrowed this item");
        long days = Math.max(0, ChronoUnit.DAYS.between(borrowedOn.plusDays(14), returnedOn));
        double fee = days * item.getLateFeePerDay();
        member.removeBorrowedItem(item);
        item.returnItem();
        Member next = repository.notifyNext(itemId);
        if (next != null) System.out.println("Waitlist notification: " + next.getName() + " (" + next.getMemberId() + ") can borrow " + item.getTitle());
        return fee;
    }

    public void exportReport(String fileName) throws LibraryException {
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(fileName))) {
            writer.write("LIBRARY REPORT");
            writer.newLine();
            writer.write("Items");
            writer.newLine();
            for (LibraryItem item : repository.getItems()) {
                writer.write(item.toString());
                writer.newLine();
            }
            writer.write("Members");
            writer.newLine();
            for (Member member : repository.getMembers()) {
                writer.write(member.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            throw new LibraryException("Unable to export report", e);
        }
    }

    public List<LibraryItem> sortedByYear() {
        List<LibraryItem> result = new ArrayList<>(repository.getItems());
        result.sort(Comparator.comparingInt(LibraryItem::getYear).thenComparing(LibraryItem::getTitle));
        return result;
    }
}
