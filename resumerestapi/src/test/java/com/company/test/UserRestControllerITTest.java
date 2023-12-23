package com.company.test;

import com.company.ResumerestapiApplication;
import com.company.dto.ResponseDTO;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.test.OAuth2ContextConfiguration;
import org.springframework.security.oauth2.client.test.OAuth2ContextSetup;
import org.springframework.security.oauth2.client.test.RestTemplateHolder;
import org.springframework.security.oauth2.client.token.grant.password.ResourceOwnerPasswordResourceDetails;
import org.springframework.security.oauth2.common.AuthenticationScheme;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestOperations;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {ResumerestapiApplication.class}, webEnvironment = WebEnvironment.DEFINED_PORT)
@OAuth2ContextConfiguration(UserRestControllerITTest.MyDetails.class)
public class UserRestControllerITTest implements RestTemplateHolder {

    @LocalServerPort
    private int port;

    public int getPort() {
        return port;
    }

    @Rule
    public OAuth2ContextSetup cotext = OAuth2ContextSetup.standard(this);

    RestOperations restTemplate; // to request to your own restapi ?

    @Test
    public void greetingShouldReturnDefaultMessage() throws Exception {
        String url = "http://localhost:" + port + "/users/1";
        System.out.println("url=" + url);
        ResponseDTO resp = this.restTemplate.getForObject(url, ResponseDTO.class);

        System.out.println("found=" + resp.getObj());
        LinkedHashMap<String, Object> hashMap = (LinkedHashMap) resp.getObj();

        Assert.assertEquals("must equal 1",1,hashMap.get("id"));
        System.out.println(resp);
    }

    @Override
    public void setRestTemplate(RestOperations restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public RestOperations getRestTemplate() {
        return restTemplate;
    }

    class MyDetails extends ResourceOwnerPasswordResourceDetails {
        public MyDetails(final Object obj) {
            UserRestControllerITTest it = (UserRestControllerITTest) obj;
            setAccessTokenUri("http://localhost:" + it.getPort() + "/oauth/token");
            setClientId("alma");
            setClientSecret("alma");

            List<String> scopes = new ArrayList<>();
            scopes.add("read");

            setScope(scopes);
            setUsername("azat@gmail.com");
            setPassword("12345");
            setClientAuthenticationScheme(AuthenticationScheme.header);

        }
    }
}
