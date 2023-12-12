package cz.cvut.fit.tjv.issuetracker.exception;

import org.springframework.http.HttpStatusCode;

public class EntityStateException extends RuntimeException{
    public EntityStateException(){}
    HttpStatusCode code = HttpStatusCode.valueOf(404);

    public EntityStateException(String message)
    {
        super(message);
    }

    public HttpStatusCode getStatusCode()
    {
        return code;
    }
}
