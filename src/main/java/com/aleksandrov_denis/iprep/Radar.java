package com.aleksandrov_denis.iprep;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Radar {
    private HashMap<Integer, Target> collection;

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

    public List<String> displayTargetsGT(double minSpeed) {
        CopyOnWriteArrayList<String> logs = new CopyOnWriteArrayList<>();
        List<Target> filtered = collection.values().stream()
                .filter(target -> target.getSpeed() > minSpeed)
                .toList();
        logs.add("-------------Displaying-Current-Targets-Moving-Faster-Than-" + minSpeed + "-m/s-------------");
        for (Target target : filtered) {
            logs.add("Target " + target.getTargetId() + " is at position [" + String.format("%.2f", target.getPosition()[0]) + ", " + String.format("%.2f", target.getPosition()[1]) + "] moving at " + String.format("%.2f", target.getSpeed()) + "m/s");
        }
        return new ArrayList<>(logs);
    }

    public Radar() {
        this.collection = new HashMap<Integer, Target>();
    }
}