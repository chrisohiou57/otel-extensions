package com.chrias.otelextensions;

import com.google.auto.service.AutoService;
import io.opentelemetry.sdk.autoconfigure.spi.ConfigProperties;
import io.opentelemetry.sdk.autoconfigure.spi.traces.SdkTracerProviderConfigurer;
import io.opentelemetry.sdk.trace.SdkTracerProviderBuilder;

/**
 * This is one of the main entry points for Instrumentation Agent's
 * customizations. It allows configuring {@link SdkTracerProviderBuilder}. See
 * the {@link #configure(SdkTracerProviderBuilder)} method below.
 *
 * <p>
 * Also see https://github.com/open-telemetry/opentelemetry-java/issues/2022
 *
 * @see SdkTracerProviderConfigurer
 * @see DemoPropagatorProvider
 */
@AutoService(SdkTracerProviderConfigurer.class)
public class CustomSdkTracerProviderConfigurer implements SdkTracerProviderConfigurer {
    @Override
    public void configure(SdkTracerProviderBuilder tracerProvider, ConfigProperties config) {
        tracerProvider.addSpanProcessor(new KubeMetadataTaggingSpanProcessor());
    }
}