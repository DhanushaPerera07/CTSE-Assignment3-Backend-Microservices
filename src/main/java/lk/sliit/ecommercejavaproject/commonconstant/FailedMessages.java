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

public final class FailedMessages {
    public static final String FAILED_LOADING_SOMETHING = "Failed to load {0}. ";

    public static final String FAILED_LOADING_PROPERTIES_FILE = "Failed to load properties file: ";
    public static final String FAILED_LOADING_APPLICATION_PROPERTIES_FILE = "Failed to load application properties file.";
    public static final String FAILED_OPENING_CONNECTION = "Error when opening connection.";
    public static final String SOMETHING_WENT_WRONG = "Something went wrong";
    public static final String SOMETHING_WENT_WRONG_LOADING_PROPERTIES_FILE = "Something went wrong when loading the properties file:";
    public static final String SOMETHING_WENT_WRONG_DATABASE_OPERATION = "Something went wrong when performing database operation.";
    public static final String SOMETHING_WENT_WRONG_READING_REQUEST_BODY = "Something went wrong when reading request body.";

    public static final String FAILED_PARSING_TO_INTEGER = "Invalid input as parameter: cannot not be parsed to integer.";
    public static final String FAILED_DELETE = "FAILED: Record is not deleted. {0} id : {1}.";

    public static final String ERROR_WHEN_FINDING_VALUE = "Error when finding {0}. user-input: {1}";

    public static final String CANNOT_GET_TO_BE_CONVERT_TO_AN_INTEGER = "FAILED: Input cannot get to be convert to an integer.";


    public final class Spring {
        public static final String FAILED_LOADING_SPRING_CONTAINER = "Failed to load the spring container.";
    }

}
