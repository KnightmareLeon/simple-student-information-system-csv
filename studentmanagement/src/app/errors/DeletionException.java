package app.errors;

public abstract class DeletionException extends ExceptionWithWindow{
    public DeletionException(){
        this.setErrMsgTitle("Deletion Error");
    }
}
