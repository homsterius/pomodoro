package io.github.homsterius.pomodoro.api;

import io.github.homsterius.pomodoro.impl.ScheduleServiceImpl;
import java.util.function.Consumer;

public class PomodoroApp extends ScheduleServiceImpl {

  /**
   * Constructor.
   *
   * @param pomodoroSet       Pomodoro set that should be accomplished.
   * @param nextStateCallback It is being called when state switched to the next one.
   */
  public PomodoroApp(PomodoroSet pomodoroSet, Consumer<PomodoroStates> nextStateCallback) {
    super(pomodoroSet, nextStateCallback);
  }
}
