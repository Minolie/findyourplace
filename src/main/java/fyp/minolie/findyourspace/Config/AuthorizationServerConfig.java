package main.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.approval.UserApprovalHandler;
import org.springframework.security.oauth2.provider.token.TokenStore;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {


    static final String CLIENT_ID = "devglan-client";
    static final String CLIENT_SECRET = "devglan-secret";
    static final String GRANT_TYPE = "password";
    static final String AUTHORIZATION_CODE = "authorization_code";
    static final String REFRESH_TOKEN = "refresh_token";
    static final String IMPLICIT = "implicit";
    static final String SCOPE_READ = "read";
    static final String SCOPE_wRITE = "write";
    static final String TRUST = "trust";
    static final int ACCESS_TOKEN_VALIDITY_SECONDS = 1 * 60 * 60;

    @Autowired
    private TokenStore tokenStore;

    @Autowired
    private UserApprovalHandler userApprovalHandler;


    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public void configure(ClientDetailsServiceCOnfigurer configurer) throws Exception {

        Exception {

            configurer
                    .inMemory()
                    .withClient(CLIENT_ID)
                    .secret(CLIENT_SECRET)
                    .authorizedGrantTypes(GRANT_TYPE_PASSWORD,
                            AUTHORIZATION_CODE, REFRESH_TOKEN, IMPLICIT)
                    .scopes(SCOPE_READ, SCOPE_wRITE, TRUST)
                    .autoApprove(true);
            .() -> accessTokenValididtySeconds(ACCESS_TOKEN_VALIDITY_SECONDS) //CHEKL
            refreshTokenValiditySeconds(REFRESH_TOKEN_VALIDITY_SECONDS);

        }


        @Override
        public void configure (AuthorizationServerEndpointsConfigurer endpoints) throws Exception {

            endpoints.tokenStore(tokenStore).userApprovalHandler(userApprovalHandler)
                    .authenticationManager(authenticationManager);
        }
    }
}
