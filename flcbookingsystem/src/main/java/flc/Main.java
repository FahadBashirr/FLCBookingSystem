package flc;

import flc.model.*;
import flc.system.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {

        FLCSystem system = new FLCSystem();

        Exercise yoga = new Exercise("Yoga", 10);
        Exercise zumba = new Exercise("Zumba", 12);
        Exercise boxfit = new Exercise("BoxFit", 15);
        Exercise aquacise = new Exercise("Aquacise", 8);

        // 48 lessons
        int id = 1;
        for (int w = 1; w <= 8; w++) {
            for (Day day : Day.values()) {
                system.addLesson(new Lesson("L" + id++, yoga, day, TimeSlot.MORNING));
                system.addLesson(new Lesson("L" + id++, zumba, day, TimeSlot.AFTERNOON));
                system.addLesson(new Lesson("L" + id++, boxfit, day, TimeSlot.EVENING));
            }
        }

        // Members
        for (int i = 1; i <= 10; i++) {
            system.addMember(new Member("M" + i, "Member" + i));
        }

        // Bookings
        Random r = new Random();
        List<Lesson> lessons = system.timetable.getAllLessons();

        for (Member m : system.members) {
            for (int j = 0; j < 3; j++) {
                m.bookLesson(lessons.get(r.nextInt(lessons.size())));
            }
        }

        // Reviews (20)
        for (int i = 0; i < 20; i++) {
            lessons.get(r.nextInt(lessons.size()))
                   .addReview(new Review(r.nextInt(5) + 1, "Good"));
        }

        // Reports
        system.generateAttendanceReport();
        system.generateIncomeReport();
    }
}