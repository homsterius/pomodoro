package io.github.homsterius.pomodoro.cliinterface;

import io.github.homsterius.pomodoro.api.PomodoroApp;
import io.github.homsterius.pomodoro.api.PomodoroSet;
import io.github.homsterius.pomodoro.api.PomodoroStates;
import java.util.concurrent.TimeUnit;

public class CliApp {

  public static void main(String[] args) {
    var pomodoroApp = new PomodoroApp(
            new PomodoroSet(25, 5, 45, 4),
            CliApp::ring
    );

    System.out.println(PomodoroStates.POMODORO);
    pomodoroApp.start();
  }

  static void ring(PomodoroStates state) {
    System.out.println(state);
    for (int i = 0; i < 4; ++i) {
      System.out.print("\u0007");
      try {
        TimeUnit.SECONDS.sleep(1);
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
      }
    }
  }
}
