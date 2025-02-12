package app.errors;

public class EmptyInputException extends ExceptionWithWindow{
    public EmptyInputException(){
        this.setErrMsg("Error. Required fields are empty. Please add input to required fields");
        this.setErrMsgTitle("Empty Input Error");
    }
}
