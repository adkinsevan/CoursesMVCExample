package org.wecancodeit.courses;

import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Repository
public class CourseRepository {

    Map<Long, Course> courseList = new HashMap<>();

    public CourseRepository(){
        Course reading = new Course(1L, "Reading", "In this Course you learning how to read");
        Course music = new Course(2L,"Music","In this Course you are learning to play music");
        Course java =  new Course(3L,"Java","In this Course you are learning to code Java");

        courseList.put(reading.getId(),reading);
        courseList.put(music.getId(),music);
        courseList.put(java.getId(),java);
    }

    public CourseRepository(Course ...coursesToAdd) {
        for(Course course: coursesToAdd){
            courseList.put(course.getId(),course);
        }
    }

    public Course findOne(long id) {
        return courseList.get(id);
    }

    public Collection<Course> findAll() {
        return courseList.values();
    }
}
