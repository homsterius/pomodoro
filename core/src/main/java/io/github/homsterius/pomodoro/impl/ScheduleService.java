package io.github.homsterius.pomodoro.impl;

import io.github.homsterius.pomodoro.api.PomodoroSet;
import io.github.homsterius.pomodoro.api.PomodoroStates;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

abstract class ScheduleService {

  protected final PomodoroSet pomodoroSet;

  private final Consumer<PomodoroStates> nextStateCallback;

  private boolean isCancelled = false;

  private final StateService stateService;

  /**
   * Constructor.
   *
   * @param pomodoroSet Pomodoro set that should be accomplished.
   * @param nextStateCallback It is being called when state switched to the next one.
   *                          Can be used, for example, for notifications.
   */
  public ScheduleService(PomodoroSet pomodoroSet, Consumer<PomodoroStates> nextStateCallback) {
    this.pomodoroSet = pomodoroSet;
    this.nextStateCallback = nextStateCallback;
    this.stateService = new StateService(pomodoroSet.getAmountOfPomodoros());
  }

  public abstract long getCurrentStateRemainingTime(TimeUnit timeUnit);

  public abstract void start();

  public boolean isCanceled() {
    return isCancelled;
  }

  public void stop() {
    isCancelled = true;
    stateService.setEnd();
  }

  public boolean isDone() {
    return stateService.getState() == PomodoroStates.END;
  }

  public PomodoroStates getState() {
    return stateService.getState();
  }

  public int getNumberOfAccomplishedPomodoros() {
    return stateService.getNumberOfAccomplishedPomodoros();
  }

  /**
   * Should be called to switch to the next state.
   */
  protected void nextState() {
    nextStateCallback.accept(
        stateService.nextState()
    );
  }
}
