package edu.aam.app.controller;

import edu.aam.app.model.vm.LogDTO;
import edu.aam.app.service.log.LogService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/logs")
public class LogController {

    @Autowired
    private LogService logService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    public String pageBy(Model model) {

        List<LogDTO> logs = logService.listAll().stream()
                .map(log -> modelMapper.map(log, LogDTO.class))
                .collect(Collectors.toList());
        model.addAttribute("logs", logs);
        return "logs/list-logs";
    }

    @GetMapping("latest")
    public List<LogDTO> pageLatest(@RequestParam(name = "top", defaultValue = "10") int top) {
        return null;
    }
}
