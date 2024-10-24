package ch.hslu.se.hsluhotel.Exceptions;

/**
 * An exception class that indicates a room with a specified ID could not be found.
 * This exception is typically thrown in scenarios where a room lookup operation fails
 * because the room with the specified ID does not exist in the system.
 */

public class RoomNotFoundExeption extends RuntimeException {
    public RoomNotFoundExeption(Long id) {
        super("Could not find room with id " + id);
    }

}
