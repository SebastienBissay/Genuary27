package genuary._2025;

import processing.core.PApplet;
import processing.core.PVector;

import java.util.ArrayList;
import java.util.List;

import static processing.core.PConstants.CLOSE;

public class Triangle {
    private final List<PVector> summits;
    private final int depth;

    public Triangle(List<PVector> summits, int depth) {
        this.summits = summits;
        this.depth = depth;
    }

    public void render(PApplet pApplet) {
        pApplet.beginShape();
        summits.forEach(s -> pApplet.vertex(s.x, s.y));
        pApplet.endShape(CLOSE);
    }

    List<Triangle> subdivide(float weight) {
        List<Triangle> subTriangles = new ArrayList<>();
        List<PVector> subSummits = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            subSummits.add(PVector.lerp(summits.get(i), summits.get((i + 1) % 3), weight));
        }
        for (int i = 0; i < 3; i++) {
            subTriangles.add(new Triangle(
                    List.of(
                            summits.get(i),
                            subSummits.get((i + 1) % 3),
                            subSummits.get((i + 2) % 3)
                    ),
                    depth + 1)
            );
        }
        subTriangles.add(new Triangle(subSummits, depth + 1));
        return subTriangles;
    }

    public int getDepth() {
        return depth;
    }
}
