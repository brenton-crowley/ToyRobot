package au.com.brentoncrowley.toyrobot.models;

import au.com.brentoncrowley.toyrobot.states.orientation.East;
import au.com.brentoncrowley.toyrobot.states.orientation.North;
import au.com.brentoncrowley.toyrobot.states.orientation.OrientationState;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TableTopTest
{

    private static final int SIZE = 5;

    private TableTop tableTop;

    @Before
    public void setUp() throws Exception
    {
        this.tableTop = new TableTop(SIZE, SIZE);
    }

    @Test
    public void testCreateGrid5x5() throws Exception
    {
        int size = 5;
        TableTop tableTop = new TableTop(size, size);

        assertTrue("TableTop should be a " + size + "x" + size + " grid, " +
                "but it is not.", (tableTop.size() == (size*size)));

    }

    @Test
    public void testPosition00NIsValid() throws Exception
    {
        int[] coordinate = {0,0};
        OrientationState orientation = new North();

        Position position = new Position(coordinate, orientation);

        assertTrue("Position: " + position.toString() + " is INVALID, " +
                        "but it should be valid.",
                tableTop.hasPositionInGrid(position)
        );
    }

    @Test
    public void testPosition54EIsInvalid() throws Exception
    {
        int size = 5;
        TableTop tableTop = new TableTop(size, size);
        int[] coordinate = {5,4};
        OrientationState orientation = new East();

        Position position = new Position(coordinate, orientation);

        assertFalse("Position: " + position.toString() + " is VALID, " +
                        "but it should be invalid.",
                tableTop.hasPositionInGrid(position)
        );
    }

    @Test
    public void testPreviousPositionNullAfterFirstMoveRobotToPosition() throws Exception
    {
        int size = 5;
        TableTop tableTop = new TableTop(size, size);
        int[] coordinate = {0,0};
        OrientationState orientation = new East();
        Robot robot = new Robot();
        Position position = new Position(coordinate, orientation);

        Position previousPosition =
                tableTop.moveRobotToPosition(robot, position);

        assertNull("Previous Robot Position should be null since it was yet " +
                        "to be placed",
                previousPosition
        );
    }

    @Test
    public void testPreviousPositionNotNullAfterTwoMoves() throws Exception
    {
        int size = 5;
        TableTop tableTop = new TableTop(size, size);
        Robot robot = new Robot();

        Position position = new Position(new int[]{0,0}, new North());
        Position previousPosition =
                tableTop.moveRobotToPosition(robot, position);

        position = new Position(new int[]{0,1}, new North());
        previousPosition =
                tableTop.moveRobotToPosition(robot, position);

        assertNotNull("Previous Robot Position should not be null since it " +
                        "was already placed.",
                previousPosition
        );
    }

    @Test
    public void testNoRobotInTableTopGridWhenNoMove() throws Exception
    {
        int size = 5;
        TableTop tableTop = new TableTop(size, size);
        Robot robot = new Robot();

        int count = 0;
        for (Robot r : tableTop.values())
        {
            if (r != null)
                count++;
        }

        assertTrue("No robots should exist in the grid when no move has been " +
                        "made. Num robots found in grid == " + count,
                (count == 0)
        );
    }

    @Test
    public void testOnlyOneRobotInTableTopGridAfterTwoMoves() throws Exception
    {
        int size = 5;
        TableTop tableTop = new TableTop(size, size);
        Robot robot = new Robot();

        Position position = new Position(new int[]{0,0}, new North());
        Position previousPosition =
                tableTop.moveRobotToPosition(robot, position);

        position = new Position(new int[]{0,1}, new North());
        previousPosition =
                tableTop.moveRobotToPosition(robot, position);

        int count = 0;
        for (Robot r : tableTop.values())
        {
            if (r != null)
                count++;
        }

        assertTrue("Only one robot should exist in the grid at any one time. " +
                        "Num robots found in grid == " + count,
                (count == 1)
        );
    }

    @Test
    public void testOnlyOneRobotInTableTopGridAfterTenMoves() throws Exception
    {
        int size = 5;
        TableTop tableTop = new TableTop(size, size);
        Robot robot = new Robot();

        // After Ten moves
        for (int i = 0; i < 10; i++)
        {
            int x = (int) Math.floor(Math.random() * size);
            int y = (int) Math.floor(Math.random() * size);
            OrientationState orientation =
                    Position.orientations.get((
                            (int) Math.floor(
                                    Math.random() * Position.orientations.size()
                            )
                    ));

            Position position = new Position(new int[]{x,y}, orientation);
            Position previousPosition =
                    tableTop.moveRobotToPosition(robot, position);
        }

        int count = 0;
        for (Robot r : tableTop.values())
        {
            if (r != null)
                count++;
        }

        assertTrue("Only one robot should exist in the grid at any one time. " +
                        "Num robots found in grid == " + count,
                (count == 1)
        );
    }

    @Test
    public void testDisplayGrid() throws Exception
    {
        Robot robot = new Robot();

        int[] coordinate = {1, 2};
        robot.place(new Position(coordinate, new North()));
        robot.left();
        robot.move();

        Robot gridRobot = robot.getTableTop().valueForCoordinate(
                robot.getPosition().getCoordinate());

        System.out.println(robot.getTableTop().displayGrid());

        assertNotNull("The robot should exist at the coordinate, " +
                "but it does not." +
                "\nCoordinate: " + coordinate.toString() +
                "\n Value at coordinate: " + gridRobot,
                gridRobot
        );

    }

}