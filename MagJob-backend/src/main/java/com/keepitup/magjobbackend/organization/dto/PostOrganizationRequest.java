package com.keepitup.magjobbackend.organization.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class PostOrganizationRequest {

    private String name;
    private String profileBannerUrl;

}
