package org.shahin.nazarov.jersey;

import io.swagger.v3.core.filter.OpenAPISpecFilter;
import io.swagger.v3.core.model.ApiDescription;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.Operation;
import io.swagger.v3.oas.models.PathItem;
import io.swagger.v3.oas.models.media.Schema;
import io.swagger.v3.oas.models.parameters.Parameter;
import io.swagger.v3.oas.models.parameters.RequestBody;
import io.swagger.v3.oas.models.responses.ApiResponse;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class SwaggerFilter implements OpenAPISpecFilter {

    private boolean filter(Map<String, List<String>> params){
        List<String> strings = params.get("x-swagger-key");
        if(strings != null && strings.contains("1")){
            return true;
        }
        return false;
    }

    @Override
    public Optional<OpenAPI> filterOpenAPI(OpenAPI openAPI, Map<String, List<String>> params, Map<String, String> cookies, Map<String, List<String>> headers) {
       return filter(params) ? Optional.of(openAPI) : Optional.empty();
    }

    @Override
    public Optional<PathItem> filterPathItem(PathItem pathItem, ApiDescription api, Map<String, List<String>> params, Map<String, String> cookies, Map<String, List<String>> headers) {
        return filter(params) ? Optional.of(pathItem) : Optional.empty();
    }

    @Override
    public Optional<Operation> filterOperation(Operation operation, ApiDescription api, Map<String, List<String>> params, Map<String, String> cookies, Map<String, List<String>> headers) {
        return filter(params) ? Optional.of(operation) : Optional.empty();
    }

    @Override
    public Optional<Parameter> filterParameter(Parameter parameter, Operation operation, ApiDescription api, Map<String, List<String>> params, Map<String, String> cookies, Map<String, List<String>> headers) {
        return filter(params) ? Optional.of(parameter) : Optional.empty();
    }

    @Override
    public Optional<RequestBody> filterRequestBody(RequestBody requestBody, Operation operation, ApiDescription api, Map<String, List<String>> params, Map<String, String> cookies, Map<String, List<String>> headers) {
        return filter(params) ? Optional.of(requestBody) : Optional.empty();
    }

    @Override
    public Optional<ApiResponse> filterResponse(ApiResponse response, Operation operation, ApiDescription api, Map<String, List<String>> params, Map<String, String> cookies, Map<String, List<String>> headers) {
        return filter(params) ? Optional.of(response) : Optional.empty();
    }

    @Override
    public Optional<Schema> filterSchema(Schema schema, Map<String, List<String>> params, Map<String, String> cookies, Map<String, List<String>> headers) {
        return filter(params) ? Optional.of(schema) : Optional.empty();
    }

    @Override
    public Optional<Schema> filterSchemaProperty(Schema property, Schema schema, String propName, Map<String, List<String>> params, Map<String, String> cookies, Map<String, List<String>> headers) {
        return filter(params) ? Optional.of(property) : Optional.empty();
    }

    @Override
    public boolean isRemovingUnreferencedDefinitions() {
        return false;
    }
}
