package org.wecancodeit.courses;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Arrays;
import java.util.Collection;

@WebMvcTest(CourseController.class)
public class CourseControllerMVCTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CourseRepository courseRepository;

    @Mock
    private Course courseOne;

    @Mock
    private Course courseTwo;

    @Test
    public void shouldBeOkForAllCoursesInTemplate() throws Exception{
        mockMvc.perform(get("/courses")).andExpect(status().isOk())
                .andExpect(view().name("coursesTemplate"));
    }
    @Test
    public void shouldFindAllCoursesInModel() throws Exception{
        Collection<Course> allCoursesInModel = Arrays.asList(courseOne,courseTwo);
        when(courseRepository.findAll()).thenReturn(allCoursesInModel);
        mockMvc.perform(get("/courses"))
                .andExpect(model().attribute("coursesModel", allCoursesInModel));
    }
    @Test
    public void shouldBeOkForOneCoursesInTemplate() throws Exception {
        Long courseOneId = 1L;
        when(courseRepository.findOne(courseOneId)).thenReturn(courseOne);
        mockMvc.perform(get("/course?id=1")).andExpect(status().isOk())
                .andExpect(view().name("courseTemplate"));
    }
    @Test
    public void shouldFindCourseOneInModel() throws Exception{
        Long courseOneId = 1L;
        when(courseRepository.findOne(courseOneId)).thenReturn(courseOne);
        mockMvc.perform(get("/course?id=1"))
                .andExpect(model().attribute("courseModel", courseOne));

    }
    @Test
    public void shouldNotBeFoundForNoRequestInModel() throws Exception {
        Long courseTwoId = 2L;
        when(courseRepository.findOne(courseTwoId)).thenReturn(courseTwo);
        mockMvc.perform(get("/course?id=3")).andExpect(status().isNotFound());
    }
}
