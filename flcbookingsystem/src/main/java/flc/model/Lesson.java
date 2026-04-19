package flc.model;

import java.util.ArrayList;
import java.util.List;

public class Lesson {
    private String lessonId;
    private Exercise exercise;
    private Day day;
    private TimeSlot timeSlot;
    private List<Booking> bookings = new ArrayList<>();
    private List<Review> reviews = new ArrayList<>();
    private final int capacity = 4;

    public Lesson(String lessonId, Exercise exercise, Day day, TimeSlot timeSlot) {
        this.lessonId = lessonId;
        this.exercise = exercise;
        this.day = day;
        this.timeSlot = timeSlot;
    }

    public boolean isFull() {
        return bookings.size() >= capacity;
    }

    public void addBooking(Booking booking) {
        bookings.add(booking);
    }

    public void addReview(Review review) {
        reviews.add(review);
    }

    public double getAverageRating() {
        if (reviews.isEmpty()) return 0;
        return reviews.stream().mapToInt(Review::getRating).average().orElse(0);
    }

    public double getIncome() {
        return bookings.size() * exercise.getPrice();
    }

    public String getName() { return exercise.getName(); }
    public Exercise getExercise() { return exercise; }
    public Day getDay() { return day; }
    public TimeSlot getTimeSlot() { return timeSlot; }
    public List<Booking> getBookings() { return bookings; }
    public String getLessonId() { return lessonId; }
}