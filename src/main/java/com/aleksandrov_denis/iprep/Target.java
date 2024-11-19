package com.aleksandrov_denis.iprep;

import java.util.Random;

public class Target extends Thread {
    private final int id;
    private double[] position;
    private final double speed;

    public int getTargetId() {
        return id;
    }

    public double getSpeed() {
        return speed;
    }

    public double[] getPosition() {
        return position;
    }

    public Target(int initId, double[] initPosition, double initSpeed) {
        this.id = initId;
        this.position = initPosition;
        this.speed = initSpeed;
        start();
    }

    @Override
    public void run() {
        Random random = new Random();
        double x, y;
        try {
            while (!interrupted()) {
                x = random.nextDouble(-1, 1);
                y = random.nextDouble(-1, 1);
                position[0] += x * speed;
                position[1] += y * speed;
                position[0] = Math.min(Math.max(position[0], -5), 5);
                position[1] = Math.min(Math.max(position[1], -5), 5);
                Thread.sleep(500);
            }
        } catch (InterruptedException e){
            position = null;
        }
    }

}
