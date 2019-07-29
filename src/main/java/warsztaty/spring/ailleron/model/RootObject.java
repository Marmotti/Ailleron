package warsztaty.spring.ailleron.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RootObject {
    @JsonProperty("RestResponse")
    private RestResponse restResponse;

    public RootObject(RestResponse restResponse) {
        this.restResponse = restResponse;
    }
    public RootObject(){}
    public RestResponse getRestResponse() {
        return restResponse;
    }
    }
