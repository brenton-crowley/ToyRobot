package au.com.brentoncrowley.toyrobot;

/**
 * Created by brentoncrowley on 29/04/2014.
 */
public class Main
{
    private static ToyRobotSimulator trs;

    public static void main(String[] args)
    {
        trs = new ToyRobotSimulator();
        trs.openInputStream();

    }

}
