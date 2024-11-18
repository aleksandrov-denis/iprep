package com.aleksandrov_denis.iprep;

import org.springframework.web.bind.annotation.*;

import java.awt.image.RasterFormatException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/radar")
public class RadarController {
    private final Radar radar = new Radar();

    @PostMapping("/addTarget")
    public void addTarget(@RequestBody Target target) {
        radar.addTarget(target);
    }

    @PostMapping("/generateTarget")
    public void generateTarget() {
        Random r = new Random();
        Target target = new Target(r.nextInt(1000), new double[]{r.nextInt(-1, 1), r.nextInt(-1, 1)}, r.nextDouble());
        radar.addTarget(target);
    }

    @DeleteMapping("removeTarget/{id}")
    public void removeTarget(@PathVariable int withId) {
        radar.removeTarget(withId);
    }

    @GetMapping("/getTargets")
    public List<Target> getTargets() {
        return new ArrayList<>(radar.getCollection().values());
    }
}
