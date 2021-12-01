package io.github.homsterius.pomodoro.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import io.github.homsterius.pomodoro.api.PomodoroSet;
import io.github.homsterius.pomodoro.api.PomodoroStates;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;


class ScheduleServiceTest {

  @Test
  void accomplishmentOfAPomodoroSet() {
    PomodoroSet pomodoroSet = new PomodoroSet(25, 5, 30, 2);

    var stateList = new ArrayList<PomodoroStates>();

    MockScheduleService mockScheduleService = new MockScheduleService(
        pomodoroSet,
        stateList::add
    );

    assertEquals(
        0,
        mockScheduleService.getNumberOfAccomplishedPomodoros()
    );

    stateList.add(mockScheduleService.getState());
    mockScheduleService.nextState();
    mockScheduleService.nextState();

    assertEquals(
        1,
        mockScheduleService.getNumberOfAccomplishedPomodoros()
    );

    assertFalse(mockScheduleService.isDone());

    mockScheduleService.nextState();
    mockScheduleService.nextState();

    assertEquals(
        2,
        mockScheduleService.getNumberOfAccomplishedPomodoros()
    );

    assertTrue(mockScheduleService.isDone());

    assertEquals(
        List.of(
            PomodoroStates.POMODORO,
            PomodoroStates.BREAK,
            PomodoroStates.POMODORO,
            PomodoroStates.LONG_BREAK,
            PomodoroStates.END
        ),
        stateList
    );
  }
}
