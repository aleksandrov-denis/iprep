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
        try {
            while (!interrupted()) {
                position[random.nextInt(2)] += speed;
                Thread.sleep(500);
            }
        } catch (InterruptedException e){
            position = null;
        }
    }

}
