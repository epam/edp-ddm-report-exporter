{{- define "reportExporter.istioResources" -}}
{{- if .Values.global.registry.reportExporter.istio.sidecar.resources.limits.cpu }}
sidecar.istio.io/proxyCPULimit: {{ .Values.global.registry.reportExporter.istio.sidecar.resources.limits.cpu | quote }}
{{- else if and (not .Values.global.registry.reportExporter.istio.sidecar.resources.limits.cpu) .Values.global.istio.sidecar.resources.limits.cpu }}
sidecar.istio.io/proxyCPULimit: {{ .Values.global.istio.sidecar.resources.limits.cpu | quote }}
{{- end }}
{{- if .Values.global.registry.reportExporter.istio.sidecar.resources.limits.memory }}
sidecar.istio.io/proxyMemoryLimit: {{ .Values.global.registry.reportExporter.istio.sidecar.resources.limits.memory | quote }}
{{- else if and (not .Values.global.registry.reportExporter.istio.sidecar.resources.limits.memory) .Values.global.istio.sidecar.resources.limits.memory }}
sidecar.istio.io/proxyMemoryLimit: {{ .Values.global.istio.sidecar.resources.limits.memory | quote }}
{{- end }}
{{- if .Values.global.registry.reportExporter.istio.sidecar.resources.requests.cpu }}
sidecar.istio.io/proxyCPU: {{ .Values.global.registry.reportExporter.istio.sidecar.resources.requests.cpu | quote }}
{{- else if and (not .Values.global.registry.reportExporter.istio.sidecar.resources.requests.cpu) .Values.global.istio.sidecar.resources.requests.cpu }}
sidecar.istio.io/proxyCPU: {{ .Values.global.istio.sidecar.resources.requests.cpu | quote }}
{{- end }}
{{- if .Values.global.registry.reportExporter.istio.sidecar.resources.requests.memory }}
sidecar.istio.io/proxyMemory: {{ .Values.global.registry.reportExporter.istio.sidecar.resources.requests.memory | quote }}
{{- else if and (not .Values.global.registry.reportExporter.istio.sidecar.resources.requests.memory) .Values.global.istio.sidecar.resources.requests.memory }}
sidecar.istio.io/proxyMemory: {{ .Values.global.istio.sidecar.resources.requests.memory | quote }}
{{- end }}
{{- end -}}
