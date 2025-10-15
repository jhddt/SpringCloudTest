package com.baorant.auth_server.config;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.settings.ClientSettings;
import org.springframework.security.oauth2.server.authorization.settings.TokenSettings;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Component
public class CustomRegisteredClientRepository implements RegisteredClientRepository {

    private final JdbcTemplate jdbcTemplate;

    public CustomRegisteredClientRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void save(RegisteredClient registeredClient) {
        // 实现保存逻辑（如果需要）
    }

    @Override
    public RegisteredClient findById(String id) {
        return findByClientId(id);
    }

    @Override
    public RegisteredClient findByClientId(String clientId) {
        try {
            String sql = "SELECT * FROM oauth_client_details WHERE client_id = ?";
            return jdbcTemplate.queryForObject(sql, new Object[]{clientId}, (rs, rowNum) -> {
                RegisteredClient.Builder builder = RegisteredClient.withId(clientId)
                        .clientId(rs.getString("client_id"))
                        .clientSecret(rs.getString("client_secret"))
                        .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC);

                String grantTypes = rs.getString("authorized_grant_types");
                if (grantTypes != null) {
                    String[] types = grantTypes.split(",");
                    for (String type : types) {
                        type = type.trim();
                        if ("authorization_code".equals(type)) {
                            builder.authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE);
                        } else if ("client_credentials".equals(type)) {
                            builder.authorizationGrantType(AuthorizationGrantType.CLIENT_CREDENTIALS);
                        } else if ("refresh_token".equals(type)) {
                            builder.authorizationGrantType(AuthorizationGrantType.REFRESH_TOKEN);
                        }
                    }
                }

                String redirectUri = rs.getString("web_server_redirect_uri");
                if (redirectUri != null && !redirectUri.trim().isEmpty()) {
                    builder.redirectUri(redirectUri);
                }

                String scope = rs.getString("scope");
                if (scope != null) {
                    String[] scopes = scope.split(",");
                    for (String s : scopes) {
                        builder.scope(s.trim());
                    }
                }

                builder.clientSettings(ClientSettings.builder()
                        .requireAuthorizationConsent(false)
                        .build());

                Integer accessTokenValidity = rs.getInt("access_token_validity");
                Integer refreshTokenValidity = rs.getInt("refresh_token_validity");
                
                TokenSettings.Builder tokenSettingsBuilder = TokenSettings.builder();
                if (accessTokenValidity != null && accessTokenValidity > 0) {
                    tokenSettingsBuilder.accessTokenTimeToLive(Duration.ofSeconds(accessTokenValidity));
                } else {
                    tokenSettingsBuilder.accessTokenTimeToLive(Duration.ofHours(1));
                }
                
                if (refreshTokenValidity != null && refreshTokenValidity > 0) {
                    tokenSettingsBuilder.refreshTokenTimeToLive(Duration.ofSeconds(refreshTokenValidity));
                } else {
                    tokenSettingsBuilder.refreshTokenTimeToLive(Duration.ofHours(24));
                }
                
                builder.tokenSettings(tokenSettingsBuilder.build());

                return builder.build();
            });
        } catch (Exception e) {
            return null;
        }
    }
}
