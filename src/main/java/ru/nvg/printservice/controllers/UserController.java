package ru.nvg.printservice.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.nvg.printservice.dao.UserRepository;
import ru.nvg.printservice.domain.User;
import ru.nvg.printservice.dto.JobSummaryDto;
import ru.nvg.printservice.dto.SaveJobCmd;

@Slf4j
@RestController
@RequestMapping(UserController.BASE_URL)
public class UserController {
    public static final String BASE_URL = "/api/v1/users";

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping
    @ResponseBody
    public User saveJobBulk(@RequestBody @Validated String userName) {
        User user = new User();
        user.setName(userName);
        return userRepository.save(user);
    }
}
