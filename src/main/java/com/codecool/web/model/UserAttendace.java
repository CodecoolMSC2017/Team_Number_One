package com.codecool.web.model;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;

import java.util.HashMap;
import java.util.Map;

public class UserAttendace {
    private Map<LocalDate, Boolean> attendacePerDays = new HashMap<>();
    private LocalDate registrationDate;

    public UserAttendace() {
        registrationDate = new LocalDate();
    }

    public Map<LocalDate, Boolean> getAttendacePerDays() {
        return attendacePerDays;
    }

    public void setAttendacePerDays(LocalDate date, boolean isHere) {
        attendacePerDays.put(date, isHere);
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }




}
