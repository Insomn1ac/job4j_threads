package ru.job4j.io;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.function.Predicate;

public class ContentGetterWithoutUnicode implements ContentGetter {
    private final ParseFile file;

    public ContentGetterWithoutUnicode(ParseFile file) {
        this.file = file;
    }

    @Override
    public String getContent() {
        Predicate<Character> predicate = ch -> ch < 0x80;
        return getContentWithoutUnicode(predicate);
    }

    public synchronized String getContentWithoutUnicode(Predicate<Character> predicate) {
        StringBuilder output = new StringBuilder();
        try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file.getFile()))) {
            int data;
            while ((data = bis.read()) > 0) {
                if (predicate.test((char) data)) {
                    output.append((char) data);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return output.toString();
    }
}
