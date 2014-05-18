package au.com.brentoncrowley.toyrobot.states.orientation;

import au.com.brentoncrowley.toyrobot.models.Position;

/**
 * Created by brentoncrowley on 02/05/2014.
 */
public class North implements OrientationState
{
    @Override
    public Position move(Position currentPosition)
    {
        OrientationState robotOrientation = currentPosition.getOrientation();
        int[] coordinate = currentPosition.getCoordinate();

        // move N: increment y by 1
        coordinate = new int[]{coordinate[0], coordinate[1] + 1};
        Position newPosition = new Position(coordinate, robotOrientation);

        return newPosition;
    }

    @Override
    public OrientationState rotateLeft()
    {
        return new West();
    }

    @Override
    public OrientationState rotateRight()
    {
        return new East();
    }

    @Override
    public String toString()
    {
        return Position.N;
    }
}
