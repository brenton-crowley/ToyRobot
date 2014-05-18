package au.com.brentoncrowley.toyrobot.states.orientation;

import au.com.brentoncrowley.toyrobot.models.Position;

/**
 * Created by brentoncrowley on 02/05/2014.
 */
public class East implements OrientationState
{

    @Override
    public Position move(Position currentPosition)
    {
        OrientationState robotOrientation = currentPosition.getOrientation();
        int[] coordinate = currentPosition.getCoordinate();

        // move E: increment x by 1
        coordinate = new int[] {coordinate[0]+1, coordinate[1]};
        Position newPosition = new Position(coordinate, robotOrientation);

        return newPosition;
    }

    @Override
    public OrientationState rotateLeft()
    {
        return new North();
    }

    @Override
    public OrientationState rotateRight()
    {
        return new South();
    }

    @Override
    public String toString()
    {
        return Position.E;
    }
}
