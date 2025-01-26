package com.combatsasality.crm.ui;

import java.util.LinkedHashSet;

public class History {
    private Mode currentMode = Mode.AUTHORIZATION;
    private LinkedHashSet<Mode> previousMode = new LinkedHashSet<>();


    public Mode getCurrentMode() {
        return currentMode;
    }

    public void changeMode(Mode mode) {
        previousMode.add(currentMode);
        currentMode = mode;
        System.out.println(currentMode);
    }

//    public Mode back() {
//        this.currentMode = previousMode.removeLast();
//        this.terminal.resume();
//        return this.currentMode;
//    }


    public enum Mode {
        AUTHORIZATION,
        SIGN_IN,
        SIGN_UP,
        MAIN_MENU,
    }
}
