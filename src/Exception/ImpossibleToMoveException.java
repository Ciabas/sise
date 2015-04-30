package Exception;

public class ImpossibleToMoveException extends Exception
{
    //Parameterless Constructor
    public ImpossibleToMoveException() {}

    //Constructor that accepts a message
    public ImpossibleToMoveException(String message)
    {
       super(message);
    }
}

