package cz.cvut.fit.tjv.issuetracker.exception;

public class EntityStateException extends RuntimeException{
    public EntityStateException(){}

    public EntityStateException(String message)
    {
        super(message);
    }
}
