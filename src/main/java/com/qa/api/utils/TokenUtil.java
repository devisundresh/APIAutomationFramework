package com.qa.api.utils;

import com.qa.api.manager.ConfigManager;

public class TokenUtil {
	
	private String tokenId;
	public void setUpToken() {
		tokenId = "0b1f4149f71eb8d41f178514f81a5d2feb067ee39d577899c6f0603b4a2d73a6";
		ConfigManager.set("bearertoken", tokenId);
	}

}
