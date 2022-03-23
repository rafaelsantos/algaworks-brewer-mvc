package com.github.rafaelsantos.brewer.repository.helper.type;

import com.github.rafaelsantos.brewer.model.Type;
import com.github.rafaelsantos.brewer.repository.filter.TypeFilter;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TypeQueries {
    public Page<Type> filter(TypeFilter filter, Pageable pageable);
}
