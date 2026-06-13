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
public class FightingAreaDTO {

    @JsonProperty("id")
    private Long id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("capacity")
    private Integer capacity;

    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("id")
    public Long getId() { return id; }

    @JsonProperty("id")
    public void setId(Long id) { this.id = id; }

    @JsonProperty("name")
    public String getName() { return name; }

    @JsonProperty("name")
    public void setName(String name) { this.name = name; }

    @JsonProperty("capacity")
    public Integer getCapacity() { return capacity; }

    @JsonProperty("capacity")
    public void setCapacity(Integer capacity) { this.capacity = capacity; }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() { return this.additionalProperties; }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) { this.additionalProperties.put(name, value); }
}

