package org.socialsignin.provider.exfm;

import org.socialsignin.provider.AbstractProviderService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.social.exfm.api.ExFm;
import org.springframework.social.exfm.api.impl.ExFmTemplate;
import org.springframework.stereotype.Service;

@Service
public class ExFmProviderService extends AbstractProviderService<ExFm> {

	@Value("${exfm.oauthApiBaseUrl}")
	private String exfmOAuthApiBaseUrl;
	
	@Override
	public Class<ExFm> getApiClass() {
		return ExFm.class;
	}

	@Override
	public ExFm getUnauthenticatedApi() {
		return new ExFmTemplate(exfmOAuthApiBaseUrl);
	}

}
