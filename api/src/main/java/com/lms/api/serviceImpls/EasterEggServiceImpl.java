package com.lms.api.serviceImpls;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.lms.api.exceptions.NumberApiFetchException;
import com.lms.api.services.EasterEggService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EasterEggServiceImpl implements EasterEggService{
	public final RestTemplate restTemplate;
	public static final String URL = "http://numbersapi.com/random/trivia";

	@Override
	public String getRandomNumberFact() {
		try {
            return restTemplate.getForObject(URL, String.class);
        } catch (Exception e) {
           throw new NumberApiFetchException("Failed to fetch number fact");
        }
	}
}
