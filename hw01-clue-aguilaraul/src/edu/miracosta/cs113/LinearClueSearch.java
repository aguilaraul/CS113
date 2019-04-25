package edu.miracosta.cs113;

import model.AssistantJack;
import model.Theory;

import java.util.Scanner;

public class LinearClueSearch {

    public static void main(String[] args) {
        // DECLARATION + INITIALIZATION
        int answerSet, solution, murder, weapon, location;
        Theory answer;
        AssistantJack jack;
        Scanner keyboard = new Scanner(System.in);

        // INPUT
        System.out.print("Which theory would like you like to test? (1, 2, 3[random]): ");
        answerSet = keyboard.nextInt();
        keyboard.close();

        // PROCESSING
        // set variables to 1
        // check solution
        // if not correct, increment wrong variable
        // repeat until solution is found or run forever
        jack = new AssistantJack(answerSet);

        weapon = 1;
        location = 1;
        murder = 1;

        do {
            solution = jack.checkAnswer(weapon, location, murder);
            if(solution == 1) {
                weapon++;
            }
            if(solution == 2) {
                location++;
            }
            if(solution == 3) {
                murder++;
            }
        } while(solution != 0);

        answer = new Theory(weapon, location, murder);

        // OUTPUT
        System.out.println("Total Checks = " + jack.getTimesAsked() + ", Solution " + answer);

        if (jack.getTimesAsked() > 20) {
            System.out.println("FAILED!! You're a horrible Detective...");
        } else {
            System.out.println("WOW! You might as well be called Batman!");
        }

    }
}
