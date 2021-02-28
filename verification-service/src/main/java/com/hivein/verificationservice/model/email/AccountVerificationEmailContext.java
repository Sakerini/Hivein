package com.hivein.verificationservice.model.email;

import com.hivein.verificationservice.model.entity.SecureToken;
import org.springframework.web.util.UriComponentsBuilder;

public class AccountVerificationEmailContext extends AbstractEmailContext {
    private String token;

    @Override
    public <T> void init(T context) {
        //we can do any common configuration setup here
        // like setting up some base URL and context
        SecureToken userInformation = (SecureToken) context; // we pass the customer informati
        put("firstName", userInformation.getName());
        setTemplateLocation("emails/email-verification");
        setSubject("Complete your registration");
        setFrom("no-reply@hivein.com");
        setTo(userInformation.getEmail());
    }

    public void setToken(String token) {
        this.token = token;
        put("token", token);
    }

    public void buildVerificationUrl(final String baseURL, final String token) {
        final String url = UriComponentsBuilder.fromHttpUrl(baseURL)
                .path("/verify/email/" + token).toUriString();
        put("verificationURL", url);
    }
}
