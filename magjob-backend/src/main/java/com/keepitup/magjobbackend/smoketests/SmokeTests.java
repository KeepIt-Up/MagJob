package com.keepitup.magjobbackend.smoketests;

import com.keepitup.magjobbackend.member.dto.PostMemberRequest;
import com.keepitup.magjobbackend.organization.dto.PostOrganizationRequest;
import com.keepitup.magjobbackend.user.dto.AuthenticationRequest;
import com.keepitup.magjobbackend.user.dto.PostUserRequest;
import com.keepitup.magjobbackend.user.dto.PutPasswordRequest;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;

@RestController
public class SmokeTests {
    private static final String USER_FIRSTNAME_TEST_VALUE = "testFirstName";
    private static final String USER_LASTNAME_TEST_VALUE = "testLastName";
    private static final String USER_EMAIL_TEST_VALUE = "test@magjob.test";
    private static final String USER_PASSWORD_TEST_VALUE = "password";
    private static final String USER_PASSWORD_UPDATED_TEST_VALUE = "passwordUpdate";

    private static final String USER_MEMBER_CREATION_EMAIL_TEST_VALUE = "testUserMember@magjob.test";

    private static final String ORGANIZATION_NAME_TEST_VALUE = "testOrganization";
    private static final String ORGANIZATION_PROFILE_BANNER_URL_TEST_VALUE = "https://magjob.com.default_profile_banner_url_valu";

    private static final String MEMBER_PSEUDONYM_TEST_VALUE = "testMember";



    private final RestTemplate restTemplate;

    private BigInteger testUserId;
    private String testUserPassword;
    private BigInteger testOrganizationId;
    private BigInteger testMemberId;

    private static final List<HttpStatus> successfulResponseCodes = Arrays.asList(
            HttpStatus.OK,
            HttpStatus.CREATED,
            HttpStatus.NO_CONTENT
    );
    private final String[] endpointsToCheck = {
            "/healthcheck/loginTest",
            "/healthcheck/getUsersTest",
            "/healthcheck/getUserTest",
            "/healthcheck/updateUserPasswordTest",
            "/healthcheck/createOrganizationTest",
            "/healthcheck/getOrganizationsTest",
            "/healthcheck/getOrganizationTest",
            "/healthcheck/getOrganizationsByUserTest",
            "/healthcheck/getMembersTest",
            "/healthcheck/getMembersByOrganizationTest",
            "/healthcheck/getMemberTest",
            "/healthcheck/createMemberTest"
    };

    @Autowired
    public SmokeTests(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    @GetMapping("/healthcheck/createUserTest")
    public ResponseEntity<String> createUserTest() throws JSONException {
        PostUserRequest postUserRequest = new PostUserRequest();
        postUserRequest.setEmail(USER_EMAIL_TEST_VALUE);
        postUserRequest.setFirstname(USER_FIRSTNAME_TEST_VALUE);
        postUserRequest.setLastname(USER_LASTNAME_TEST_VALUE);
        postUserRequest.setPassword(USER_PASSWORD_TEST_VALUE);

        testUserPassword = USER_PASSWORD_TEST_VALUE;

        ResponseEntity<String> response = restTemplate.postForEntity("/api/users", postUserRequest, String.class);
        String responseBody = response.getBody();
        JSONObject jsonObject = new JSONObject(responseBody);
        testUserId = "null".equals(jsonObject.getString("id")) ? null : new BigInteger(jsonObject.getString("id"));

        return response;
    }
    @GetMapping("/healthcheck/loginTest")
    public ResponseEntity<String> loginTest() {
        AuthenticationRequest authenticationRequest = new AuthenticationRequest();
        authenticationRequest.setEmail(USER_EMAIL_TEST_VALUE);
        authenticationRequest.setPassword(testUserPassword);

        return restTemplate.postForEntity("/api/users/login", authenticationRequest, String.class);
    }
    @GetMapping("/healthcheck/getUsersTest")
    public ResponseEntity<String> getUsersTest() throws JSONException {
        HttpEntity<String> entity = createHttpEntityWithAuthenticatedTestUserCredentials(USER_EMAIL_TEST_VALUE, testUserPassword);

        return restTemplate.exchange("/api/users", HttpMethod.GET, entity, String.class);
    }

    @GetMapping("/healthcheck/getUserTest")
    public ResponseEntity<String> getUserTest() throws JSONException {
        HttpEntity<String> entity = createHttpEntityWithAuthenticatedTestUserCredentials(USER_EMAIL_TEST_VALUE, testUserPassword);

        return restTemplate.exchange("/api/users/" + testUserId, HttpMethod.GET, entity, String.class);
    }

    //TODO (this template doesn't support PATCH Http method)
//    @GetMapping("/healthcheck/updateUserTest")
//    public ResponseEntity<String> updateUserTest() throws JSONException {
//        HttpEntity<String> entity = createHttpEntityWithAuthenticatedTestUserCredentials(USER_EMAIL_TEST_VALUE, testUserPassword);
//
//        PatchUserRequest patchUserRequest = new PatchUserRequest();
//        patchUserRequest.setFirstname("testFirstNameUpdate");
//
//        HttpEntity<PatchUserRequest> requestEntity = new HttpEntity<>(patchUserRequest, entity.getHeaders());
//
//        return restTemplate.exchange("/api/users/" + testUserId, HttpMethod.PATCH, requestEntity, String.class);
//    }

    @GetMapping("/healthcheck/updateUserPasswordTest")
    public ResponseEntity<String> updateUserPasswordTest() throws JSONException {
        HttpEntity<String> entity = createHttpEntityWithAuthenticatedTestUserCredentials(USER_EMAIL_TEST_VALUE, testUserPassword);

        PutPasswordRequest putPasswordRequest = new PutPasswordRequest();
        putPasswordRequest.setPassword(USER_PASSWORD_UPDATED_TEST_VALUE);

        testUserPassword = USER_PASSWORD_UPDATED_TEST_VALUE;

        HttpEntity<PutPasswordRequest> requestEntity = new HttpEntity<>(putPasswordRequest, entity.getHeaders());

        return restTemplate.exchange("/api/users/" + testUserId, HttpMethod.PUT, requestEntity, String.class);
    }

    @GetMapping("/healthcheck/createOrganizationTest")
    public ResponseEntity<String> createOrganizationTest() throws JSONException {
        HttpEntity<String> entity = createHttpEntityWithAuthenticatedTestUserCredentials(USER_EMAIL_TEST_VALUE, testUserPassword);

        PostOrganizationRequest postOrganizationRequest = new PostOrganizationRequest();
        postOrganizationRequest.setName(ORGANIZATION_NAME_TEST_VALUE);
        postOrganizationRequest.setProfileBannerUrl(ORGANIZATION_PROFILE_BANNER_URL_TEST_VALUE);
        postOrganizationRequest.setUser(testUserId);

        HttpEntity<PostOrganizationRequest> requestEntity = new HttpEntity<>(postOrganizationRequest, entity.getHeaders());

        ResponseEntity<String> response = restTemplate.exchange("/api/organizations", HttpMethod.POST, requestEntity, String.class);

        String responseBody = response.getBody();
        JSONObject jsonObject = new JSONObject(responseBody);
        testOrganizationId = "null".equals(jsonObject.getString("id")) ? null : new BigInteger(jsonObject.getString("id"));

        return response;
    }

    @GetMapping("/healthcheck/getOrganizationsTest")
    public ResponseEntity<String> getOrganizationsTest() throws JSONException {
        HttpEntity<String> entity = createHttpEntityWithAuthenticatedTestUserCredentials(USER_EMAIL_TEST_VALUE, testUserPassword);

        return restTemplate.exchange("/api/organizations", HttpMethod.GET, entity, String.class);
    }

    @GetMapping("/healthcheck/getOrganizationTest")
    public ResponseEntity<String> getOrganizationTest() throws JSONException {
        HttpEntity<String> entity = createHttpEntityWithAuthenticatedTestUserCredentials(USER_EMAIL_TEST_VALUE, testUserPassword);

        return restTemplate.exchange("/api/organizations/" + testOrganizationId, HttpMethod.GET, entity, String.class);
    }

    @GetMapping("/healthcheck/getOrganizationsByUserTest")
    public ResponseEntity<String> getOrganizationsByUserTest() throws JSONException {
        HttpEntity<String> entity = createHttpEntityWithAuthenticatedTestUserCredentials(USER_EMAIL_TEST_VALUE, testUserPassword);

        return restTemplate.exchange("/api/organizations/users/" + testUserId, HttpMethod.GET, entity, String.class);
    }

    @GetMapping("/healthcheck/getMembersTest")
    public ResponseEntity<String> getMembersTest() throws JSONException {
        HttpEntity<String> entity = createHttpEntityWithAuthenticatedTestUserCredentials(USER_EMAIL_TEST_VALUE, testUserPassword);

        return restTemplate.exchange("/api/members", HttpMethod.GET, entity, String.class);
    }

    @GetMapping("/healthcheck/getMemberTest")
    public ResponseEntity<String> getMemberTest() throws JSONException {
        HttpEntity<String> entity = createHttpEntityWithAuthenticatedTestUserCredentials(USER_EMAIL_TEST_VALUE, testUserPassword);

        return restTemplate.exchange("/api/members/" + testMemberId, HttpMethod.GET, entity, String.class);
    }

    @GetMapping("/healthcheck/getMembersByOrganizationTest")
    public ResponseEntity<String> getMembersByOrganizationTest() throws JSONException {
        HttpEntity<String> entity = createHttpEntityWithAuthenticatedTestUserCredentials(USER_EMAIL_TEST_VALUE, testUserPassword);

        ResponseEntity<String> response = restTemplate.exchange("/api/organizations/" + testOrganizationId + "/members", HttpMethod.GET, entity, String.class);

        String responseBody = response.getBody();
        JSONObject jsonObject = new JSONObject(responseBody);

        if (jsonObject.has("members")) {
            JSONArray membersArray = jsonObject.getJSONArray("members");

            if (membersArray.length() > 0) {
                JSONObject firstMember = membersArray.getJSONObject(0);
                testMemberId = firstMember.isNull("id") ? null : new BigInteger(firstMember.getString("id"));
            }
        }

        return response;
    }

    @GetMapping("/healthcheck/createMemberTest")
    public ResponseEntity<String> createMemberTest() throws JSONException {
        HttpEntity<String> entity = createHttpEntityWithAuthenticatedTestUserCredentials(USER_EMAIL_TEST_VALUE, testUserPassword);

        PostUserRequest postUserRequest = new PostUserRequest();
        postUserRequest.setEmail(USER_MEMBER_CREATION_EMAIL_TEST_VALUE);
        postUserRequest.setFirstname(USER_FIRSTNAME_TEST_VALUE);
        postUserRequest.setLastname(USER_LASTNAME_TEST_VALUE);
        postUserRequest.setPassword(USER_PASSWORD_TEST_VALUE);

        ResponseEntity<String> userResponse = restTemplate.postForEntity("/api/users", postUserRequest, String.class);
        String userResponseBody = userResponse.getBody();
        JSONObject userJsonObject = new JSONObject(userResponseBody);
        BigInteger userId = "null".equals(userJsonObject.getString("id")) ? null : new BigInteger(userJsonObject.getString("id"));

        PostMemberRequest postMemberRequest = new PostMemberRequest();
        postMemberRequest.setPseudonym(MEMBER_PSEUDONYM_TEST_VALUE);
        postMemberRequest.setOrganization(testOrganizationId);
        postMemberRequest.setUser(userId);

        HttpEntity<PostMemberRequest> requestEntity = new HttpEntity<>(postMemberRequest, entity.getHeaders());

        return restTemplate.exchange("/api/members", HttpMethod.POST, requestEntity, String.class);
    }

    //TODO
    //updateUserTest
    //deleteUserTest
    //updateOrganizationTest
    //deleteOrganizationTest
    //updateMemberTest
    //deleteMemberTest
    @GetMapping("/healthcheck")
    public ResponseEntity<String> healthCheck() throws JSONException {
        ResponseEntity<String> createUserResponse = createUserTest();
        if(!successfulResponseCodes.contains(createUserResponse.getStatusCode())) {
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                    .body("Endpoint: " + "/healthcheck/createUserTest" + " is not working properly");
        }

        HttpEntity<String> entity = createHttpEntityWithAuthenticatedTestUserCredentials(USER_EMAIL_TEST_VALUE, testUserPassword);
        for (String endpoint : endpointsToCheck) {
            ResponseEntity<String> response = restTemplate.exchange(endpoint, HttpMethod.GET, entity, String.class);
            if (!successfulResponseCodes.contains(response.getStatusCode())) {
                return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                        .body("Endpoint: " + endpoint + " is not working properly");
            }
        }
        return ResponseEntity.ok("All endpoints work properly");
    }

    private HttpEntity<String> createHttpEntityWithAuthenticatedTestUserCredentials(String email, String password) throws JSONException {
        AuthenticationRequest authenticationRequest = new AuthenticationRequest();
        authenticationRequest.setEmail(email);
        authenticationRequest.setPassword(password);

        ResponseEntity<String> response = restTemplate.postForEntity("/api/users/login", authenticationRequest, String.class);
        String responseBody = response.getBody();
        JSONObject jsonObject = new JSONObject(responseBody);

        String jwt =  jsonObject.getString("jwt");

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + jwt);
        headers.setContentType(MediaType.APPLICATION_JSON);

        return new HttpEntity<>(headers);
    }
}
