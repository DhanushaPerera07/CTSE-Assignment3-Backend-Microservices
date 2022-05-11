/*
 * MIT License
 *
 * Copyright (c) 2022 Dhanusha Perera
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NON INFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package lk.sliit.ecommercejavaproject.config;

import lk.sliit.ecommercejavaproject.commonconstant.SuccessfulMessages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Configuration;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Properties;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import static lk.sliit.ecommercejavaproject.commonconstant.Commons.LOGGER_FILE_DIRECTORY;
import static lk.sliit.ecommercejavaproject.commonconstant.Commons.LOGGER_FILE_NAME;

//@Configuration
public class LogConfig implements ApplicationRunner {

    private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(LogConfig.class);

    @Autowired
    Properties prop;

    public void createFileIfNotExists(String path) {
        File logFile = new File(path);

        if (!logFile.exists()) {
            String logFileFolder = Paths.get(System.getProperty("user.dir"), // System.getProperty("catalina.home")
                    LOGGER_FILE_DIRECTORY).toString();
            File fileLogFileDir = new File(logFileFolder);

            if (fileLogFileDir.mkdir()) {
                logger.info(SuccessfulMessages.LogUtil.LOGGER_FILE_FOLDER_CREATED_SUCCESSFULLY);
            }
            try {
                createAFile(logFile);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }

    private static boolean createAFile(File file) throws IOException {
        return file.createNewFile();
    }

    private void initLogging() {
//        Properties prop = new Properties();
        System.out.println("************* System Properties ****************");
        for (Object key : prop.keySet()) {
            System.out.println(key + ": " + prop.getProperty(key.toString()));
        }

        try {
            String logFilePath;
            if (prop.getProperty("app.log_dir") != null) {
                logFilePath = Paths.get(prop.getProperty("app.log_dir"),
                        LOGGER_FILE_NAME).toString();
                // logFilePath = prop.getProperty("app.log_dir") + "/back-end.log";
            } else {
                logFilePath = Paths.get(System.getProperty("user.dir"), // System.getProperty("catalina.home")
                        LOGGER_FILE_DIRECTORY,
                        LOGGER_FILE_NAME).toString();
//                logFilePath = Paths.get(System.getProperty("catalina.home"), // System.getProperty("catalina.home")
//                        LOGGER_FILE_DIRECTORY,
//                        LOGGER_FILE_NAME).toString();

                // logFilePath = System.getProperty("catalina.home") + "/logs/back-end.log";
            }

            createFileIfNotExists(logFilePath);

            FileHandler fileHandler = new FileHandler(logFilePath, true);
            fileHandler.setFormatter(new SimpleFormatter());
            fileHandler.setLevel(Level.INFO);
            Logger.getLogger("").addHandler(fileHandler);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    @Override
    public void run(ApplicationArguments args) throws Exception {
        initLogging();
    }
}
