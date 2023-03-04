package by.novik.hibernatestudents.controller;


import by.novik.hibernatestudents.entity.Student;
import by.novik.hibernatestudents.service.StudentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequiredArgsConstructor
@Slf4j
@SessionAttributes("student")
@RequestMapping("students")
public class StudentController {
    private final StudentService studentService;

    @GetMapping
    public String findAll(Model model) {
        model.addAttribute("students_list", studentService.findAll());
        return "students";
    }


    @PostMapping
    public String create(Student student, Model model) {

        studentService.save(student);
        model.addAttribute("students_list", studentService.findAll());
        return "students";
    }

    @ModelAttribute(name = "student")
    public Student getNewStudent() {
        return new Student();
    }

    @GetMapping("delete/{id}")
    public String delete(@PathVariable Long id) {
        studentService.delete(id);
        return "redirect:/students";
    }

}
