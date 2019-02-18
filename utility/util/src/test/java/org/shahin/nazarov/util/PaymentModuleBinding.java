package org.shahin.nazarov.util;

import com.google.inject.AbstractModule;

public class PaymentModuleBinding extends AbstractModule {
    @Override
    protected void configure() {
        bind(PaymentProcessor.class).to(PayPalPaymentProcessor.class);
    }
}
