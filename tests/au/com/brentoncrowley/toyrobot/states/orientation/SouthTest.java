package au.com.brentoncrowley.toyrobot.states.orientation;

import au.com.brentoncrowley.toyrobot.models.Position;
import org.junit.Test;

import static org.junit.Assert.*;

public class SouthTest
{
    @Test
    public void testMove() throws Exception
    {
        Position position = new Position(new int[]{2, 2}, new South());
        OrientationState state = new South();
        Position movedPosition = state.move(position);
        int startY = position.getCoordinate()[1];
        int movedY = movedPosition.getCoordinate()[1];

        assertTrue("The position should have moved one unit from the " +
                        "original position but it has not. " +
                        "\nStart Position: " + position.toString() +
                        "\nMoved Position: " + movedPosition,
                (movedY == startY - 1)
        );
    }

    @Test
    public void testRotateLeft() throws Exception
    {
        OrientationState state = new South();
        OrientationState rotatedOrientation = state.rotateLeft();

        assertTrue("The position should have rotated one unit to the left " +
                        "but it has not.",
                (rotatedOrientation.getClass() == new East().getClass())
        );
    }

    @Test
    public void testRotateRight() throws Exception
    {
        OrientationState state = new South();
        OrientationState rotatedOrientation = state.rotateRight();

        assertTrue("The position should have rotated one unit to the right " +
                        "but it has not.",
                (rotatedOrientation.getClass() == new West().getClass())
        );
    }
}