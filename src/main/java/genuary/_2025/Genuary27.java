package genuary._2025;

import processing.core.PApplet;
import processing.core.PVector;

import java.util.ArrayList;
import java.util.List;

import static genuary._2025.parameters.Parameters.*;
import static genuary._2025.save.SaveUtil.saveSketch;

public class Genuary27 extends PApplet {
    public static void main(String[] args) {
        PApplet.main(Genuary27.class);
    }

    @Override
    public void settings() {
        size(WIDTH, HEIGHT);
        randomSeed(SEED);
        noiseSeed(floor(random(MAX_INT)));
    }

    @Override
    public void setup() {
        background(BACKGROUND_COLOR.red(), BACKGROUND_COLOR.green(), BACKGROUND_COLOR.blue());
        stroke(STROKE_COLOR.red(), STROKE_COLOR.green(), STROKE_COLOR.blue(), STROKE_COLOR.alpha());
        noFill();
        noLoop();
    }

    @Override
    public void draw() {
        List<Triangle> triangles = new ArrayList<>();
        triangles.add(new Triangle(List.of(new PVector(MARGIN, MARGIN),
        new PVector(WIDTH - MARGIN, MARGIN),
        new PVector(WIDTH - MARGIN, HEIGHT - MARGIN)), 0));

        triangles.add(new Triangle(List.of(new PVector(MARGIN, HEIGHT - MARGIN),
                new PVector(WIDTH - MARGIN, HEIGHT - MARGIN),
                new PVector(MARGIN, MARGIN)), 0));

        do {
            triangles.addAll(triangles.get(0).subdivide(SUBDIVISION_WEIGHT));
            triangles.remove(0);
        } while (triangles.get(0).getDepth() < MAX_DEPTH);

        triangles.forEach(t -> t.render(this));
        saveSketch(this);
    }
}