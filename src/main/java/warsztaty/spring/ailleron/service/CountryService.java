package warsztaty.spring.ailleron.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import warsztaty.spring.ailleron.model.RestResponse;
import warsztaty.spring.ailleron.model.RootObject;

import java.util.Locale;

@Service
public class CountryService {

    private final static String COUNTRY_CODE_URL = "http://www.groupkt.com/country/get/iso2code/";

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private MessageSource messageSource;

    public RestResponse getCountryFromRestApi(String code){
        ResponseEntity<RootObject> rootResponse = getDataFromApi(code);
        if(rootResponse.getStatusCode().is2xxSuccessful()){
            RestResponse response = rootResponse.getBody().getRestResponse();
            response.setSayHello(messageSource.getMessage("hello.message", null, Locale.forLanguageTag(code)));
            return response;
        }
        return null;
    }

    private ResponseEntity<RootObject> getDataFromApi(String code) {
        return restTemplate.getForEntity(UriComponentsBuilder.fromHttpUrl(COUNTRY_CODE_URL + code).build().toUri(), RootObject.class);
    }
}
