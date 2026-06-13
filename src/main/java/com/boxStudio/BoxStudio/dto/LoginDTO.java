package com.boxStudio.BoxStudio.dto;

import java.util.LinkedHashMap;
import java.util.Map;
import javax.annotation.processing.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("jsonschema2pojo")
public class LoginDTO {

    @JsonProperty("email")
    private String email;
    @JsonProperty("password")
    private String password;

    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("email")
    public String getEmail() { return email; }

    @JsonProperty("email")
    public void setEmail(String email) { this.email = email; }

    @JsonProperty("password")
    public String getPassword() { return password; }

    @JsonProperty("password")
    public void setPassword(String password) { this.password = password; }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() { return this.additionalProperties; }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) { this.additionalProperties.put(name, value); }
}


