package cz.frantisekfilip.ticketsandbets.mappers;

import cz.frantisekfilip.ticketsandbets.domain.dto.MatchDto;

public interface MatchMapper<A,B> {
    public B mapTo(A a);

    public A mapFrom(B b);

    MatchDto mapToExport(A updatedMatch);

}
