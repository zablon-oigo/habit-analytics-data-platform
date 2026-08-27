package org.habit.dto.response;

import java.time.LocalDateTime;
import java.util.UUID;

public record TrackingResponse(
    UUID id,
    UUID habitId,
    LocalDateTime timestamp,
    String note
) {}