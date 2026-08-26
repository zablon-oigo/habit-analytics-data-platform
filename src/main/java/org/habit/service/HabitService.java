package org.habit.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.habit.dto.request.HabitRequest;
import org.habit.dto.request.TrackingRequest;
import org.habit.dto.response.HabitResponse;
import org.habit.dto.response.TrackingResponse;
import org.habit.exception.HabitNotFoundException;
import org.habit.mapper.HabitMapper;
import org.habit.mapper.TrackingMapper;
import org.habit.model.Habit;
import org.habit.model.Tracking;
import org.habit.repository.HabitRepository;
import org.habit.repository.TrackingRepository;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class HabitService {

    private final HabitRepository habitRepository;
    private final TrackingRepository trackingRepository;

    public List<HabitResponse> getAllHabits() {
        return habitRepository.findAll()
            .stream()
            .map(HabitMapper::mapToHabitResponse)
            .toList();
    }

    public HabitResponse createHabit(final HabitRequest habitRequest) {
        final Habit habit = Habit.builder()
            .name(habitRequest.name())
            .description(habitRequest.description())
            .frequency(habitRequest.frequency())
            .startDate(habitRequest.startDate())
            .build();

        final Habit savedHabit = habitRepository.save(habit);

        return HabitMapper.mapToHabitResponse(savedHabit);
    }

    public HabitResponse getHabitResponseById(final UUID id) {
        final Habit habit = habitRepository.findById(id)
            .orElseThrow(() ->
                new HabitNotFoundException("Habit not found: " + id)
            );

        return HabitMapper.mapToHabitResponse(habit);
    }

    public HabitResponse updateHabit(
        final UUID id,
        final HabitRequest habitRequest
    ) {
        final Habit habit = habitRepository.findById(id)
            .orElseThrow(() ->
                new HabitNotFoundException("Habit not found: " + id)
            );

        habit.setName(habitRequest.name());
        habit.setDescription(habitRequest.description());
        habit.setFrequency(habitRequest.frequency());
        habit.setStartDate(habitRequest.startDate());

        final Habit updatedHabit = habitRepository.save(habit);

        return HabitMapper.mapToHabitResponse(updatedHabit);
    }

    public void deleteHabit(final UUID id) {
        if (!habitRepository.existsById(id)) {
            throw new HabitNotFoundException("Habit not found: " + id);
        }

        habitRepository.deleteById(id);
    }

    public TrackingResponse addTrackingEntry(
        final UUID habitId,
        final TrackingRequest trackingRequest
    ) {
        final Habit habit = habitRepository.findById(habitId)
            .orElseThrow(() ->
                new HabitNotFoundException("Habit not found: " + habitId)
            );

        final Tracking tracking = Tracking.builder()
            .habit(habit)
            .timestamp(LocalDateTime.now())
            .note(trackingRequest.note())
            .build();

        final Tracking savedTracking = trackingRepository.save(tracking);

        return TrackingMapper.mapToTrackingResponse(savedTracking);
    }

    public List<TrackingResponse> getAllTrackingsForHabit(
        final UUID habitId
    ) {
        if (!habitRepository.existsById(habitId)) {
            throw new HabitNotFoundException(
                "Habit not found: " + habitId
            );
        }

        return trackingRepository.findByHabitId(habitId)
            .stream()
            .map(TrackingMapper::mapToTrackingResponse)
            .toList();
    }
}