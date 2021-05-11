package com.xmen.application.cqrs.querybus;

/**
 * Generic query bus
 *
 * @author <a href="davidsandoval9217@gmail.com"> David Sandoval</a>
 * @since 1.0
 * @version 1.0
 */
public interface QueryBus {

    <T> T handle(Query<T> query) throws Exception;
}
