import java.util.Arrays;

public class SignalProcessor implements Runnable {
    private final Target trackedTarget;

    public SignalProcessor(Target initTarget) {
        this.trackedTarget = initTarget;
    }

    @Override
    public void run() {
        System.out.println("Processing signal from target " + this.trackedTarget.getTargetId() + " on thread" + Thread.currentThread().getName());
        try {
            // Simulate signal processing
            System.out.println("Target " + trackedTarget.getTargetId() + " is at position " + Arrays.toString(trackedTarget.getPosition()) + " moving at " + trackedTarget.getSpeed() + "km/s");
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Completed signal from target " + this.trackedTarget.getTargetId());
    }
}
