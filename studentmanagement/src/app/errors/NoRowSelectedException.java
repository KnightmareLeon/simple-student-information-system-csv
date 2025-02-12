package app.errors;

public class NoRowSelectedException extends ExceptionWithWindow{
    public NoRowSelectedException(){
        this.setErrMsg("Error. No row selected. Please select a row.");
        this.setErrMsgTitle("No Row Selected");

    }
}
