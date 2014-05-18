package au.com.brentoncrowley.toyrobot.models;

import au.com.brentoncrowley.toyrobot.states.orientation.East;
import au.com.brentoncrowley.toyrobot.states.orientation.North;
import au.com.brentoncrowley.toyrobot.states.orientation.South;
import au.com.brentoncrowley.toyrobot.states.orientation.West;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class RobotTest
{

    private Robot robot;

    @Before
    public void setUp() throws Exception
    {
        robot = new Robot();
    }

    @After
    public void tearDown() throws Exception
    {
        robot = null;
    }

    @Test
    public void testPlacePositionXIsOutsideLowerBoundary() throws Exception
    {
        robot.place(new Position(new int[]{-1, 0}, new North()));

        // robot position should be null
        assertFalse("The placement should not occur since the position " +
                        "does not fall within the grid bounds, thus " +
                        "the robot should not be placed. " +
                        "\nPosition: " + robot.getPosition(),
                robot.isPlaced()
        );
    }

    @Test
    public void testPlacePositionYIsOutsideLowerBoundary() throws Exception
    {
        robot.place(new Position(new int[]{0, -1}, new North()));

        // robot position should be null
        assertFalse("The placement should not occur since the position " +
                        "does not fall within the grid bounds, thus " +
                        "the robot should not be placed. " +
                        "\nPosition: " + robot.getPosition(),
                robot.isPlaced()
        );
    }

    @Test
    public void testPlacePositionXIsOutsideUpperBoundary() throws Exception
    {
        robot.place(new Position(new int[]{5, 0}, new North()));

        // robot position should be null
        assertFalse("The placement should not occur since the position " +
                        "does not fall within the grid bounds, thus " +
                        "the robot should not be placed. " +
                        "\nPosition: " + robot.getPosition(),
                robot.isPlaced()
        );
    }

    @Test
    public void testPlacePositionYIsOutsideUpperBoundary() throws Exception
    {
        robot.place(new Position(new int[]{0, 5}, new North()));

        // robot position should be null
        assertFalse("The placement should not occur since the position " +
                        "does not fall within the grid bounds, thus " +
                        "the robot should not be placed. " +
                        "\nPosition: " + robot.getPosition(),
                robot.isPlaced()
        );
    }

    @Test
    public void testPlacePositionOrientationIsInvalid() throws Exception
    {
        robot.place(new Position(new int[]{0, 0}, null));

        // robot position should be null
        assertFalse("The placement should not occur since the position " +
                        "does not fall within the grid bounds, thus " +
                        "the robot should not be placed. " +
                        "\nPosition: " + robot.getPosition(),
                robot.isPlaced()
        );
    }

    @Test
    public void testPlacePositionIsValid() throws Exception
    {
        robot.place(new Position(new int[]{0, 0}, new North()));

        // robot position should be null
        assertTrue("The placement should occur since the position " +
                        "falls within the grid bounds, thus " +
                        "the robot should be placed. " +
                        "\nPosition: " + robot.getPosition(),
                robot.isPlaced()
        );
    }

    @Test
    public void testMoveIsInvalidWhenNoPlacement() throws Exception
    {
        robot.move();

        assertFalse("The supplied move() should fail since the " +
                        "robot is yet to be placed. ",
                robot.isPlaced()
        );
    }

    @Test
    public void testRightIsInvalidWhenNoPlacement() throws Exception
    {
        robot.right();

        assertFalse("right() should fail since the " +
                        "robot is yet to be placed. ",
                robot.isPlaced()
        );
    }

    @Test
    public void testLeftIsInvalidWhenNoPlacement() throws Exception
    {
        robot.left();

        assertFalse("left() should fail since the " +
                        "robot is yet to be placed. ",
                robot.isPlaced()
        );
    }

    @Test
    public void testLeftFrom00NTo00W() throws Exception
    {
        Position position = new Position(new int[] {0,0}, new North());

        robot.place(position);
        robot.left();

        Position targetPosition = new Position(new int[]{0,0}, new West());

        assertTrue("The targetPosition should equal the start position with " +
                        "the orientation changed one unit to the left. " +
                        "\nStart Position: " + position +
                        "\nTarget Position: " + targetPosition +
                        "\nActual Position: " + robot.getPosition(),
                targetPosition.equals(robot.getPosition())
        );
    }

    @Test
    public void testRightFrom00NTo00E() throws Exception
    {
        Position position = new Position(new int[] {0,0}, new North());

        robot.place(position);
        robot.right();

        Position targetPosition = new Position(new int[]{0,0}, new East());

        assertTrue("The targetPosition should equal the start position with" +
                        "the orientation changed one unit to the left. " +
                        "Start orientation: " + position.getOrientation() +
                        " Target Orienatation: " + targetPosition.getOrientation() +
                        " Actual Orientation: " + robot.getPosition(),
                targetPosition.equals(robot.getPosition())
        );
    }

    @Test
    public void testCycleRightUntilSameOrientationReached() throws Exception
    {
        Position position = new Position(new int[] {0,0}, new North());

        robot.place(position);
        robot.right();

        while (!robot.getPosition().equals(position))
        {
            robot.right();
        }

        assertTrue("The final position should be the same as the start" +
                        "position." +
                        "Start position: " + position.toString() +
                        " Actual Orientation: " + robot.getPosition(),
                position.equals(robot.getPosition())
        );
    }

    @Test
    public void testMove00NTo01N() throws Exception
    {
        Position position = new Position(new int[] {0,0}, new North());

        robot.place(position);
        robot.move();

        Position targetPosition = new Position(new int[]{0,1}, new North());

        assertTrue("move() should succeed since the robot has moved " +
                        "one unit from the old position, but remains " +
                        "still within the grid boundaries. \n" +
                        "Start Position: " + position.toString() + "\n" +
                        "TargetPosition: " + targetPosition.toString() + "\n" +
                        "Robot Position: " +
                        robot.getPosition().toString() + " ",
                robot.getPosition().equals(targetPosition)
        );
    }

    @Test
    public void testMoveIsFrom22ETo32E() throws Exception
    {
        Position position = new Position(new int[] {2,2}, new East());

        robot.place(position);
        robot.move();

        Position targetPosition = new Position(new int[]{3,2}, new East());

        assertTrue("move() should succeed since the robot has moved " +
                        "one unit from the old position, but remains " +
                        "still within the grid boundaries. \n" +
                        "Start Position: " + position.toString() + "\n" +
                        "TargetPosition: " + targetPosition.toString() + "\n" +
                        "Robot Position: " +
                        robot.getPosition().toString() + " ",
                robot.getPosition().equals(targetPosition)
        );
    }

    @Test
    public void testMoveIsFrom44WTo34W() throws Exception
    {
        Position position = new Position(new int[] {4,4}, new West());

        robot.place(position);
        robot.move();

        Position targetPosition = new Position(new int[]{3,4}, new West());

        assertTrue("move() should succeed since the robot has moved " +
                        "one unit from the old position, but remains " +
                        "still within the grid boundaries. \n" +
                        "Start Position: " + position.toString() + "\n" +
                        "TargetPosition: " + targetPosition.toString() + "\n" +
                        "Robot Position: " +
                        robot.getPosition().toString() + " ",
                robot.getPosition().equals(targetPosition)
        );
    }

    @Test
    public void testMoveIsFrom32STo31S() throws Exception
    {
        Position position = new Position(new int[] {3,2}, new South());

        robot.place(position);
        robot.move();

        Position targetPosition = new Position(new int[]{3,1}, new South());

        assertTrue("move() should succeed since the robot has moved " +
                        "one unit from the old position, but remains " +
                        "still within the grid boundaries. \n" +
                        "Start Position: " + position.toString() + "\n" +
                        "TargetPosition: " + targetPosition.toString() + "\n" +
                        "Robot Position: " +
                        robot.getPosition().toString() + " ",
                robot.getPosition().equals(targetPosition)
        );
    }

    @Test
    public void testMoveBreachesSouthBoundary() throws Exception
    {
        Position position = new Position(new int[] {3,0}, new South());

        robot.place(position);
        robot.move();

        assertTrue("move() should fail since the robot has attempted to " +
                        "move one unit outside the grid boundaries.\n" +
                        "Start Position: " + position.toString() + "\n" +
                        "Robot Position: " +
                        robot.getPosition().toString() + " ",
                robot.getPosition().equals(position)
        );
    }

    @Test
    public void testMoveBreachesNorthBoundary() throws Exception
    {
        Position position = new Position(new int[] {3,4}, new North());

        robot.place(position);
        robot.move();

        assertTrue("move() should fail since the robot has attempted to " +
                        "move one unit outside the grid boundaries.\n" +
                        "Start Position: " + position.toString() + "\n" +
                        "Robot Position: " +
                        robot.getPosition().toString() + " ",
                robot.getPosition().equals(position)
        );
    }

    @Test
    public void testMoveBreachesWestBoundary() throws Exception
    {
        Position position = new Position(new int[] {0,4}, new West());

        robot.place(position);
        robot.move();

        assertTrue("move() should fail since the robot has attempted to " +
                        "move one unit outside the grid boundaries.\n" +
                        "Start Position: " + position.toString() + "\n" +
                        "Robot Position: " +
                        robot.getPosition().toString() + " ",
                robot.getPosition().equals(position)
        );
    }

    @Test
    public void testMoveBreachesEastBoundary() throws Exception
    {
        Position position = new Position(new int[] {4,2}, new East());

        robot.place(position);
        robot.move();

        assertTrue("move() should fail since the robot has attempted to " +
                        "move one unit outside the grid boundaries.\n" +
                        "Start Position: " + position.toString() + "\n" +
                        "Robot Position: " +
                        robot.getPosition().toString() + " ",
                robot.getPosition().equals(position)
        );
    }

    @Test
    public void testPlace00NMoveTo44E() throws Exception
    {
        Position position = new Position(new int[] {0,0}, new North());
        Position finishPosition = new Position(new int[]{4,4}, new East());

        robot.place(position); // 00N
        robot.move(); // 01N
        robot.move(); // 02N
        robot.move(); // 03N
        robot.move(); // 04N
        robot.right(); // 04E
        robot.move(); // 14E
        robot.move(); // 24E
        robot.move(); // 34E
        robot.move(); // 44E

        assertTrue("move() sequence should succeed since the robot has " +
                        "been placed at the start position and commanded " +
                        "to move to the finish position." +
                        "Start Position: " + position.toString() + "\n" +
                        "TargetPosition: " + finishPosition.toString() + "\n" +
                        "Robot Position: " +
                        robot.getPosition().toString() + " ",
                robot.getPosition().equals(finishPosition)
        );


    }

    @Test
    public void testPlace00NMoveTo34W() throws Exception
    {
        Position position = new Position(new int[] {0,0}, new North());
        Position finishPosition = new Position(new int[]{3,4}, new West());

        robot.place(position); // 00N
        robot.place(new Position(new int[] {4,4}, new West())); // 44W
        robot.move(); // 34W

        assertTrue("move() sequence should succeed since the robot has " +
                        "been placed at the start position and commanded " +
                        "to move to the finish position. \n" +
                        "Start Position: " + position.toString() + "\n" +
                        "TargetPosition: " + finishPosition.toString() + "\n" +
                        "Robot Position: " +
                        robot.getPosition().toString() + " ",
                robot.getPosition().equals(finishPosition)
        );
    }

    @Test
    public void testPlace00NMoveTo43S() throws Exception
    {
        System.out.println("Start Moving");
        Position position = new Position(new int[] {0,0}, new North());
        Position finishPosition = new Position(new int[]{4,3}, new South());

        robot.place(position); // 00N
        robot.place(new Position(new int[] {4,4}, new South())); // 44S
        robot.move(); // 43S
        robot.left(); // 43W
        robot.left(); // 43N
        robot.move();
        robot.right(); // 43S
        robot.right(); // 43S
        robot.move();

        assertTrue("move() sequence should succeed since the robot has " +
                        "been placed at the start position and commanded " +
                        "to move to the finish position.\n" +
                        "Start Position: " + position.toString() + "\n" +
                        "TargetPosition: " + finishPosition.toString() + "\n" +
                        "Robot Position: " +
                        robot.getPosition().toString() + " ",
                robot.getPosition().equals(finishPosition)
        );
    }

    @Test
    public void testPlace00NMovePlace44ERightMovePlace44W() throws Exception
    {
        System.out.println("Start Moving");
        Position position = new Position(new int[] {0,0}, new North());
        Position finishPosition = new Position(new int[]{4,4}, new West());

        robot.place(position); // 00N
        robot.move(); // 01N
        robot.place(new Position(new int[] {4,4}, new East())); // 44S
        robot.right();
        robot.move(); // 43S
        robot.place(new Position(new int[] {4,4}, new West())); // 44W

        assertTrue("move() sequence should succeed since the robot has " +
                        "been placed at the start position and commanded " +
                        "to move to the finish position.\n" +
                        "Start Position: " + position.toString() + "\n" +
                        "TargetPosition: " + finishPosition.toString() + "\n" +
                        "Robot Position: " +
                        robot.getPosition().toString() + " ",
                robot.getPosition().equals(finishPosition)
        );
    }
}