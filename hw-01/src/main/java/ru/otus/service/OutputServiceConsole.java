package ru.otus.service;

import lombok.Data;

@Data
public class OutputServiceConsole implements OutputService {

    @Override
    public void outputString(String s) {
        System.out.println(s);
    }

}
