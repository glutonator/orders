package com.orders;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Embeddable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Embeddable
public class CustomDate {

    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    private LocalDate Date;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalTime  ExactTime;

    public CustomDate() {
    }

    public CustomDate(LocalDate date, LocalTime exactTime) {
        Date = date;
        ExactTime = exactTime;
    }

    public LocalDate getDate() {
        return Date;
    }

    public void setDate(LocalDate date) {
        Date = date;
    }

    public LocalTime getExactTime() {
        return ExactTime;
    }

    public void setExactTime(LocalTime exactTime) {
        ExactTime = exactTime;
    }

    @Override
    public String toString() {
        return "CustomDate{" +
                "Date=" + Date +
                ", ExactTime=" + ExactTime +
                '}';
    }
}
