package com.exampel.calculateWorkingHours;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;

public class CalculateWorkingHoursApplication {

    public static void main(String[] args) {

        long totalMinutes = 0, hours = 0, minutes = 0, minutesTillClosing = 0, minutesFromOpening = 0;
        int startingHour, startingMinute, finishingHour, finishingMinute;
        Scanner input = new Scanner(System.in);

        System.out.println("*** This is a calculator which helps you calculate your working hours ***");
        System.out.println("What hour did you start?");
        startingHour = input.nextInt();
        System.out.println("And what minute?");
        startingMinute = input.nextInt();
        System.out.println("What hour did you finish?");
        finishingHour = input.nextInt();
        System.out.println("And what minute?");
        finishingMinute = input.nextInt();

        LocalTime startingTime = LocalTime.of(startingHour, startingMinute);
        LocalTime finishingTime = LocalTime.of(finishingHour, finishingMinute);

        // Office opening and closing hours
        LocalTime officeOpen = LocalTime.of(8, 00);
        LocalTime officeClose = LocalTime.of(17, 00);

        if (startingTime.getHour() < officeOpen.getHour() || startingTime.getHour() > officeClose.getHour()) {
            startingTime = LocalTime.of(8, 00);
        }
        if (finishingTime.getHour() > officeClose.getHour()) {
            finishingTime = LocalTime.of(17, 00);
        }

        // Calculating the difference in Minutes
        totalMinutes = ChronoUnit.MINUTES.between(startingTime, finishingTime);
        hours = (totalMinutes / 60);
        minutes = ChronoUnit.MINUTES.between(startingTime, finishingTime) % 60;

        if (startingTime.getHour() > finishingTime.getHour()) {

            // Calculating the difference in Minutes
            minutesTillClosing = ChronoUnit.MINUTES.between(startingTime, officeClose);
            minutesFromOpening = ChronoUnit.MINUTES.between(officeOpen, finishingTime);
            totalMinutes = (minutesTillClosing + minutesFromOpening);
            hours = (totalMinutes / 60);
            minutes = totalMinutes % 60;

        }

        System.out.println("You started working at: " + startingTime + " and you finished working at " + finishingTime
                + ". \nYou have been working for " + hours + " hours and " + minutes + " minutes. " +
                "\nYou have been working in total for " + totalMinutes + " minutes.");
    }
}
