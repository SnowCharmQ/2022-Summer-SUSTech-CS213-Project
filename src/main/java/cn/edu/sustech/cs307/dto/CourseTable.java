package cn.edu.sustech.cs307.dto;

import java.time.DayOfWeek;
import java.util.Comparator;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;


public class CourseTable {
    public static class CourseTableEntry {
        /**
         * Course full name: String.format("%s[%s]", course.name, section.name)
         */
        public String courseFullName;
        /**
         * The section class's instructor
         */
        public Instructor instructor;
        /**
         * The class's begin and end time (e.g. 3 and 4).
         */
        public short classBegin, classEnd;
        /**
         * The class location.
         */
        public String location;

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            CourseTableEntry entry = (CourseTableEntry) o;
            return classBegin == entry.classBegin && classEnd == entry.classEnd && courseFullName
                    .equals(entry.courseFullName)
                    && instructor.equals(entry.instructor) && location.equals(entry.location);
        }

        @Override
        public String toString() {
            return "CourseTableEntry{" +
                    "courseFullName='" + courseFullName + '\'' +
                    ", instructor=" + instructor +
                    ", classBegin=" + classBegin +
                    ", classEnd=" + classEnd +
                    ", location='" + location + '\'' +
                    ", instructor=" + instructor +
                    '}';
        }

        public String toBeautifiedString() {
            return String.format("CourseTableEntry{fn:%s, begin:%s, end:%s, location:%s, instructor:%s}", courseFullName, classBegin, classEnd, location, instructor);
        }

        @Override
        public int hashCode() {
            return Objects.hash(courseFullName, instructor, classBegin, classEnd, location);
        }
    }

    /**
     * Stores all courses(encapsulated by CourseTableEntry) according to DayOfWeek.
     * The key should always be from MONDAY to SUNDAY, if the student has no course for any of the days, put an empty list.
     */
    public Map<DayOfWeek, Set<CourseTableEntry>> table;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CourseTable that = (CourseTable) o;
        return table.equals(that.table);
    }

    @Override
    public int hashCode() {
        return Objects.hash(table);
    }

    // For Debug Framework
    @Override
    public String toString() {
        var sb = new StringBuilder();
        sb.append("CourseTable{\n");
        var lst = table
                .keySet()
                .stream()
                .sorted(Comparator.comparingInt(DayOfWeek::getValue))
                .map(a -> table.get(a)).collect(Collectors.toList());
        for (int i = 0; i < lst.size(); i++) {
            var ordered = lst.get(i)
                    .stream()
                    .sorted(Comparator.comparing(a -> a.courseFullName))
                    .map(CourseTableEntry::toBeautifiedString)
                    .collect(Collectors.joining(",\n\t", "\t[", "],\n"));
            sb.append(i + 1).append(": ").append(ordered);
        }
        sb.append("}");
        return sb.toString();
    }
}
