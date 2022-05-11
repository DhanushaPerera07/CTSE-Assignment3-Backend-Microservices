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

public final class ValidationMessages {
    public static final String INVALID_DATA_INPUT = "Invalid data input";
    public static final String INVALID_DATA_INPUT_CUSTOMIZED = "Invalid data input: {0} is invalid.\n";
    public static final String INVALID_DATA_INPUT_ID_SHOULD_NOT_BE_INCLUDED
            = "Invalid data input: id should not be included or set the id to zero";
    public static final String INVALID_ID
            = "Invalid input detected. {0} id should only be an integer (except zero).";
    public static final String SQL_INTEGRITY_CONSTRAINT_VIOLATION
            = "An integrity constraint (foreign key, primary key or unique key) has been violated.";
    public static final String EMAIL_IS_ALREADY_TAKEN_CUSTOMIZED = "{0} email is already taken.";
    public static final String EMAIL_IS_ALREADY_TAKEN = "Given email is already taken.";

    public static final String ID_IS_REQUIRED
            = "{0} ID is required.";
    public static final String INTEGERS_ARE_ONLY_ACCEPTED_EXCEPT_ZERO
            = "Integers are only accepted (except zero).";

    public static final String RECORD_IS_NOT_FOUND = "{0} record is not found for user input({1}) {2}";
    public static final String TO_RETRIEVE = "to retrieve.";
    public static final String TO_UPDATE = "to update.";
    public static final String TO_DELETE = "to delete.";


    public static final String REQUEST_CONTENT_TYPE_INVALID
            = "Invalid Content Type: Request Content Type is invalid. Request Content Type should be: {0} only.";

    public static final String ID_AND_FOREIGN_KEY_ID_BOTH_NOT_ALLOWED
            = "Invalid input: Both {0} id and {1} id are not allowed. Only one of them is allowed.";

    public static final String DATES_NOT_ACCEPTABLE
            = "Invalid input: No of days, arrival date and departure date not acceptable " +
            "(Departure date - Arrival date difference should be at least a day).\n";

    public static final String RECORD_NOT_FOUND_FOR_THE_ID
            = "Invalid input: No matching {0} record found for the given {0} id.\n";

    public static final String DUPLICATE_RECORD_FOUND = "Duplicate record found for the {0}.\n";
}
