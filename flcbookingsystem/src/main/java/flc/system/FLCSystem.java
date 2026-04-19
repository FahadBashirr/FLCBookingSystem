package flc.system;

import flc.model.*;
import java.util.*;

public class FLCSystem {
    public List<Member> members = new ArrayList<>();
    public Timetable timetable = new Timetable();

    public void addMember(Member m) { members.add(m); }
    public void addLesson(Lesson l) { timetable.addLesson(l); }

    public void generateAttendanceReport() {
        System.out.println("=== Attendance Report ===");
        for (Lesson l : timetable.getAllLessons()) {
            System.out.println(
                l.getLessonId() + " | " +
                l.getExercise().getName() + " | Members: " +
                l.getBookings().size() + " | Avg Rating: " +
                l.getAverageRating()
            );
        }
    }

    public void generateIncomeReport() {
        Map<String, Double> income = new HashMap<>();

        for (Lesson l : timetable.getAllLessons()) {
            income.merge(l.getExercise().getName(), l.getIncome(), Double::sum);
        }

        String maxExercise = Collections.max(income.entrySet(), Map.Entry.comparingByValue()).getKey();
        System.out.println("Highest income exercise: " + maxExercise);
    }
}