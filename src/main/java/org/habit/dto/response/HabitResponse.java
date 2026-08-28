package org.habit.dto.response;

import lombok.Builder;
import org.habit.model.Frequency;

import java.time.LocalDate;
import java.util.UUID;

@Builder(toBuilder = true)
public record HabitResponse(
    UUID id,
    String name,
    String description,
    Frequency frequency,
    LocalDate startDate
) {}