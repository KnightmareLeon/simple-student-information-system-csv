package app.errors;

public class ProgramDeletionException extends DeletionException{
    public ProgramDeletionException(){
        super();
        this.setErrMsg("Program has students in it. Delete all students under this program before proceeding to delete this.");
    }
}
