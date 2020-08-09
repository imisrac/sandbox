package codingame;

import codingame.pojo.Vector;
import lombok.Getter;

@Getter
class CodersStrikeBack {

    public static final int BOOST = 10000;
    private boolean boostable = true;
    private Vector vector;

    public void move(int nextCheckpointX, int nextCheckpointY, int nextCheckpointDist, int nextCheckpointAngle) {

        int thrust = Math.min(nextCheckpointDist, refineByTurning(nextCheckpointAngle));
        if (boostable && nextCheckpointDist >= BOOST && nextCheckpointAngle == 0) {
            thrust = BOOST;
            boostable = false;
        }

        this.vector = new Vector(nextCheckpointX, nextCheckpointY, thrust);
    }

    private int refineByTurning(int nextCheckpointAngle) {
        int thrust = 100;
        if (nextCheckpointAngle < -90 || nextCheckpointAngle > 90) {
            thrust = 0;
        }
        return thrust;
    }

}
