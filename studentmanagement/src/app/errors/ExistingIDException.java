package app.errors;

public class ExistingIDException extends ExceptionWithWindow{
    public ExistingIDException(){
        this.setErrMsg("Error. ID already exists. Please try again.");
        this.setErrMsgTitle("ID Already Exists.");
    }
}
