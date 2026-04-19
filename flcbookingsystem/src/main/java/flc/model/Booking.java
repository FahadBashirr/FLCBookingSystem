package flc.model;

public class Booking {
    private String bookingId;
    private Lesson lesson;

    public Booking(String bookingId, Lesson lesson) {
        this.bookingId = bookingId;
        this.lesson = lesson;
    }

    public Lesson getLesson() { return lesson; }
}