package com.xmen.application.cqrs.commandbus;

/**
 * Generic command handler
 *
 * @author <a href="davidsandoval9217@gmail.com"> David Sandoval</a>
 * @since 1.0
 * @version 1.0
 */
public interface CommandHandler<T extends Command> {

    void handle(T command) throws Exception;
}