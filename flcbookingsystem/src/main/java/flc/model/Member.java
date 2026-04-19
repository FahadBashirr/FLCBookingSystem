package flc.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Member {
    private String id;
    private String name;
    private List<Booking> bookings = new ArrayList<>();

    public Member(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public boolean bookLesson(Lesson lesson) {
        if (lesson.isFull()) return false;

        for (Booking b : bookings) {
            if (b.getLesson().getDay() == lesson.getDay() &&
                b.getLesson().getTimeSlot() == lesson.getTimeSlot()) {
                return false;
            }
        }

        Booking booking = new Booking(UUID.randomUUID().toString(), lesson);
        bookings.add(booking);
        lesson.addBooking(booking);
        return true;
    }

    public List<Booking> getBookings() { return bookings; }
    public String getId() { return id; }
}