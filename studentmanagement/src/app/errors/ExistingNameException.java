package app.errors;

public class ExistingNameException extends ExceptionWithWindow{
    public ExistingNameException(){
        this.setErrMsg("Error. Name already exists. Please try again.");
        this.setErrMsgTitle("Name Already Exists");

    }
}
