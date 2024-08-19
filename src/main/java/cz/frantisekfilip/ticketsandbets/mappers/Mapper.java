package cz.frantisekfilip.ticketsandbets.mappers;

import cz.frantisekfilip.ticketsandbets.domain.dto.MatchDto;

public interface Mapper<A,B> {
    public B mapTo(A a);

    public A mapFrom(B b);

}
