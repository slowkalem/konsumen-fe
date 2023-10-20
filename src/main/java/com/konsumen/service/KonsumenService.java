package com.konsumen.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.konsumen.model.Konsumen;

@Service
public class KonsumenService {
	@Value("${api.url}")
	private String apiUrl;

	private RestTemplate restTemplate;

	@Autowired
	public void ApiService(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	public List<Konsumen> getKonsumen() {
		ResponseEntity<Konsumen[]> response = restTemplate.exchange(apiUrl + "/konsumen", HttpMethod.GET, null,
				Konsumen[].class);
		return Arrays.asList(response.getBody());
	}

	public String saveKonsumen(Konsumen konsumen) {
		HttpEntity<Konsumen> request = new HttpEntity<Konsumen>(konsumen);
		String response = restTemplate.postForObject(apiUrl + "/konsumen", request, String.class);

		return response;
	}

	public Konsumen getKonsumenById(int id) {
		ResponseEntity<Konsumen> response = restTemplate.exchange(apiUrl + "/konsumen/" + id,
				HttpMethod.GET, null, Konsumen.class);
		return response.getBody();
	}

	public ResponseEntity<String> updateKonsumen(Konsumen konsumen) {
		HttpEntity<Konsumen> request = new HttpEntity<Konsumen>(konsumen);
		ResponseEntity<String> response = restTemplate.exchange(apiUrl + "/konsumen/" + konsumen.getId(),
				HttpMethod.PUT, request, String.class);
		return response;
	}

	public ResponseEntity<String> deleteKonsumen(int id) {
		ResponseEntity<String> response = restTemplate.exchange(apiUrl + "/konsumen/" + id,
				HttpMethod.DELETE, null, String.class);
		return response;
	}
}
