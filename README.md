A custom SpanProcessor and ResourceProvider. The custom SpanProcessor adds Kubernetes metadata tags to traces. It depends on environment variables for the K8s metadata. This can be achieved by using the Downward API to expose K8s metadata to the pod that is instrumented with Otel. The custom ResourceProvider could do the same, except the metadata is added as a resource attribute instead of a tag. For now, just demonstrating that a custom resource can be added.

I did this because I couldn't find a way to add these by default using standard Otel libraries. However, I was sending traces directly to Tempo. Maybe this could be achieved if I were using an Otel collector or the Otel Operator?

The was derived from the example below. I could only get it working when I embedded the extensions in the agent JAR.
https://github.com/open-telemetry/opentelemetry-java-instrumentation/tree/main/examples/extension