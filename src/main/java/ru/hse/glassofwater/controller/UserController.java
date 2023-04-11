package ru.hse.glassofwater.controller;

import org.springframework.web.bind.annotation.*;
import ru.hse.glassofwater.model.User;

public interface UserController {
    @GetMapping("{id}")
    User getUser(@PathVariable("id") Long id);

    @PostMapping
    User create(@RequestBody User user);

    @PutMapping("{id}")
    User update(@PathVariable("id") Long id, @RequestBody User user);

    @DeleteMapping("{id}")
    void delete(@PathVariable("id") Long id);
}
