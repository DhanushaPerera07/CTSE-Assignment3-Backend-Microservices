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
package lk.sliit.ecommercejavaproject.commonconstant;

public final class SuccessfulMessages {

    public static final String CREATED_RECORD_SUCCESSFUL = "Created {0} record, generated ID: {1}";
    public static final String SEND_LIST_SUCCESSFULLY = "Send {0} record(s) successfully. No of records: {1}";

    public static final class LogUtil {
        public static final String LOGGER_FILE_FOLDER_CREATED_SUCCESSFULLY = "Logger directory created successfully.";
    }

    public static final class ServletContext {
        public static final String CONTEXT_INITIALIZED_SUCCESSFULLY = "Context initialized successfully...!";
        public static final String LOGGER_INITIALIZED_SUCCESSFULLY = "Logger initialized successfully...!";
    }

    public static final class Spring {
        public static final String APP_INITIALIZER_EXECUTED_SUCCESSFULLY = "AppInitializer executed successfully!";
    }
}
