package au.com.brentoncrowley.toyrobot.models;

import java.util.*;

/**
 * Created by brentoncrowley on 29/04/2014.
 */
public class TableTop
{
    private int rowSize;
    private int colSize;

    private Map<String, Robot> grid;

    public TableTop(int rowSize, int colSize)
    {
        this.rowSize = rowSize;
        this.colSize = colSize;

        createGrid();
    }

    /*
    *
    * The rowSize * colSize of the grid.
    *
    * @return int
    * */
    public int size()
    {
        if (grid != null)
            return grid.size();

        return 0;
    }

    /*
    *
    * Returns the set of values in the underlying grid.
    *
    * @return Collection<Robot> a set of values
    * */
    public Collection<Robot> values()
    {
        return grid.values();
    }

    /*
    *
    * Look up a value in the grid. The grid is immutable, so this provides
    * a mechanism to lookup values without giving full access to the grid.
    *
    * @param int[] coordinate The value to be extracted from the grid.
    *
    * @return Robot A robot if it exists at a position otherwise null
    * */
    public Robot valueForCoordinate(int[] coordinate)
    {
        String key = gridKeyForCoordinate(coordinate);

        if (key == null)
            return null;

        Robot robot = grid.get(key);

        return robot;
    }

    // first placement
    public Position moveRobotToPosition(Robot robot, Position position)
    {
        String gridKey = gridKeyForCoordinate(position.getCoordinate());
        Position previousPosition = robot.getPosition();

        if (previousPosition != null)
        {
            String previousPositionKey =
                    gridKeyForCoordinate(previousPosition.getCoordinate());
            grid.put(previousPositionKey, null);
        }

        robot.setPosition(position);
        grid.put(gridKey, robot);

        return previousPosition;
    }

    /*
    *
    * Returns a boolean indicating the validity of a position in the tableTop.
    * Will return true if the position is valid otherwise false.
    *
    * A position exists in the grid if it meets the following conditions:
    * • the supplied position is not null
    * • the supplied position is well-formed
    * • x-coordinate falls between the scope of rowSize
    * • y-coordinate falls between the scope of colSize
    * • grid key exists (valid key in HashMap<String, Robot> grid)
    *
    * @param Position position the position to validate
    *
    * @return boolean true is position in tableTop otherwise false.
    * */
    public boolean hasPositionInGrid(Position position)
    {
        String message = null;

        if (position == null)
            message = "Position must not be null. Position: " + position;

        // Test for a well formed position
        if (!position.isValid())
            message = "Position must not be valid. Position: " + position;

        // Check that both values of the coordinate are valid for this grid.
        int x = position.getX();
        int y = position.getY();

        if (x < 0 || x >= rowSize)
        {
            message = "X position must fall between 0-" + rowSize + ". " +
                    "Position: " + position;
        }

        if (y < 0 || y >= colSize)
        {
            message = "Y position must fall between 0-" + colSize + ". " +
                    "Position: " + position;
        }

        String gridKey = gridKeyForCoordinate(position.getCoordinate());

        if (gridKey == null)
            message = "Coordinate must not be null: " + position;

        if (!grid.keySet().contains(gridKey))
        {
            message = "Invalid coordinate. Position: " + position + "\n" +
                    "GridKey: " + gridKey + "\n";
        }


        if (message != null)
        {
            // System.out.println(message);
            return false;
        }

        return true;
    }
    /*
    * Prints the grid in ASCII format.
    *
    * @return String the formatted grid.
    * */
    public String displayGrid()
    {
        String grid = "";

        // col index
        for (int y = rowSize; y > 0; y--)
        {
            int[] coordinate;

            for (int x = 0; x < colSize; x++)
            {
                coordinate = new int[]{x, y-1};
                Robot robot = this.grid.get(gridKeyForCoordinate(coordinate));

                if (robot == null)
                    grid += " * ";
                else
                    grid += " " +
                            robot.getPosition().getOrientation().toString() +
                            " ";

            }

            grid += "\n";
        }

        return grid;
    }

    // Getters / Setters

    public int getColSize()
    {
        return colSize;
    }

    public int getRowSize()
    {
        return rowSize;
    }

    // private

    /*
    * Generates a hashmap with all the valid coordinates as String keys.
    * A string key has the form: '[0, 0]'
    *
    * */
    private void createGrid()
    {
        grid = new HashMap<String, Robot>();

        for (int i = 0; i < rowSize; i++)
        {
            for (int j = 0; j < colSize; j++)
            {
                int[] coordinate = {i, j};
                grid.put(gridKeyForCoordinate(coordinate), null);
            }
        }
    }

    /*
    * Will take any int[] with a length of two, and return a String
    * representation of that coordinate.
    *
    *
    * @param int[] coordinate e.g. [0, 0]
    *
    * @return String the converted coordinate key if valid otherwise null.
    *
    * */
    private String gridKeyForCoordinate(int[] coordinate)
    {
        if (coordinate == null)
            return null;

        if (coordinate.length != 2)
            return null;

        return Arrays.toString(coordinate);
    }
}
