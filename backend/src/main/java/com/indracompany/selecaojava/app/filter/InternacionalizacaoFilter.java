package com.indracompany.selecaojava.app.filter;

import java.io.IOException;
import java.util.Locale;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author 	Felipe Rudolfe Carvalho Pinheiro
 * @since   20 de abr de 2020
 */
@Component
@Order(1)
public class InternacionalizacaoFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;

		String locale = req.getHeader("Accept-Language");
		if (locale != null) {
			String [] lang = locale.split("-");
			Locale.setDefault((lang.length == 2) ? new Locale(lang[0], lang[1]) : new Locale(lang[0]));
		}

        chain.doFilter(request, response);
	}

}