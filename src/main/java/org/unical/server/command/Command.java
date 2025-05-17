package org.unical.server.command;

import lombok.Getter;

import java.util.Random;

@Getter
public class Command {

    private String command;

    public void generateRandomCommand() {
        String move = "MOVE";
        String wait = "WAIT";
        Random random = new Random();
        int dir = random.nextInt(6) + 1;
        int speed = random.nextInt(1, 3);
        int choice = random.nextInt(1, 3);

        int x = random.nextInt(10);
        int y = random.nextInt(10);

        if(true) {
            command = speed + " " + x + " " + y;
        } else {
            command = wait;
        }
    }

}
