package com.rest.api.service;

import com.rest.api.request.SendMessageRequest;

public interface TwillioMessagingService {
	String sendMesssage(SendMessageRequest sendMessageRequest);
}
