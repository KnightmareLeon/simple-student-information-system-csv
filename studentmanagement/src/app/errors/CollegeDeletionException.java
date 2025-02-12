package app.errors;

public class CollegeDeletionException extends DeletionException {
    public CollegeDeletionException(){
        super();
        this.setErrMsg("College has programs in it. Delete all programs under this college before proceeding to delete this.");
    }
}
