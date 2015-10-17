package com.tangotv.cli.configs;


import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public final class Config {

    private static final Map<String, Object> env = new HashMap<>();
    private static final String CONFIG_FILE = System.getProperty("user.dir") + "/application.config";

    public static Object get(String key) {
        Object result;
        if (env.keySet().contains(key)) {
            result = env.get(key);
        } else {
            result = load(key);
        }
        return result;
    }

    private static Object load(String key) {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(CONFIG_FILE)));
            String line;
            while((line = reader.readLine()).trim() != null) {
                Log.d("Config", line);
                if (line.startsWith("#")) {
                    continue;
                }

                if (line.startsWith(key)) {
                    String[] tokens = line.split("=");
                    if(tokens.length == 2) {
                        env.put(tokens[0], tokens[1]);
                        return tokens[1];
                    }
                }
            }
        } catch (IOException ioException) {

        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException ioException2) {

            }
        }
        return null;
    }

    public static String getAsString(String key) {
        return String.valueOf(get(key));
    }

    public static File getAsFile(String key) {
        String filename = (String) get(key);
        if (filename != null) {
            return new File(filename);
        }
        return null;
    }

}
