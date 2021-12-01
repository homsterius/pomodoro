package io.github.homsterius.pomodoro.impl;

import io.github.homsterius.pomodoro.api.PomodoroStates;

class StateService {

  private final int numberOfIterations;

  private int currentIteration;

  private PomodoroStates state;

  StateService(int numberOfIterations) {
    this.numberOfIterations = numberOfIterations;
    this.currentIteration = 0;
    this.state = PomodoroStates.POMODORO;
  }

  PomodoroStates getState() {
    return state;
  }

  int getNumberOfAccomplishedPomodoros() {
    return currentIteration;
  }

  PomodoroStates nextState() {
    state = switch (state) {
      case POMODORO ->
        ++currentIteration < numberOfIterations
          ? PomodoroStates.BREAK
          : PomodoroStates.LONG_BREAK;
      case BREAK -> PomodoroStates.POMODORO;
      case LONG_BREAK, END -> PomodoroStates.END;
    };

    return state;
  }

  void setEnd() {
    state = PomodoroStates.END;
  }
}
