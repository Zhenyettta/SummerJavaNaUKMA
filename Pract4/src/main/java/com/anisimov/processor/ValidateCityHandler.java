package com.anisimov.processor;

import com.anisimov.annotation.ValidateCity;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Set;

public class ValidateCityHandler implements InvocationHandler {
    private final Object target;
    private static final Set<String> VALID_CITIES = Set.of("Kyiv", "Odessa", "Irpin", "Lviv", "Kharkiv");

    public ValidateCityHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Parameter[] parameters = method.getParameters();
        for (int i = 0; i < parameters.length; i++) {
            if (parameters[i].isAnnotationPresent(ValidateCity.class)) {
                String city = (String) args[i];
                if (!VALID_CITIES.contains(city)) {
                    throw new IllegalArgumentException("Invalid city: " + city);
                }
            }
        }
        return method.invoke(target, args);
    }
}
