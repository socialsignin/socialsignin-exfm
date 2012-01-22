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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.social.connect.ConnectionFactory;
import org.springframework.social.connect.web.ConnectInterceptor;
import org.springframework.social.exfm.api.ExFm;
import org.springframework.social.exfm.connect.ExFmConnectionFactory;

/** 
* @author Michael Lavelle
*/
@Configuration
public class ExFmProviderConfig extends AbstractProviderConfig<ExFm> {

	@Autowired
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
	

	@Override
	protected ConnectionFactory<ExFm> createConnectionFactory() {
		return new ExFmConnectionFactory(exfmConsumerKey,
				exfmConsumerSecret,exfmOAuthAuthorizeUrl,exfmOAuthTokenUrl,exfmOAuthApiBaseUrl);
	}

	@Override
	protected ConnectInterceptor<ExFm> getConnectInterceptor() {
		return exfmConnectInterceptor;
	}

}
