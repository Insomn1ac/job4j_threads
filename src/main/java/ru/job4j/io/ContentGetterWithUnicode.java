package ru.job4j.io;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;

public class ContentGetterWithUnicode implements ContentGetter {
    private final ParseFile file;

    public ContentGetterWithUnicode(ParseFile file) {
        this.file = file;
    }

    @Override
    public synchronized String getContent() {
        StringBuilder output = new StringBuilder();
        try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file.getFile()))) {
            int data;
            while ((data = bis.read()) > 0) {
                output.append((char) data);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return output.toString();
    }
}
