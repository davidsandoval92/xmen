package com.xmen.application.cqrs.commandbus;

/**
 * Generic command bus
 *
 * @author <a href="davidsandoval9217@gmail.com"> David Sandoval</a>
 * @since 1.0
 * @version 1.0
 */
public interface CommandBus {

    void handle(Command command) throws Exception;
}