package com.ucc.dispense.auth;

import org.springframework.security.oauth2.jwt.Jwt;

import java.util.List;

/**
 * This class is a thin wrapper around the info we get from Keycloak
 */
public class UccUser {

	public final Jwt jwt;

	public final String username;

	public final String fullName;

	public final String firstName;

	public final String lastName;

	public final String email;

	public final List<String> groups;

	public UccUser(Jwt jwt) {
		this.jwt = jwt;
		this.username = claimOrThrow(jwt, "preferred_username");
		this.fullName = claimOrThrow(jwt, "name");
		this.firstName = claimOrThrow(jwt, "given_name");
		this.lastName = claimOrThrow(jwt, "family_name");
		this.email = claimOrThrow(jwt, "email");

		List<String> raw = jwt.getClaimAsStringList("usergroups");
		if (raw == null || raw.isEmpty()) {
			throw new IllegalArgumentException("JWT missing required claim: usergroups");
		}

		this.groups = List.copyOf(raw);
	}

	private static String claimOrThrow(Jwt jwt, String claim) {
		String value = jwt.getClaimAsString(claim);
		if (value == null || value.isBlank()) {
			throw new IllegalArgumentException("JWT missing required claim: " + claim);
		}
		return value;
	}

	public boolean isCommittee() {
		return groups.contains("committee");
	}

	public boolean isDoor() {
		return groups.contains("door");
	}

	public boolean isWheel() {
		return groups.contains("wheel");
	}

}
