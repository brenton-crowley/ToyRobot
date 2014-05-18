package au.com.brentoncrowley.toyrobot.models;

import au.com.brentoncrowley.toyrobot.states.orientation.East;
import au.com.brentoncrowley.toyrobot.states.orientation.North;
import au.com.brentoncrowley.toyrobot.states.orientation.OrientationState;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class PositionTest
{

    @Test
    public void testIsPositionValid() throws Exception
    {
        int[] coordinate = {0,0};
        Position position = new Position(coordinate, new North());

        assertTrue("Position: " + position.toString() + " is INVALID, but " +
                "it should be valid", position.isValid());

    }

    @Test
    public void testIsCoordinate00Valid() throws Exception
    {
        int[] coordinate = {0,0};
        OrientationState orientation = new North();

        Position position = new Position(coordinate, orientation);

        assertTrue("Coordinate: " + Arrays.toString(position.getCoordinate()) +
                   " is INVALID, but it should be valid",
                position.isValidCoordinate()
        );

    }

    @Test
    public void testCoordinate0IsInvalid() throws Exception
    {
        int[] coordinate = {0};
        OrientationState orientation = new North();

        Position position = new Position(coordinate, orientation);

        assertFalse("Coordinate: " + Arrays.toString(position.getCoordinate()) +
                        " is VALID, but it should not be valid",
                position.isValidCoordinate()
        );

    }

    @Test
    public void testCoordinateNotNull() throws Exception
    {
        int[] coordinate = {0, 0};
        OrientationState orientation = new North();

        Position position = new Position(coordinate, orientation);

        assertNotNull("Coordinate: " + Arrays.toString(position.getCoordinate()) +
                        " is null, but it should be valid",
                position.getCoordinate()
        );
    }

    @Test
    public void testOrientationNotNull() throws Exception
    {
        int[] coordinate = {0, 0};
        OrientationState orientation = new North();

        Position position = new Position(coordinate, orientation);

        assertNotNull("Orientation: " + position.getOrientation() +
                        " is null, but it should be valid",
                position.getOrientation()
        );
    }

    @Test
    public void testGetXPosition() throws Exception
    {
        int x = 0;
        int y = 0;
        int[] coordinate = {x,y};
        Position position = new Position(coordinate, new North());

        assertTrue("getX(): " + position.getX() + " is not " + x,
                (x == position.getX())
        );
    }

    @Test
    public void testGetYPosition() throws Exception
    {
        int x = 1;
        int y = 1;
        int[] coordinate = {x,y};
        Position position = new Position(coordinate, new North());

        assertTrue("getY(): " + position.getY() + " is not " + y,
                (y == position.getY())
        );
    }

    @Test
    public void testPositionEqual() throws Exception
    {
        int x = 1;
        int y = 1;
        int[] coordinate = {x,y};
        Position positionOne = new Position(coordinate, new North());
        Position positionTwo = new Position(coordinate, new North());

        assertTrue("PositionOne " + positionOne.toString() + " does not equal " +
                        "positionTwo" + positionTwo.toString() + ", but they " +
                        "should be equal to each other.",
                (positionOne.equals(positionTwo))
        );
    }

    @Test
    public void testPositionNotEqualCoordinates() throws Exception
    {
        int x = 1;
        int y = 1;
        int[] coordinate = {x,y};
        Position positionOne = new Position(coordinate, new North());
        coordinate = new int[] {0, 1};
        Position positionTwo = new Position(coordinate, new North());

        assertFalse("PositionOne " + positionOne.toString() + " equals " +
                        "positionTwo " + positionTwo.toString() + ", but they " +
                        "should NOT be equal to each other.",
                (positionOne.equals(positionTwo))
        );
    }

    @Test
    public void testPositionNotEqualOrientation() throws Exception
    {
        int x = 1;
        int y = 1;
        int[] coordinate = {x,y};
        Position positionOne = new Position(coordinate, new North());
        Position positionTwo = new Position(coordinate, new East());

        assertFalse("PositionOne " + positionOne.toString() + " equals " +
                        "positionTwo " + positionTwo.toString() + ", but they " +
                        "should NOT be equal to each other.",
                (positionOne.equals(positionTwo))
        );
    }

}