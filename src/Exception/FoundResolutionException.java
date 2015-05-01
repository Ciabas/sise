package Exception;

public class FoundResolutionException extends Exception
{
    //Parameterless Constructor
    public FoundResolutionException() {}

    //Constructor that accepts a message
    public FoundResolutionException(String message)
    {
       super(message);
    }
}


