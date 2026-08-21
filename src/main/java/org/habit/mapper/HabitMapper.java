package org.habit.mapper;

import org.habit.dto.response.HabitResponse;
import org.habit.model.Habit;

public final class HabitMapper {

    private HabitMapper() {}

    public static HabitResponse mapToHabitResponse(final Habit habit) {
        return HabitResponse.builder()
            .id(habit.getId())
            .name(habit.getName())
            .description(habit.getDescription())
            .frequency(habit.getFrequency())
            .startDate(habit.getStartDate())
            .build();
    }

}