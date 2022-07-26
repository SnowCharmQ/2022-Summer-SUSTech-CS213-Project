package cn.edu.sustech.cs307.dto;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class CourseSearchEntry {
    /**
     * The course of the searched section
     */
    public Course course;

    /**
     * The searched course section
     */
    public CourseSection section;

    /**
     * All classes of the section
     */
    public Set<CourseSectionClass> sectionClasses;

    /**
     * List all course or time conflicting courses' full name, sorted by courseName, then sectionName.
     * Course full name: String.format("%s[%s]", course.name, section.name)
     * <p>
     * The conflict courses come from the student's enrolled courses (' sections).
     * <p>
     * Course conflict is when multiple sections belong to the same course.
     * Time conflict is when multiple sections have time-overlapping classes.
     * Note that a section is both course and time conflicting with itself!
     */
    public List<String> conflictCourseNames;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CourseSearchEntry entry = (CourseSearchEntry) o;
        return course.equals(entry.course) && section.equals(entry.section)
                && sectionClasses.equals(entry.sectionClasses)
                && conflictCourseNames.equals(entry.conflictCourseNames);
    }

    @Override
    public int hashCode() {
        return Objects.hash(course, section, sectionClasses, conflictCourseNames);
    }

    // For Debug Framework
    @Override
    public String toString() {
        return String.format("\n\tCourseSearchEntry{id:%s, secID:%s, %s[%s], clsID:%s, cf_s:%s}",
                course.id, section.id, course.name, section.name, sectionClasses.stream().map(c->c.id+"")
                        .collect(Collectors.joining(",", "(", ")")), conflictCourseNames);
    }
}
