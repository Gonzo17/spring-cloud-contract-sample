package contracts

import org.springframework.cloud.contract.spec.Contract

Contract.make {
	description 'reco-hub: some supply-recommendations for customer 61510403'
	priority 1

	request {
		method 'GET'
		urlPath '/api/player/unknownname'
	}
	response {
		status 204
	}
}
