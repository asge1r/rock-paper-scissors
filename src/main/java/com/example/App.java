package com.example;

import java.util.Random;
import java.util.Scanner;

public class App {
    private static final Scanner scanner = new Scanner(System.in);
    private static final Random random = new Random();

    public static void main(String[] args) {
        while (true) {
            playRound();
            System.out.println("Do you want to continue? (yes/no)");
            String choice = scanner.next();
            if (!choice.equalsIgnoreCase("yes")) {
                break;
            }
        }
    }

    private static void playRound() {
        System.out.println("Enter your choice (stone/scissors/paper): ");
        Choice playerChoice = getPlayerChoice();
        Choice computerChoice = getRandomChoice();

        System.out.println("You chose: " + playerChoice);
        System.out.println("Computer chose: " + computerChoice);

        GameResult result = evaluateRound(playerChoice, computerChoice);
        System.out.println("Result: " + result);
    }

    private static Choice getPlayerChoice() {
        while (true) {
            String choice = scanner.next().toUpperCase();
            try {
                return Choice.valueOf(choice);
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid choice. Please enter stone, scissors, or paper.");
            }
        }
    }

    private static Choice getRandomChoice() {
        Choice[] choices = Choice.values();
        return choices[random.nextInt(choices.length)];
    }

    private static GameResult evaluateRound(Choice playerChoice, Choice computerChoice) {
        if (playerChoice == computerChoice) {
            return GameResult.TIE;
        } else if ((playerChoice == Choice.STONE && computerChoice == Choice.SCISSORS) ||
                (playerChoice == Choice.SCISSORS && computerChoice == Choice.PAPER) ||
                (playerChoice == Choice.PAPER && computerChoice == Choice.STONE)) {
            return GameResult.PLAYER_WON;
        } else {
            return GameResult.COMPUTER_WON;
        }
    }
}
