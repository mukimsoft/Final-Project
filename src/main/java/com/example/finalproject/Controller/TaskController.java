package com.example.finalproject.Controller;

import com.example.finalproject.entity.Task; // Ensure this path is correct for your project
import com.example.finalproject.repository.TaskRepository; // Ensure this path is correct for your project
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class TaskController {

    @Autowired
    private TaskRepository taskRepository;

    @GetMapping("/tasks")
    public String listTasks(Model model, Authentication auth) {
        String username = auth.getName();
        model.addAttribute("tasks", taskRepository.findByUsername(username));
        model.addAttribute("newTask", new Task());
        return "tasks";
    }

    @PostMapping("/tasks")
    public String addTask(@ModelAttribute Task task, Authentication auth) {
        task.setUsername(auth.getName());
        taskRepository.save(task);
        return "redirect:/tasks";
    }
}