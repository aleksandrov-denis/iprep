package com.aleksandrov_denis.iprep;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Radar {
    private HashMap<Integer, Target> collection;
    private ExecutorService executor;

    public HashMap<Integer, Target> getCollection() {
        return collection;
    }

    public void addTarget(Target newTarget) {
        try {
            if (collection.containsKey(newTarget.getTargetId())) {
                System.out.println("Replacing existing key-value pair");
            }
            collection.put(newTarget.getTargetId(), newTarget);
        } catch (ClassCastException e) {
            System.out.println("Invalid Target provided" + e.getMessage());
        }
    }

    public void removeTarget(int withId) {
        Target target = collection.remove(withId);
        target.interrupt();
    }

    public void removeAllTargets() {
        for (HashMap.Entry<Integer, Target> entry : collection.entrySet()) {
            Target target = entry.getValue();
            target.interrupt();
        }
        collection.clear();
    }

    public void displayTargetsGT(double minSpeed) {
        List<Target> filtered = collection.values().stream()
                .filter(target -> target.getSpeed() > minSpeed)
                .toList();
        executor = Executors.newFixedThreadPool(3);
        System.out.println("-----------------Displaying-Current-Targets----------------");
        for (Target target : filtered) {
            executor.submit(new SignalProcessor(target));
        }
    }

    public void stopDisplay() {
        // graceful shutdown of executor
        try {
            if (!executor.awaitTermination(2, TimeUnit.SECONDS)) {
                executor.shutdown();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public Radar() {
        this.collection = new HashMap<Integer, Target>();
    }
}