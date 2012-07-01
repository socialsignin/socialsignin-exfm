/*
 * Copyright 2011 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.socialsignin.provider.exfm;

import org.socialsignin.provider.AbstractProviderConfig;
import org.socialsignin.springsocial.security.ExFmConnectInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.social.connect.ConnectionFactory;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.support.ConnectionFactoryRegistry;
import org.springframework.social.connect.web.ConnectInterceptor;
import org.springframework.social.exfm.api.ExFm;
import org.springframework.social.exfm.api.impl.ExFmTemplate;
import org.springframework.social.exfm.connect.ExFmConnectionFactory;

/** 
* @author Michael Lavelle
*/
@Configuration
public class ExFmProviderConfig extends AbstractProviderConfig<ExFm> {

	@Autowired(required=false)
	private ExFmConnectInterceptor exfmConnectInterceptor;

	@Value("${exfm.consumerKey}")
	private String exfmConsumerKey;
	
	@Value("${exfm.consumerSecret}")
	private String exfmConsumerSecret;
	

	@Value("${exfm.oauthTokenUrl}")
	private String exfmOAuthTokenUrl;
	
	@Value("${exfm.oauthAuthorizeUrl}")
	private String exfmOAuthAuthorizeUrl;
	

	@Value("${exfm.oauthApiBaseUrl}")
	private String exfmOAuthApiBaseUrl;
	
	
	public ExFmProviderConfig() {
		super();
	}
	
	public ExFmProviderConfig(String exfmConsumerKey,
			ExFm authenticatedApi) {
		super(authenticatedApi);
		this.exfmConsumerKey = exfmConsumerKey;
	}
	
	/*
	 * When Ex.Fm implement an Oauth API, this consructor would be more useful, until then
	 * the we'll use a username/password constructor
	 * 
	public ExFmProviderConfig(String exfmOAuthApiBaseUrl,String exfmConsumerKey,String accessToken) {
		super(new ExFmTemplate(exfmOAuthApiBaseUrl,accessToken));
		this.exfmConsumerKey = exfmConsumerKey;
		this.exfmOAuthApiBaseUrl = exfmOAuthApiBaseUrl;
	}
	*/
	
	public ExFmProviderConfig(String exfmOAuthApiBaseUrl,String username,String password) {
		super(new ExFmTemplate(exfmOAuthApiBaseUrl,username,password));
		this.exfmOAuthApiBaseUrl = exfmOAuthApiBaseUrl;
	}
	
	
	public ExFmProviderConfig(String exfmOAuthApiBaseUrl,String exfmConsumerKey,String exfmConsumerSecret,ConnectionRepository connectionRepository,
			ConnectionFactoryRegistry connectionFactoryRegistry) {
		super(connectionRepository, connectionFactoryRegistry);
		this.exfmConsumerKey = exfmConsumerKey;
		this.exfmConsumerSecret  = exfmConsumerSecret;
		this.exfmOAuthApiBaseUrl = exfmOAuthApiBaseUrl;

	}

	public ExFmProviderConfig(String exfmOAuthApiBaseUrl,String exfmConsumerKey,String exfmConsumerSecret,ConnectionRepository connectionRepository,
			UsersConnectionRepository usersConnectionRepository,
			ConnectionFactoryRegistry connectionFactoryRegistry) {
		super(connectionRepository, usersConnectionRepository,
				connectionFactoryRegistry);
		this.exfmConsumerKey = exfmConsumerKey;
		this.exfmConsumerSecret  = exfmConsumerSecret;
		this.exfmOAuthApiBaseUrl = exfmOAuthApiBaseUrl;

	}
	
	public ExFmProviderConfig(String exfmOAuthApiBaseUrl,String exfmConsumerKey,String exfmConsumerSecret,String userId,	UsersConnectionRepository usersConnectionRepository,
			ConnectionFactoryRegistry connectionFactoryRegistry) {
		super(userId,usersConnectionRepository,
				connectionFactoryRegistry);
		this.exfmConsumerKey = exfmConsumerKey;
		this.exfmConsumerSecret  = exfmConsumerSecret;
		this.exfmOAuthApiBaseUrl = exfmOAuthApiBaseUrl;
	}

	public void setexfmConsumerKey(String exfmConsumerKey) {
		this.exfmConsumerKey = exfmConsumerKey;
	}

	public void setexfmConsumerSecret(String exfmConsumerSecret) {
		this.exfmConsumerSecret = exfmConsumerSecret;
	}

	public void setExfmOAuthTokenUrl(String exfmOAuthTokenUrl) {
		this.exfmOAuthTokenUrl = exfmOAuthTokenUrl;
	}

	public void setExfmOAuthAuthorizeUrl(String exfmOAuthAuthorizeUrl) {
		this.exfmOAuthAuthorizeUrl = exfmOAuthAuthorizeUrl;
	}

	public void setExfmOAuthApiBaseUrl(String exfmOAuthApiBaseUrl) {
		this.exfmOAuthApiBaseUrl = exfmOAuthApiBaseUrl;
	}

	@Override
	protected ConnectionFactory<ExFm> createConnectionFactory() {
		return new ExFmConnectionFactory(exfmConsumerKey,
				exfmConsumerSecret,exfmOAuthAuthorizeUrl,exfmOAuthTokenUrl,exfmOAuthApiBaseUrl);
	}

	@Override
	protected ConnectInterceptor<ExFm> getConnectInterceptor() {
		return exfmConnectInterceptor;
	}

	@Override
	public Class<ExFm> getApiClass() {
		return ExFm.class;
	}

}
