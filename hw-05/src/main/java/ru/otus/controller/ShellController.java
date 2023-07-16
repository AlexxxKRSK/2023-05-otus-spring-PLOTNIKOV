package ru.otus.controller;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@ShellComponent
public class ShellController {

    @ShellMethod(value = "Ping-pong", key = "ping")
    public String doSmthng() {
        return "pong";
    }
}
