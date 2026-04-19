package flc;

import java.util.List;
import java.util.Random;

import flc.model.Day;
import flc.model.Exercise;
import flc.model.Lesson;
import flc.model.Member;
import flc.model.Review;
import flc.model.TimeSlot;
import flc.system.FLCSystem;
import flc.gui.FLCReportGUI;

public class Main {
    public static void main(String[] args) {

        FLCSystem system = new FLCSystem();

        Exercise yoga = new Exercise("Yoga", 10);
        Exercise zumba = new Exercise("Zumba", 12);
        Exercise boxfit = new Exercise("BoxFit", 15);
        @SuppressWarnings("unused")
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
        FLCReportGUI gui = new FLCReportGUI(system);
        gui.setVisible(true);
    }
}