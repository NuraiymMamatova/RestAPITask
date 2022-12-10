package com.peaksoft.project_on_restapi.model.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum StudyFormat {

        ONLINE, OFFLINE

//    ONLINE("online"),
//    OFFLINE("offline");
//
//    private String studyFormat;
//
//    StudyFormat(String studyFormat) {
//        this.studyFormat = studyFormat;
//    }
//
//    public String getStudyFormat() {
//        return this.studyFormat;
//    }
//
//    @JsonCreator
//    public static StudyFormat getStudyFormatFromCode(String value) {
//        for (StudyFormat format :  StudyFormat.values()) {
//            if (format.getStudyFormat().equals(value)) {
//                return format;
//            }
//        }
//        return null;
//    }
}
