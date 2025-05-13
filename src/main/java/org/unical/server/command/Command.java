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
        int dir = random.nextInt(6);
        int speed = random.nextInt(3);
        int choice = random.nextInt(2);

        if(true) {
            command = move +  " " + dir + " " + speed;
        } else {
            command = wait;
        }
    }

}
