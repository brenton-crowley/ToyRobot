package au.com.brentoncrowley.toyrobot.states.orientation;

import au.com.brentoncrowley.toyrobot.models.Position;
import au.com.brentoncrowley.toyrobot.models.Robot;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class WestTest
{

    @Test
    public void testMove() throws Exception
    {
        Position position = new Position(new int[]{4, 4}, new West());
        OrientationState state = new West();
        Position movedPosition = state.move(position);
        int startX = position.getCoordinate()[0];
        int movedX = movedPosition.getCoordinate()[0];

        assertTrue("The position should have moved one unit from the " +
                        "original position but it has not. " +
                        "\nStart Position: " + position.toString() +
                        "\nMoved Position: " + movedPosition,
                (movedX == startX - 1)
        );
    }

    @Test
    public void testRotateLeft() throws Exception
    {
        OrientationState state = new West();
        OrientationState rotatedOrientation = state.rotateLeft();

        assertTrue("The position should have rotated one unit to the left " +
                        "but it has not.",
                (rotatedOrientation.getClass() == new South().getClass())
        );
    }

    @Test
    public void testRotateRight() throws Exception
    {
        OrientationState state = new West();
        OrientationState rotatedOrientation = state.rotateRight();

        assertTrue("The position should have rotated one unit to the right " +
                        "but it has not.",
                (rotatedOrientation.getClass() == new North().getClass())
        );
    }
}