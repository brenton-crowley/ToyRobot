package au.com.brentoncrowley.toyrobot.states.orientation;

import au.com.brentoncrowley.toyrobot.models.Position;

/**
 * Created by brentoncrowley on 02/05/2014.
 */
public class West implements OrientationState
{

    @Override
    public Position move(Position currentPosition)
    {
        OrientationState robotOrientation = currentPosition.getOrientation();
        int[] coordinate = currentPosition.getCoordinate();

        // move W: decrement x by -1
        coordinate = new int[] {coordinate[0]-1, coordinate[1]};
        Position newPosition = new Position(coordinate, robotOrientation);

        return newPosition;
    }

    @Override
    public OrientationState rotateLeft()
    {
        return new South();
    }

    @Override
    public OrientationState rotateRight()
    {
        return new North();
    }

    @Override
    public String toString()
    {
        return Position.W;
    }
}
