/*
 * Copyright 2016-2021 the original author or authors.
 *
 * All rights reserved. This program and the accompanying materials are
 * made available under the terms of the Eclipse Public License v2.0 which
 * accompanies this distribution and is available at
 *
 * http://www.eclipse.org/legal/epl-v20.html
 */

package org.junitpioneer.playwright;

import static org.junitpioneer.internal.PioneerAnnotationUtils.isAnyAnnotationPresent;

import com.microsoft.playwright.Playwright;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.api.extension.ParameterResolver;

public class PlaywrightParameterResolver implements ParameterResolver {

	@Override
	public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext)
			throws ParameterResolutionException {
		return isAnyAnnotationPresent(extensionContext, PlaywrightTests.class)
				&& parameterContext.getParameter().getType() == Playwright.class;
	}

	@Override
	public Playwright resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext)
			throws ParameterResolutionException {
		Playwright playwright = Playwright.create();
		PlaywrightUtils.putIntoStore(extensionContext, playwright::close);
		return playwright;
	}

}
