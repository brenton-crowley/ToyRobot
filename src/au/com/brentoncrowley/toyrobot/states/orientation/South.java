package au.com.brentoncrowley.toyrobot.states.orientation;

import au.com.brentoncrowley.toyrobot.models.Position;

/**
 * Created by brentoncrowley on 02/05/2014.
 */
public class South implements OrientationState
{
    @Override
    public Position move(Position currentPosition)
    {
        OrientationState robotOrientation = currentPosition.getOrientation();
        int[] coordinate = currentPosition.getCoordinate();

        // move S: decrement y by -1
        coordinate = new int[] {coordinate[0], coordinate[1]-1};
        Position newPosition = new Position(coordinate, robotOrientation);

        return newPosition;
    }

    @Override
    public OrientationState rotateLeft()
    {
        return new East();
    }

    @Override
    public OrientationState rotateRight()
    {
        return new West();
    }

    @Override
    public String toString()
    {
        return Position.S;
    }
}
