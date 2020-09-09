package com.nikhil.phase3.dto;

import org.springframework.data.annotation.Id;

public class SectorDto {
    @Id
    private String id;
    private String sectorName;
    private String brief;

    public SectorDto(String sectorName, String brief) {
        //this.id = id;
        this.sectorName = sectorName;
        this.brief = brief;
    }

    public SectorDto() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSectorName() {
        return sectorName;
    }

    public void setSectorName(String sectorName) {
        this.sectorName = sectorName;
    }

    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }
}
