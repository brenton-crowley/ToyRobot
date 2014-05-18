package au.com.brentoncrowley.toyrobot.models;

import au.com.brentoncrowley.toyrobot.states.orientation.OrientationState;

/**
 * Created by brentoncrowley on 29/04/2014.
 */
public class Robot
{
    public static final int SIZE = 5;

    private Position position;
    private TableTop tableTop;

    public Robot()
    {
        this.tableTop = new TableTop(SIZE, SIZE);
    }

    /*
    * The place method is the primary move operation for the robot. It can be
    * called directly to reposition the robot, but it is also used internally
    * for the left(), right() and move() operations.
    *
    * @param Position position the intended new position for the robot
    *
    * @return boolean indicating the success of the placement
    * */
    public boolean place(Position position)
    {
        // validate position
        if (!tableTop.hasPositionInGrid(position))
        {
            System.out.println("Invalid position.");
            return false;
        }

        // don't bother moving to identical position
        if (isPlaced() && this.position.equals(position))
            return false;

        tableTop.moveRobotToPosition(this, position);

        System.out.println("=> " + position.toString());

        return true;
    }

    /*
    * MOVE will move the toy robot one unit forward in the direction it 
    * is currently facing. If the robot has not been placed then the instruction
    * is ignored.
    *
    * */
    public void move()
    {
        if (isPlaced())
        {
            OrientationState robotOrientation = position.getOrientation();
            Position newPosition = robotOrientation.move(position);

            place(newPosition);
        }
    }

    /*
    * LEFT will rotate the robot 90 degrees to the left
    * without changing the position of the robot. If the robot has not been
    * placed then the instruction is ignored.
    *
    * */
    public void left()
    {
        if (isPlaced())
        {
            place(new Position(position.getCoordinate(),
                    position.getOrientation().rotateLeft())
            );
        }
    }

    /*
    * RIGHT will rotate the robot 90 degrees to the right
    * without changing the position of the robot. If the robot has not been
    * placed then the instruction is ignored.
    *
    * */
    public void right()
    {
        if (isPlaced())
        {
            place(new Position(position.getCoordinate(),
                    position.getOrientation().rotateRight())
            );
        }
    }

    /*
    * REPORT will announce the X,Y and Orientation of the robot. 
    *
    * @return String in format 'orientation[coordinate]' where orientation
    *                is one of Position.orientations: N,E,S,W, and coordinate
    *                is an int[]: [0, 0]
    *                e.g. N[0,0], E[1,4], S[2,2] etc.
    *
    * */
    public String report()
    {
        if (isPlaced())
            return "Robot is: " + position.toString();
        else
            return "Robot not yet placed.";
    }

    /*
    *
    * Returns a boolean indicating the existence of the robot on the table top
    * grid. If the robot does not have a position then it is not placed. Will
    * return true if the robot is in a position on the grid otherwise false.
    *
    * @return boolean that determines whether or not the robot is in a position
    *                 on the table top grid.
    *
    * */
    public boolean isPlaced()
    {
        if (position == null)
            return false;

        return true;
    }

    // Getters / Setters

    public Position getPosition()
    {
        return position;
    }

    public void setPosition(Position position)
    {
        this.position = position;
    }

    public TableTop getTableTop()
    {
        return tableTop;
    }
}
