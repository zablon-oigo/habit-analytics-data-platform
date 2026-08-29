package org.habit.analytics;

import java.time.LocalDateTime;
import java.util.UUID;

public record TrackingCreated(
    UUID trackingId,
    UUID habitId,
    LocalDateTime timestamp,
    String note
) {}
