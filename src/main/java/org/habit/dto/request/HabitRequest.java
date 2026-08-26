package org.habit.dto.request;

import lombok.Builder;
import org.habit.model.Frequency;

import java.time.LocalDate;

@Builder(toBuilder = true)
public record HabitRequest(
    String name,
    String description,
    Frequency frequency,
    LocalDate startDate
) {}
