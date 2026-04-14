# API Endpoints
Endpoints are defined in DispenseController.

Each endpoint is a method with one or more annotations. The annotations are used by Spring to define the path to the endpoint, the HTTP verb used to access it, and any authentication requirements.

Here's an example:
```	java
@GetMapping("/doorOnly") // This endpoint is accessed by a GET on /doorOnly
@PreAuthorize("hasAuthority('DOOR')") // This endpoint can only be accessed by the door usergroup
public String doorOnly(UccUser user) { // All endpoints get an UccUser input by default, which contains information on the user.
    return "Hi :3";
}
```

You can return almost anything out of the endpoint method. It's automatically turned into JSON by Jackson.

# Authentication
Authentication is handled by JWT tokens from Keycloak. These are basically signed blobs which contain a bunch of information about a user.

For development, you can get a token by using `tools/get_token`. You then pass it as a Bearer token when making your requests.

Security configuration is in SecurityConfig. All endpoints require a token except for health checks.

Each token is automatically mapped into an `UccUser` which then gets passed to every endpoint by default.

# Developer Environment
Dependencies for `dispense-api` are individual docker containers stored in `docker/`.

A compose file in the root starts them up.
To run everything:
```sh
cd dispense-api/
docker compose up --build
```

## IDE
I like to use IntelliJ. Open up the `pom.xml` inside the repo and you're off to the races :)