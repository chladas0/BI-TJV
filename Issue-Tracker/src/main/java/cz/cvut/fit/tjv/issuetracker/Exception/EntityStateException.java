package cz.cvut.fit.tjv.issuetracker.Exception;

public class EntityStateException extends RuntimeException{
    public EntityStateException(){}

    public EntityStateException(String message)
    {
        super(message);
    }
}
