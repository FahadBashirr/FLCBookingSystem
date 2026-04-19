package flc.system;

import java.util.ArrayList;
import java.util.List;

import flc.model.Day;
import flc.model.Lesson;

public class Timetable {
    private final List<Lesson> lessons = new ArrayList<>();

    public void addLesson(Lesson lesson) {
        lessons.add(lesson);
    }

    public List<Lesson> getLessonsByDay(Day day) {
        List<Lesson> result = new ArrayList<>();
        for (Lesson l : lessons) {
            if (l.getDay() == day) result.add(l);
        }
        return result;
    }

    public List<Lesson> getLessonsByExercise(String name) {
        List<Lesson> result = new ArrayList<>();
        for (Lesson l : lessons) {
            if (l.getExercise().getName().equalsIgnoreCase(name)) {
                result.add(l);
            }
        }
        return result;
    }

    public Lesson getLesson(String id) {
        for (Lesson l : lessons) {
            if (l.getLessonId().equals(id)) return l;
        }
        return null;
    }

    public List<Lesson> getAllLessons() {
        return lessons;
    }
}