package com.chrias.otelextensions;

import java.util.logging.Logger;

import io.opentelemetry.context.Context;
import io.opentelemetry.sdk.common.CompletableResultCode;
import io.opentelemetry.sdk.trace.ReadWriteSpan;
import io.opentelemetry.sdk.trace.ReadableSpan;
import io.opentelemetry.sdk.trace.SpanProcessor;

/**
 * See <a href=
 * "https://github.com/open-telemetry/opentelemetry-specification/blob/master/specification/trace/sdk.md#span-processor">
 * OpenTelemetry Specification</a> for more information about
 * {@link SpanProcessor}.
 *
 * @see DemoSdkTracerProviderConfigurer
 */
public class KubeMetadataTaggingSpanProcessor implements SpanProcessor {

  Logger log = Logger.getLogger(KubeMetadataTaggingSpanProcessor.class.getCanonicalName());

  @Override
  public void onStart(Context parentContext, ReadWriteSpan span) {
    String k8sNamespace = System.getenv("K8S_NAMESPACE");
    log.fine("Add custom attribute from sys property: " + k8sNamespace);
    span.setAttribute("namespace", k8sNamespace);
  }

  @Override
  public boolean isStartRequired() {
    return true;
  }

  @Override
  public void onEnd(ReadableSpan span) {
  }

  @Override
  public boolean isEndRequired() {
    return false;
  }

  @Override
  public CompletableResultCode shutdown() {
    return CompletableResultCode.ofSuccess();
  }

  @Override
  public CompletableResultCode forceFlush() {
    return CompletableResultCode.ofSuccess();
  }
}
