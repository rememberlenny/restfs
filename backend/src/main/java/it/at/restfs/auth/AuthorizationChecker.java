package it.at.restfs.auth;

import java.util.Optional;
import java.util.UUID;

public interface AuthorizationChecker {	
	
	/*
	 * TODO if classes in construction method will be always the same ... make it ones !!?
	 */
	
	public enum Implementation {
		NO_AUTH("noAuth", NoAuthorization.class, NoAuthorization.class),
		OAUTH2("oAuth2", OAuth2.class, OAuth2.class),
		MASTER_PWD("masterPwd", MasterPassword.class, MasterPassword.class),
		BASIC_AUTH("basicAuth", BasicAuth.class, BasicAuth.class);
		
		public String k; 
		public Class<? extends AuthorizationChecker> c;
		public Class<? extends AuthorizationMaker> m;
		
		private Implementation(String k, Class<? extends AuthorizationChecker> c, Class<? extends AuthorizationMaker> m) {
			this.k = k;
			this.c = c;
			this.m = m;
		}
	}		
	
	public boolean isTokenValid(UUID container, Optional<String> authorization);

}
