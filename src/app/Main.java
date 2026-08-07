package app;

import ui.Menu;
import service.WorkoutTracker;

public class Main {
    public static void main(String[] args){
        WorkoutTracker tracker = new WorkoutTracker();
        Menu menu = new Menu(tracker);

        menu.start();

    }
}
