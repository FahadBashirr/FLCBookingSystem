package flc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import flc.model.Booking;
import flc.model.Day;
import flc.model.Exercise;
import flc.model.Lesson;
import flc.model.Member;
import flc.model.Review;
import flc.model.TimeSlot;

public class SystemTest {

    private Lesson yogaLesson;
    private Member m1, m2, m3, m4, m5;

    @BeforeEach
    @SuppressWarnings("unused")
    void setUp() {

        // ✅ Correct constructor using enums
        Exercise yogaExercise = new Exercise("Yoga", 10.0);
        yogaLesson = new Lesson(
                "Yoga",
                yogaExercise,
                Day.SATURDAY,
                TimeSlot.MORNING
        );

        m1 = new Member("M1", "Ali");
        m2 = new Member("M2", "Sara");
        m3 = new Member("M3", "John");
        m4 = new Member("M4", "Ayesha");
        m5 = new Member("M5", "Ahmed");
    }

    // ✅ 1. Capacity test (max 4 members)
    @Test
    void testLessonCapacityLimit() {
        assertTrue(m1.bookLesson(yogaLesson));
        assertTrue(m2.bookLesson(yogaLesson));
        assertTrue(m3.bookLesson(yogaLesson));
        assertTrue(m4.bookLesson(yogaLesson));

        // 5th must fail
        assertFalse(m5.bookLesson(yogaLesson));
    }

    // ✅ 2. Booking creation test
    @Test
    void testBookingCreation() {
        Booking booking = new Booking("B1", yogaLesson);

        assertNotNull(booking);
        assertEquals("Yoga", booking.getLesson().getName());
    }

    // ✅ 3. Rating system test
    @Test
    void testRatingSystem() {
        yogaLesson.addReview(new Review(5, ""));
        yogaLesson.addReview(new Review(4, ""));
        yogaLesson.addReview(new Review(3, ""));

        assertEquals(4.0, yogaLesson.getAverageRating(), 0.01);
    }

    // ✅ 4. Day validation test
    @Test
    void testDayAndTimeSlot() {
        assertEquals(Day.SATURDAY, yogaLesson.getDay());
        assertEquals(TimeSlot.MORNING, yogaLesson.getTimeSlot());
    }

    // ✅ 5. Income calculation test
    @Test
    void testIncomeCalculation() {
        m1.bookLesson(yogaLesson);
        m2.bookLesson(yogaLesson);

        double expectedIncome = 2 * yogaLesson.getExercise().getPrice();

        assertEquals(expectedIncome, yogaLesson.getIncome());
    }
}