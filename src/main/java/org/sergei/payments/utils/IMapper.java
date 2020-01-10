package org.sergei.payments.utils;

import java.util.List;
import java.util.function.Function;

import com.google.common.collect.ImmutableList;

/**
 * Mapper from JPA entity into the DTO or vice versa
 *
 * @param <FROM> DTOs/Entities
 * @param <TO>   DTOs/Entities
 * @author Sergei Visotsky
 */
public interface IMapper<FROM, TO> extends Function<FROM, TO> {

    /**
     * onvert the whole list of DTOs/entities
     *
     * @param fromList list of DTOs/entities
     * @return converted list
     */
    default List<TO> applyList(List<FROM> fromList) {
        return fromList.stream().map(this).collect(ImmutableList.toImmutableList());
    }
}
