package com.memsource.assignment.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ProjectResponse {

    private int totalElements;
    private int totalPages;
    private int pageSize;
    private int pageNumber;
    private int numberOfElements;
    private List<ProjectResponseContent> content;
}

@AllArgsConstructor
@NoArgsConstructor
@Getter
class ProjectResponseContent {

    private String uid;
    private String id;
    private String name;
    private String dateCreated;
    private String sourceLang;
    private String userRole;
    private int internalId;
    private Domain domain;
    private Domain subDomain;
    private Owner owner;
    private List<String> targetLangs;
//    private List<String> references;
//    private List<String> mtSettingsPerLanguageList;
    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    private class Domain {
        private String name;
        private String id;
        private String uid;
    }
    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    private class Owner {
        private String firstName;
        private String lastName;
        private String userName;
        private String email;
        private String role;
        private String id;
        private String uid;
    }
}
