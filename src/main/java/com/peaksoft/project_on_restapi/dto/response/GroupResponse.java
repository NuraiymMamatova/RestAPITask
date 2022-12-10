package com.peaksoft.project_on_restapi.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GroupResponse {

    private Long id;

    private String groupName;

    private Date dateOfStart;

    private String image;
}
