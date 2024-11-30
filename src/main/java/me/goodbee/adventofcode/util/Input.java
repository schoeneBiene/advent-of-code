package me.goodbee.adventofcode.util;

import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Input {
    public static String getTxt(String fileName) {
        try {
            Path path = Paths.get(Input.class.getClassLoader().getResource(fileName).toURI());

            return new String(Files.readAllBytes(path));
        } catch(IOException | URISyntaxException | NullPointerException e) {
            System.out.println("warning you dont got that file!!");
            e.printStackTrace();
            return "";
        }
    }
}
