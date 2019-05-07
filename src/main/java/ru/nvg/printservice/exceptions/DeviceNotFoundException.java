package ru.nvg.printservice.exceptions;

/* Thrown when  is not found
 * @see ru.nvg.printservice.domain.Device
 */
public class DeviceNotFoundException extends RuntimeException {
    public DeviceNotFoundException(String msg) {
        super("Device is not found " + msg);
    }
}
