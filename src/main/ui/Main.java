package ui;

import model.Event;
import model.EventLog;
import model.Event;

import java.util.Iterator;

public class Main {
    public static void main(String[] args) {
        Menu menu = new Menu();
        menu.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                for (Event event : EventLog.getInstance()) {
                    System.out.println(event.toString());
                }
                System.out.println("GUI has been closed");
                System.exit(0);
            }
        });
    }
}
