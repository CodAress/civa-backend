package com.civa.platform.iam.interfaces.rest.transform;

import com.civa.platform.iam.domain.model.aggregates.User;
import com.civa.platform.iam.interfaces.rest.resources.AuthenticatedUserResource;

public class AuthenticatedUserResourceFromEntityAssembler {
    public static AuthenticatedUserResource toResourceFromEntity(User entity, String token) {
        return new AuthenticatedUserResource(entity.getId(), entity.getUsername(), token);
    }
}
