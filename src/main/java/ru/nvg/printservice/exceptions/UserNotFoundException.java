package ru.nvg.printservice.exceptions;


import javassist.NotFoundException;

/* Thrown when  is not found
 * @see ru.nvg.printservice.domain.User
 */
public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String msg) {
        super("User is not found " + msg);
    }
}
