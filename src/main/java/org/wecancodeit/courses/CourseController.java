package org.wecancodeit.courses;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;

@Controller
public class CourseController {

    @Resource
    private CourseRepository courseRepository;

    @RequestMapping("/courses")
    public String findAllCourses(Model model){
        model.addAttribute("coursesModel", courseRepository.findAll());
        return "coursesTemplate";
    }
    @RequestMapping("/course")
    public String findOneCourse(@RequestParam(value = "id") long id, Model model) throws CourseNotFoundException {

        if(courseRepository.findOne(id) == null){
            throw new CourseNotFoundException();
        }

        model.addAttribute("courseModel", courseRepository.findOne(id));
        return "courseTemplate";
    }
}
