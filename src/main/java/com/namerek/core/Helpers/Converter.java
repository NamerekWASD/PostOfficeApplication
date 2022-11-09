package com.namerek.core.Helpers;

import java.util.ArrayList;
import java.util.List;

public class Converter {
    public static <T> List<T> toList(Iterable<T> iterable) {
        List<T> listObjects = new ArrayList<T>();
        for (T object : iterable)
            listObjects.add(object);
        return listObjects;
    }
}
