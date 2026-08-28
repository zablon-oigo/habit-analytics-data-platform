package org.habit.analytics;

import java.time.LocalDate;
import java.util.UUID;

public record HabitDailyStats(
    UUID habitId,
    LocalDate date,
    long completedCount
) {}
