package au.com.brentoncrowley.toyrobot.models;

import au.com.brentoncrowley.toyrobot.states.orientation.*;

import java.util.Arrays;
import java.util.HashMap;

/**
 * Created by brentoncrowley on 29/04/2014.
 */
public class Position
{

    public static final String N = "N";
    public static final String E = "E";
    public static final String S = "S";
    public static final String W = "W";

    public static final HashMap<String, OrientationState> orientations;
    static
    {
        orientations = new HashMap<String, OrientationState>();
        orientations.put(Position.N, new North());
        orientations.put(Position.E, new East());
        orientations.put(Position.S, new South());
        orientations.put(Position.W, new West());
    }

    private OrientationState orientation;

    private int[] coordinate;
    /*
    *
    * The only limiting factor of a Position object is that its orientation
    * must be one of N, E, S, W. An exception is thrown if invalid input
    * is received.
    *
    * @param Point coordinate the x,y point of the position.
    * @param String orientation the direction of the position: N, E, S, W
    *
    * */
    public Position(int[] coordinate, OrientationState orientation)
    {
        this.coordinate = coordinate;
        this.orientation = orientation;
    }

    /*
    *
    * Returns a boolean indicating the validity of the position. A position
    * is valid if it has a valid coordinate and orientation.
    *
    * @return boolean true if valid otherwise false
    * */
    public boolean isValid()
    {
        return (isValidCoordinate() && isValidOrientation());
    }

    /*
    *
    * Returns a boolean indicating the validity of the orientation.
    * An orientation is valid when it is not null.
    *
    * @return boolean true if valid otherwise false
    * */
    public boolean isValidOrientation()
    {
        if (orientation == null)
            return false;

        return true;
    }

    /*
    *
    * Returns a boolean indicating the validity of the coordinate.
    * A coordinate is valid when it is not null, and its length is two.
    *
    * @return boolean true if valid otherwise false
    * */
    public boolean isValidCoordinate()
    {
        if (coordinate == null)
            return false;

        if (coordinate.length != 2)
            return false;

        return true;
    }

    /*
    * Returns the value at index[0] of the coordinate.
    *
    * @return int
    * */
    public int getX()
    {
        if (coordinate != null && coordinate.length > 0)
            return coordinate[0];

        return -1;
    }

    /*
    * Returns the value at index[1] of the coordinate.
    *
    * @return int
    * */
    public int getY()
    {
        if (coordinate != null && coordinate.length > 1)
            return coordinate[1];

        return -1;
    }

    // Overrides

    @Override
    public String toString()
    {
        String toString = "";

        if (orientation != null)
            toString += orientation.toString();

        if (coordinate != null)
            toString += Arrays.toString(coordinate);

        return toString;
    }

    @Override
    public boolean equals(Object obj)
    {
        Position position = (Position) obj;

        if (getX() != position.getX())
            return false;

        if (getY() != position.getY())
            return false;

        if (orientation.getClass() != position.getOrientation().getClass())
            return false;

        return true;
    }

    // Getters / Setters

    public OrientationState getOrientation()
    {
        return orientation;
    }

    public int[] getCoordinate()
    {
        return coordinate;
    }
}
