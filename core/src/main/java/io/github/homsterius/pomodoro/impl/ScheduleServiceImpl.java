package io.github.homsterius.pomodoro.impl;

import io.github.homsterius.pomodoro.api.PomodoroSet;
import io.github.homsterius.pomodoro.api.PomodoroStates;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

public class ScheduleServiceImpl extends ScheduleService {

  private final ScheduledExecutorService executor;

  private ScheduledFuture<?> scheduledFuture;

  /**
   * Constructor.
   *
   * @param pomodoroSet       Pomodoro set that should be accomplished.
   * @param nextStateCallback It is being called when state switched to the next one.
   */
  public ScheduleServiceImpl(PomodoroSet pomodoroSet, Consumer<PomodoroStates> nextStateCallback) {
    super(pomodoroSet, nextStateCallback);
    this.executor = Executors.newSingleThreadScheduledExecutor();
  }

  @Override
  public long getCurrentStateRemainingTime(TimeUnit timeUnit) {
    return scheduledFuture.getDelay(timeUnit);
  }

  @Override
  public void start() {
    scheduledFuture = executor.schedule(
        this::nextStepExecutor,
        pomodoroSet.getPomodoroMinutes(),
        TimeUnit.MINUTES
    );
  }

  @Override
  public void stop() {
    if (!scheduledFuture.isDone()) {
      scheduledFuture.cancel(true);
    }
  }

  private int getMinutesDelay() {
    return switch (getState()) {
      case POMODORO -> pomodoroSet.getPomodoroMinutes();
      case BREAK -> pomodoroSet.getBreakMinutes();
      case LONG_BREAK -> pomodoroSet.getLongBreakMinutes();
      case END -> 0;
    };
  }

  private void nextStepExecutor() {
    nextState();

    if (getState() == PomodoroStates.END) {
      executor.shutdown();
      return;
    }

    scheduledFuture = executor.schedule(
        this::nextStepExecutor,
        getMinutesDelay(),
        TimeUnit.MINUTES
    );
  }
}
