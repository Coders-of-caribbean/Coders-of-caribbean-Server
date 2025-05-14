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
        int x = random.nextInt(0, 23);
        int y = random.nextInt(0, 21);
        int speed = random.nextInt(1, 3);

        command = speed + " " + x + " " + y;
    }

}
