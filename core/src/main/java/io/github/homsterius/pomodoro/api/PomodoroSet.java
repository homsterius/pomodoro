package io.github.homsterius.pomodoro.api;

public class PomodoroSet {

  private final int pomodoroMinutes;

  private final int breakMinutes;

  private final int amountOfPomodoros;

  private final int longBreakMinutes;

  /**
   * Constructor.
   *
   * @param pomodoroMinutes Duration of a pomodoro
   * @param breakMinutes Duration of a break
   * @param longBreakMinutes Duration of a long break
   * @param amountOfPomodoros Amount of pomodoros that should be accomplished
   */
  public PomodoroSet(
      int pomodoroMinutes,
      int breakMinutes,
      int longBreakMinutes,
      int amountOfPomodoros
  ) {
    this.pomodoroMinutes = pomodoroMinutes;
    this.breakMinutes = breakMinutes;
    this.longBreakMinutes = longBreakMinutes;
    this.amountOfPomodoros = amountOfPomodoros;
  }

  public int getPomodoroMinutes() {
    return pomodoroMinutes;
  }

  public int getBreakMinutes() {
    return breakMinutes;
  }

  public int getLongBreakMinutes() {
    return longBreakMinutes;
  }

  public int getAmountOfPomodoros() {
    return amountOfPomodoros;
  }
}
