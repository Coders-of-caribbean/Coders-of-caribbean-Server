package org.unical.server.command;

import lombok.Getter;

import java.util.Random;

@Getter
public class Command {

    private String command;

    public void generateRandomCommand() {
        String move = "MOVE";
        String wait = "WAIT";
        String slower = "SLOWER";
        String faster = "FASTER";
        Random random = new Random();
        int x = random.nextInt(0, 23);
        int y = random.nextInt(0, 21);
        int speed = random.nextInt(1, 3);
        //int choice = random.nextInt(0, 4);
        int choice = 0;
        switch (choice) {

            case 0:
                //command = move + " " + speed + " " + x + " " + y;
                command = speed + " " + x + " " + y + " ";
                break;

            case 1:
                command = wait;
                break;

            case 2:
                command = slower;
                break;

            case 3:
                command = faster;
                break;

        }
    }



}
