package com.github.rafaelsantos.brewer.thymeleaf;

import java.util.HashSet;
import java.util.Set;

import org.thymeleaf.dialect.AbstractProcessorDialect;
import org.thymeleaf.processor.IProcessor;
import org.thymeleaf.standard.StandardDialect;

import com.github.rafaelsantos.brewer.thymeleaf.processor.ErrorClassAttributeTagProcessor;
import com.github.rafaelsantos.brewer.thymeleaf.processor.MessageElementTagProcessor;
import com.github.rafaelsantos.brewer.thymeleaf.processor.PaginationElementTagProcessor;
import com.github.rafaelsantos.brewer.thymeleaf.processor.SortElementTagProcessor;

public class BrewerDialect extends AbstractProcessorDialect {

	public BrewerDialect() {
		super("Brewer", "brewer", StandardDialect.PROCESSOR_PRECEDENCE);
	}

	@Override
	public Set<IProcessor> getProcessors(String dialectPrefix) {
		final Set<IProcessor> processors = new HashSet<>();
		
		processors.add(new ErrorClassAttributeTagProcessor(dialectPrefix));
		processors.add(new MessageElementTagProcessor(dialectPrefix));
		processors.add(new SortElementTagProcessor(dialectPrefix));
		processors.add(new PaginationElementTagProcessor(dialectPrefix));
		
		return processors;
	}

}
