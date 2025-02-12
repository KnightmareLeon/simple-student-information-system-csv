package app.errors;

public class ExistingCodeException extends ExceptionWithWindow{
    public ExistingCodeException(){
        this.setErrMsg("Error. Code already exists. Please try again.");
        this.setErrMsgTitle("Code Already Exists");
    }
}
