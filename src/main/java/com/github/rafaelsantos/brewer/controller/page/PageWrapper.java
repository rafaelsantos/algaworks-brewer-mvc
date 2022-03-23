package com.github.rafaelsantos.brewer.controller.page;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;

import org.springframework.web.util.UriComponentsBuilder;

public class PageWrapper<T> {
    
    private Page<T> page;
    private UriComponentsBuilder uri;

    public PageWrapper(Page<T> page, HttpServletRequest request) {
        String url = request.getRequestURL().append(
            request.getQueryString() != null ? String.format("?%s", request.getQueryString()) : "")
            .toString().replaceAll("\\+", "%20");

        this.page = page;
        this.uri = UriComponentsBuilder.fromHttpUrl(url);
    }

    public List<T> getContent() {
        return page.getContent();
    }

    public boolean isEmpty() {
        return page.getContent().isEmpty();
    }

    public int getCurrent() {
        return page.getNumber();
    }

    public boolean isFirst() {
        return page.isFirst();
    }

    public boolean isLast() {
        return page.isLast();
    }

    public int getTotal() {
        return page.getTotalPages();
    }

    public String urlToPage(int page) {
        return uri.replaceQueryParam("page", page).build(true).encode().toUriString();
    }

    public String sort(String field) {
        UriComponentsBuilder uriSort = UriComponentsBuilder.fromUriString(uri.build(true).encode().toUriString());

        String sort = String.format("%s,%s", field, invert(field));
        return uriSort.replaceQueryParam("sort", sort).build(true).encode().toUriString();
    }

    public String invert(String field) {
        String direction = "asc";

        Order order = page.getSort() != null ? page.getSort().getOrderFor(field) : null;

        if (order != null)
            direction = Sort.Direction.ASC.equals(order.getDirection()) ? "desc" : "asc";

        return direction;
    }

    public boolean desc(String field) {
		return invert(field).equals("asc");
	}
	
	public boolean sorted(String field) {
		Order order = page.getSort() != null ? page.getSort().getOrderFor(field) : null; 
		
		if (order == null)
			return false;
		
		return page.getSort().getOrderFor(field) != null ? true : false;
	}
}
