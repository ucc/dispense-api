package com.ucc.dispense.api;

import com.ucc.dispense.auth.UccUser;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class DispenseController {

	@GetMapping("/doorOnly")
	@PreAuthorize("hasAuthority('DOOR')")
	public String doorOnly(UccUser user) {
		return "Hi :3";
	}

}
