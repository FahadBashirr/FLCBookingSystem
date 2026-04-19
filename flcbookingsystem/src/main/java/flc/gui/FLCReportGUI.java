package flc.gui;

import flc.model.Lesson;
import flc.system.FLCSystem;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class FLCReportGUI extends JFrame {

    private FLCSystem system;

    private JTable attendanceTable;
    private JTable incomeTable;

    private DefaultTableModel attendanceModel;
    private DefaultTableModel incomeModel;

    public FLCReportGUI(FLCSystem system) {
        this.system = system;

        setTitle("FLC System Reports");
        setSize(900, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JTabbedPane tabs = new JTabbedPane();

        // ---------- Attendance Tab ----------
        attendanceModel = new DefaultTableModel();
        attendanceModel.addColumn("Lesson ID");
        attendanceModel.addColumn("Exercise");
        attendanceModel.addColumn("Day");
        attendanceModel.addColumn("Time Slot");
        attendanceModel.addColumn("Bookings");
        attendanceModel.addColumn("Avg Rating");

        attendanceTable = new JTable(attendanceModel);
        tabs.add("Attendance Report", new JScrollPane(attendanceTable));

        // ---------- Income Tab ----------
        incomeModel = new DefaultTableModel();
        incomeModel.addColumn("Lesson ID");
        incomeModel.addColumn("Exercise");
        incomeModel.addColumn("Bookings");
        incomeModel.addColumn("Income (£)");

        incomeTable = new JTable(incomeModel);
        tabs.add("Income Report", new JScrollPane(incomeTable));

        add(tabs);

        loadAttendance();
        loadIncome();
    }

    private void loadAttendance() {
        attendanceModel.setRowCount(0);

        for (Lesson l : system.timetable.getAllLessons()) {
            attendanceModel.addRow(new Object[]{
                    l.getLessonId(),
                    l.getExercise().getName(),
                    l.getDay(),
                    l.getTimeSlot(),
                    l.getBookings().size(),
                    l.getAverageRating()
            });
        }
    }

    private void loadIncome() {
        incomeModel.setRowCount(0);

        for (Lesson l : system.timetable.getAllLessons()) {

            double income = l.getBookings().size() * l.getExercise().getPrice();

            incomeModel.addRow(new Object[]{
                    l.getLessonId(),
                    l.getExercise().getName(),
                    l.getBookings().size(),
                    income
            });
        }
    }
}