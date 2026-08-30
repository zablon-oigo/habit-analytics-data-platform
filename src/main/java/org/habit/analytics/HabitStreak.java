package org.habit.analytics;

import java.time.LocalDate;
import java.util.UUID;


public record HabitStreak(
    UUID habitId,
    int currentStreak,
    int longestStreak,
    LocalDate lastCompletedDate
) {}
