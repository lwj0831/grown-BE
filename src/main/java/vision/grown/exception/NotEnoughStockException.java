package vision.grown.exception;

public class NotEnoughStockException extends RuntimeException{
    public NotEnoughStockException(String message) {
        super(message);
    }
}
