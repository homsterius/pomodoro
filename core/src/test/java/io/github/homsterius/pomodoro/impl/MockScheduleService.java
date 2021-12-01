package io.github.homsterius.pomodoro.impl;

import io.github.homsterius.pomodoro.api.PomodoroSet;
import io.github.homsterius.pomodoro.api.PomodoroStates;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;


class MockScheduleService extends ScheduleService {

  /**
   * Constructor.
   *
   * @param pomodoroSet       Pomodoro set that should be accomplished.
   * @param nextStateCallback It is being called when state switched to the next one.
   */
  MockScheduleService(PomodoroSet pomodoroSet, Consumer<PomodoroStates> nextStateCallback) {
    super(pomodoroSet, nextStateCallback);
  }

  @Override
  public long getCurrentStateRemainingTime(TimeUnit timeUnit) {
    return 0;
  }

  @Override
  public boolean isCanceled() {
    return false;
  }

  @Override
  public void start() {
  }

  @Override
  public void stop() {
  }

  public void nextState() {
    super.nextState();
  }
}
