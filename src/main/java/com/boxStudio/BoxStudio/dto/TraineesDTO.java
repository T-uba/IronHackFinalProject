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
public class TraineesDTO {

    @JsonProperty("firstName")
    private String firstName;
    @JsonProperty("lastName")
    private String lastName;
    @JsonProperty("email")
    private String email;
    @JsonProperty("password")
    private String password;

    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("firstName")
    public String getFirstName() { return firstName; }

    @JsonProperty("firstName")
    public void setFirstName(String firstName) { this.firstName = firstName; }

    @JsonProperty("lastName")
    public String getLastName() { return lastName; }

    @JsonProperty("lastName")
    public void setLastName(String lastName) { this.lastName = lastName; }

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

