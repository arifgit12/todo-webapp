package edu.aam.app.controller;

import edu.aam.app.model.Log;
import edu.aam.app.model.vm.LogDTO;
import edu.aam.app.service.log.LogService;
import edu.aam.app.service.notification.INotificationService;
import edu.aam.app.util.AuthenticatedUser;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

    @Autowired
    private INotificationService notificationService;

    @GetMapping
    public String pageBy(@RequestParam(required = false) Integer page, Model model) {

        Pageable pageable;
        if(page == null || page < 1) {
            pageable = PageRequest.of(0, 5);
        } else {
            pageable = PageRequest.of(page-1, 5);
        }

        Page<Log> logPage = logService.listAll(pageable);
        List<LogDTO> logs = logPage.getContent().stream()
                .map(log -> modelMapper.map(log, LogDTO.class))
                .collect(Collectors.toList());

        if(page==null || page < 1 || page > logPage.getTotalPages()) {
            page=1;
        }

        model.addAttribute("notification_number_todo", notificationService.countUnseenNotifications(AuthenticatedUser.findLoggedInUsername()));
        model.addAttribute("maxPages", logPage.getTotalPages());
        model.addAttribute("page", page);
        model.addAttribute("logs", logs);

        return "logs/list-logs";
    }

    @GetMapping("latest")
    public List<LogDTO> pageLatest(@RequestParam(name = "top", defaultValue = "10") int top) {
        return null;
    }
}
