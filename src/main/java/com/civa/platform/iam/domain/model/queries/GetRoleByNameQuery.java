package com.civa.platform.iam.domain.model.queries;

import com.civa.platform.iam.domain.model.valueobjects.Roles;

public record GetRoleByNameQuery(Roles name) {
}
