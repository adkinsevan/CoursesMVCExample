package org.wecancodeit.courses;

import org.junit.jupiter.api.Test;

import java.util.Collection;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CourseRepositoryTest {

    private CourseRepository underTest;
    Course courseOne = new Course(1L, "course one name", "description");
    Course courseTwo = new Course(2L, "course two name", "description");

    @Test
    public void shouldFindCourseOne() {
        underTest = new CourseRepository(courseOne);
        Course foundCourse = underTest.findOne(1L);
        assertEquals(foundCourse, courseOne);
    }

    @Test
    public void shouldFindCourseOneAndTwo() {
        underTest = new CourseRepository(courseOne, courseTwo);
        Collection<Course> foundCourses = underTest.findAll();
        assertThat(foundCourses).contains(courseOne, courseTwo);
    }


}
