package com.solstice;

import org.junit.jupiter.api.extension.*;


/**
 * Created by Mike Koleno on 5/30/17.
 */
public class CustomerResolver implements ParameterResolver {
    @Override
    public boolean supports(final ParameterContext parameterContext, final ExtensionContext extensionContext) throws ParameterResolutionException {
        return parameterContext.getParameter().getType().isAssignableFrom(Customer.class);
    }

    @Override
    public Object resolve(final ParameterContext parameterContext, final ExtensionContext extensionContext) throws ParameterResolutionException {
        Customer customer = new Customer("1", "jbaumgartner", "pass1234", "Justin");
        return customer;
    }

}
