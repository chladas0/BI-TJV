package cz.cvut.fit.tjv.issuetracker.exception;

public class IllegalDataException extends RuntimeException{
        public IllegalDataException(){}

        public IllegalDataException(String message)
        {
            super(message);
        }
}
