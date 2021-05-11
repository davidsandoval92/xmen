package com.xmen.application.query;

import com.xmen.application.cqrs.querybus.Query;
import com.xmen.domain.entities.DnaVerificationAttempt;
import java.util.List;

/**
 * Create dna verified attempt query
 *
 * @author <a href="davidsandoval9217@gmail.com"> David Sandoval</a>
 * @since 1.0
 * @version 1.0
 */
public class GetDnaVerificationAttemptQuery extends Query<List<DnaVerificationAttempt>> {

}
