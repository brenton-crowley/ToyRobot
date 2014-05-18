package au.com.brentoncrowley.toyrobot;

import au.com.brentoncrowley.toyrobot.models.Position;
import au.com.brentoncrowley.toyrobot.models.Robot;
import au.com.brentoncrowley.toyrobot.models.TableTop;
import au.com.brentoncrowley.toyrobot.states.orientation.North;
import au.com.brentoncrowley.toyrobot.states.orientation.OrientationState;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by brentoncrowley on 29/04/2014.
 */
public class ToyRobotSimulator
{
    private static final String PLACE = "place";
    private static final String MOVE = "move";
    private static final String REPORT = "report";
    private static final String LEFT = "left";
    private static final String RIGHT = "right";
    private static final String EXIT = "exit";

    protected Robot robot;

    public ToyRobotSimulator()
    {
        robot = new Robot();

        System.out.println(introMessage());
    }

    /*
    * Starts the robot command sequence driven by user input. Valid commands
    * include:
    *
    * place int int orientation (place 0 0 N)
    * move (move one unit in the current orientation)
    * left (rotates orientation to the left)
    * right (rotates orientation to the right)
    * report (prints a string of the current position)
    * report -v (prints a visual representation of the grid)
    *
    * */
    public void openInputStream()
    {
        try
        {
            BufferedReader br =
                    new BufferedReader(new InputStreamReader(System.in));

            String input;

            while((input=br.readLine())!=null &&
                    !input.equals(EXIT))
            {
                validateInput(input);
            }

        }
        catch(IOException io)
        {
            io.printStackTrace();
        }
    }

    /*
    * Attempts to interpret the raw user input, and then translates the input
    * into a command the robot can understand.
    *
    * @param String input
    * */
    protected void validateInput(String input)
    {
        String[] commands = input.trim().split(" ");
        String outputMessage = null;

        if (commands.length == 1)
        {
            // single command
            if (commands[0].equals(MOVE))
            {
                robot.move();
            }
            else if (commands[0].equals(LEFT))
            {
                robot.left();
            }
            else if (commands[0].equals(RIGHT))
            {
                robot.right();
            }
            else if (commands[0].equals(REPORT))
            {
                System.out.println(robot.report());
            }
            else
            {
                outputMessage = "Invalid command";
            }
        }
        else if (commands.length == 2)
        {
            if (commands[0].equals(REPORT) && commands[1].equals("-v"))
            {
                System.out.println(robot.report());
                System.out.println(robot.getTableTop().displayGrid());
            }
            else
            {
                outputMessage = "Invalid command";
            }
        }
        else if (commands.length == 4) // place command must equal 4
        {
            // probably a place command
            try
            {
                int x = Integer.parseInt(commands[1]);
                int y = Integer.parseInt(commands[2]);
                String orientationString = commands[3];

                if (Position.orientations.get(orientationString) != null)
                {
                    OrientationState orientation =
                            Position.orientations.get(orientationString);

                    robot.place(new Position(new int[]{x, y}, orientation));
                }
                else
                    System.out.println("Orientation input is invalid. " +
                            "\nOrientation input: " + orientationString +
                             "\nValid orientations: " +
                             Position.orientations.keySet().toString() + "\n");

            }
            catch (NumberFormatException error){}

        }
        else
        {
            outputMessage = "Invalid command";
        }

        if (outputMessage != null)
            System.out.println(outputMessage);
    }

    private String introMessage()
    {
        String introMessage = "Greetings human. I will serve your commands in " +
                "the form of movement on a 5x5 grid. " +
                "You can place me, move me, rotate me and ask me for " +
                "my current position. You must place me before you can " +
                "make any other kind of movement. \n\n" +
                "If you want to place " +
                "me on the board then call the 'place int int orientation' " +
                "command. You can place me anywhere on the grid, " +
                "and at any time. I will not respond to place commands " +
                "that fall outside of the grid boundaries.\n" +
                "The following are valid place commands:\n" +
                "place 0 0 N\n" +
                "place 0 1 S\n" +
                "place 3 4 E" +
                "\n\n" +
                "A move command will move me one unit in the direction that " +
                "I am facing. I will not fall off the table, so if you " +
                "try to move me at a grid boundary then I will not respond.\n" +
                "The following is a valid move command:\n" +
                "move" +
                "\n\n" +
                "You can change the direction I am facing by calling the " +
                "left or right command. I will always respond to these " +
                "commands, so long as I am placed.\n" +
                "The following are valid commands to rotate me:\n" +
                "left\n" +
                "right" +
                "\n\n" +
                "You can ask me where I am on the board by making " +
                "the report command. If you supply -v for the command " +
                "a visual of the grid will print out.\n" +
                "The following is a valid report command:\n" +
                "report\n" +
                "report -v" +
                "\n\n" +
                "To stop the commands type 'exit'";

         return introMessage;
    }
}
