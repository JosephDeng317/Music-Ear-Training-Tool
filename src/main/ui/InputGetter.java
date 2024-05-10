package ui;

import java.util.Arrays;
import java.util.Scanner;
import java.util.ArrayList;

// This class helps take user inputs by only returning the input once the user has typed a valid option

public class InputGetter {
    private Scanner scanner;
    private ArrayList<String> options;

    // MODIFIES: this
    // EFFECTS: creates an InputGetter with allowed inputs specified by options
    public InputGetter(String[] options) {
        scanner = new Scanner(System.in);
        this.options = new ArrayList<String>(Arrays.asList(options));
        this.options.replaceAll(String::toUpperCase);
    }

    // MODIFIES: this
    // EFFECTS: creates an InputGetter with allowed inputs specified by options
    public InputGetter(ArrayList<String> options) {
        scanner = new Scanner(System.in);
        this.options = new ArrayList<String>(options);
    }

    // REQUIRES: options is not empty
    // EFFECTS: Asks the user to choose one of the valid options and outputs it
    public String chooseOption() {
        while (true) {
            String input = scanner.nextLine();
            if (options.contains(input.toUpperCase())) {
                return input;
            }
            System.out.println("Please choose a valid input!");
        }
    }

    // EFFECTS: adds option to list of options
    public void addOption(String option) {
        options.add(option);
    }
}
