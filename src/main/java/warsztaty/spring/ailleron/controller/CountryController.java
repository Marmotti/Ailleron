package warsztaty.spring.ailleron.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import warsztaty.spring.ailleron.model.RestResponse;
import warsztaty.spring.ailleron.service.CountryService;

@RestController
public class CountryController {

    @Autowired
    CountryService countryService;

    @GetMapping("/country/{code}")
    public RestResponse getCountryByCode(@PathVariable String code){
        return countryService.getCountryFromRestApi(code);
    }
}
