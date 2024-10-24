package ch.hslu.se.hsluhotel.web.advice;
import ch.hslu.se.hsluhotel.Exceptions.RoomNotFoundExeption;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Controller advice to handle {@link RoomNotFoundExeption} exceptions.
 * This class provides a way to return a custom response when a {@code RoomNotFoundExeption} is thrown.
 */

@ControllerAdvice
public class RoomNotFoundAdvice {

    // Exception handler for {@link RoomNotFoundExeption}.
    // This method is called when a {@code RoomNotFoundExeption} is thrown, and it returns the exception's message.
    @ResponseBody
    @ExceptionHandler(RoomNotFoundExeption.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String employeeNotFoundHandler(RoomNotFoundExeption roomNotFoundExeptionException) {
        return roomNotFoundExeptionException.getMessage();
    }
}
