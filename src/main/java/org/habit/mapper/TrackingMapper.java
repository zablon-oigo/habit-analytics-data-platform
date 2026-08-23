package org.habit.mapper;

import org.habit.dto.response.TrackingResponse;
import org.habit.model.Tracking;

public final class TrackingMapper {

    private TrackingMapper() {}

    public static TrackingResponse mapToTrackingResponse(
        final Tracking tracking
    ) {
        return new TrackingResponse(
            tracking.getId(),
            tracking.getHabit().getId(),
            tracking.getTimestamp(),
            tracking.getNote()
        );
    }
}