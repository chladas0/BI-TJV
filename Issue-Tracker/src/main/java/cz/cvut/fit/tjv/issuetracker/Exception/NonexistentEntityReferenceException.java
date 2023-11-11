package cz.cvut.fit.tjv.issuetracker.Exception;

public class NonexistentEntityReferenceException extends RuntimeException{
        public NonexistentEntityReferenceException(){}

        public NonexistentEntityReferenceException(String message)
        {
            super(message);
        }
}
