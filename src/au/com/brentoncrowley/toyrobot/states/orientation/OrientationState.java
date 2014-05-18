package au.com.brentoncrowley.toyrobot.states.orientation;

import au.com.brentoncrowley.toyrobot.models.Position;

/**
 * Created by brentoncrowley on 02/05/2014.
 */
public interface OrientationState
{
    /*
    * Return a new position that is one unit farther from the current position.
    * Each implementation of this method should handle the correct unit
    * movement. For example, if there is a North() implementation then it
    * should increment the y-position of the robot by unit.
    *
    * e.g. [0, 0] => [0, 1]
    *
    * @return Position the new position
    * */
    public Position move(Position currentPosition);

    /*
    * Returns the orientation to the left of the implemented orientation.
    * For example, if there is a East() implementation then the left
    * orientation would be North()
    *
    * @return OrientationState
    * */
    public OrientationState rotateLeft();

    /*
    * Returns the orientation to the right of the implemented orientation.
    * For example, if there is a East() implementation then the right
    * orientation would be South()
    *
    * @return OrientationState
    * */
    public OrientationState rotateRight();
}
