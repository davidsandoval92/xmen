package com.xmen.application.cqrs.querybus;

/**
 * Generic query handler
 *
 * @author <a href="davidsandoval9217@gmail.com"> David Sandoval</a>
 * @since 1.0
 * @version 1.0
 */
public interface QueryHandler<T, U extends Query<T>> {

    T handle(U query) throws Exception;
}
