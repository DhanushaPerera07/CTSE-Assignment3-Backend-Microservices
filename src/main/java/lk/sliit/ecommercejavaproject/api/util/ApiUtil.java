package lk.sliit.ecommercejavaproject.api.util;

import lk.sliit.ecommercejavaproject.exception.IdFormatException;

public class ApiUtil {

    public static Long getLongId(String value) {
        Long id;
        try {
            id = new Long(value);
        } catch (NumberFormatException e) {
            throw new IdFormatException();
        }
        return id;
    }
}
