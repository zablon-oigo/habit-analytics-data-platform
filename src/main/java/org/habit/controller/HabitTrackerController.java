package org.habit.controller;

import java.util.List;
import java.util.UUID;

import org.habit.dto.request.HabitRequest;
import org.habit.dto.request.TrackingRequest;
import org.habit.dto.response.HabitResponse;
import org.habit.dto.response.TrackingResponse;
import org.habit.service.HabitService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
@RestController
@RequestMapping("/api/habits")
@RequiredArgsConstructor
public class HabitTrackerController {

    private final HabitService habitService;

    @GetMapping
    public List<HabitResponse> getAllHabits() {
        log.info("Fetching all habits");
        return habitService.getAllHabits();
    }

    @PostMapping
    public HabitResponse addHabit(
        @RequestBody final HabitRequest habitRequest
    ) {
        log.info("Adding habit");
        return habitService.createHabit(habitRequest);
    }

    @GetMapping("/{id}")
    public HabitResponse getHabitById(
        @PathVariable("id") final UUID id
    ) {
        log.info("Fetching habit with id: {}", id);
        return habitService.getHabitResponseById(id);
    }

    @PatchMapping("/{id}")
    public HabitResponse updateHabit(
        @PathVariable("id") final UUID id,
        @RequestBody final HabitRequest habitRequest
    ) {
        log.info("Updating habit with id: {}", id);
        return habitService.updateHabit(id, habitRequest);
    }

    @DeleteMapping("/{id}")
    public void deleteHabit(
        @PathVariable("id") final UUID id
    ) {
        log.info("Deleting habit with id: {}", id);
        habitService.deleteHabit(id);
    }

    @PostMapping("/{id}/tracking")
    public TrackingResponse addTracking(
        @PathVariable("id") final UUID id,
        @RequestBody final TrackingRequest trackingRequest
    ) {
        return habitService.addTrackingEntry(id, trackingRequest);
    }

    @GetMapping("/{id}/tracking")
    public List<TrackingResponse> getTrackings(
        @PathVariable("id") final UUID id
    ) {
        return habitService.getAllTrackingsForHabit(id);
    }
}
