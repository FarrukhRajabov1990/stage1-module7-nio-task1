package com.epam.mjc.nio;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public class FileReader {

    public Profile getDataFromFile(File file) {

        Path path = Paths.get(file.getPath());
        List<String> arrayOfString = new ArrayList<>();
        try(BufferedReader reader = Files.newBufferedReader(path)) {

            arrayOfString = reader.lines().collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }

        Map<String, String> mapList = new HashMap<>();
        for (String words : arrayOfString) {
            String[] splitWords = words.split(":");
            mapList.put(splitWords[0].trim(), splitWords[1].trim());
        }

        Profile newProfile = new Profile();

        try {
            newProfile.setName(mapList.get("Name"));
            newProfile.setAge(Integer.parseInt(mapList.get("Age")));
            newProfile.setEmail(mapList.get("Email"));
            newProfile.setPhone(Long.parseLong(mapList.get("Phone")));
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        return newProfile;
    }
}
