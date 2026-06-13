package com.boxStudio.BoxStudio.dto;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.processing.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("jsonschema2pojo")
public class BookingDTO {

    @JsonProperty("id")
    private Long id;
    @JsonProperty("traineeId")
    private Long traineeId;
    @JsonProperty("fightingAreaIds")
    private List<Long> fightingAreaIds = new ArrayList<Long>();
    @JsonProperty("startTime")
    private String startTime;
    @JsonProperty("endTime")
    private String endTime;

    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("id")
    public Long getId() { return id; }

    @JsonProperty("id")
    public void setId(Long id) { this.id = id; }

    @JsonProperty("traineeId")
    public Long getTraineeId() { return traineeId; }

    @JsonProperty("traineeId")
    public void setTraineeId(Long traineeId) { this.traineeId = traineeId; }

    @JsonProperty("fightingAreaIds")
    public List<Long> getFightingAreaIds() { return fightingAreaIds; }

    @JsonProperty("fightingAreaIds")
    public void setFightingAreaIds(List<Long> fightingAreaIds) { this.fightingAreaIds = fightingAreaIds; }

    @JsonProperty("startTime")
    public String getStartTime() { return startTime; }

    @JsonProperty("startTime")
    public void setStartTime(String startTime) { this.startTime = startTime; }

    @JsonProperty("endTime")
    public String getEndTime() { return endTime; }

    @JsonProperty("endTime")
    public void setEndTime(String endTime) { this.endTime = endTime; }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() { return this.additionalProperties; }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) { this.additionalProperties.put(name, value); }
}

