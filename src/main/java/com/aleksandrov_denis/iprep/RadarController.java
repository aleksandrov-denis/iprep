package com.aleksandrov_denis.iprep;

import org.springframework.web.bind.annotation.*;

import java.awt.image.RasterFormatException;
import java.util.HashMap;

@RestController
@RequestMapping("/radar")
public class RadarController {
    private final Radar radar = new Radar();

    @PostMapping("/addTarget")
    public void addTarget(@RequestBody Target target) {
        radar.addTarget(target);
    }

    @DeleteMapping("removeTarget/{id}")
    public void removeTarget(@PathVariable int withId) {
        radar.removeTarget(withId);
    }

    @GetMapping("/getTargets")
    public HashMap<Integer, Target> getTargets() {
        return radar.getCollection();
    }
}
