package sudyar.exception;

/**
 * Используется, когда пытаемся добавить элемент, имеющий такие же "уникальные" поля, как у уже имеющихся элементов коллекци
 */
public class DuplicateException extends RuntimeException{

    public DuplicateException(String message) {
        super(message);
    }
}
