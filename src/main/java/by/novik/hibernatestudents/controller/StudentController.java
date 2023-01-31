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
@RequestMapping("students")
public class OrderController {
    private final StudentService studentService;

    @GetMapping
    public String findAll(Model model) {
        model.addAttribute("students_list",studentService.findAll());
        return "students_page";
    }
    @GetMapping("{id}")
    public String findById(Model model, @PathVariable(name = "id")  Long id) {
        Student student = studentService.findById(id).orElseThrow();

        model.addAttribute("single_student",student);

        return "student_page";
    }

    @PostMapping
    public String createStudent(Student student,Model model) {
        studentService.save(student);
        model.addAttribute("students_list",studentService.findAll());
        return "students_page";
    }



    @GetMapping("delete/{id}")
    public String deleteOrder(@PathVariable Long id) {
        studentService.delete(id);
        return "redirect:/students";
    }

}
