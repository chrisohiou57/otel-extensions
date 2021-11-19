package com.chrias.otelextensions;

import com.google.auto.service.AutoService;
import io.opentelemetry.api.common.Attributes;
import io.opentelemetry.sdk.autoconfigure.spi.ConfigProperties;
import io.opentelemetry.sdk.autoconfigure.spi.ResourceProvider;
import io.opentelemetry.sdk.resources.Resource;

@AutoService(ResourceProvider.class)
public class KubeResourceProvider implements ResourceProvider {

    @Override
    public Resource createResource(ConfigProperties config) {
        Attributes attributes = Attributes.builder().put("custom.resource", "customResourceValue").build();
        return Resource.create(attributes);
    }
    
}
