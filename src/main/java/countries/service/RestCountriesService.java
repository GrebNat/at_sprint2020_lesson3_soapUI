package countries.service;

import com.google.gson.Gson;
import countries.dto.CountryDto;

import java.util.HashMap;
import java.util.Map;

public class RestCountriesService {

    private RestCountriesService restCountriesService;

    public RestCountriesService getInstance() {
        if (restCountriesService == null)
            restCountriesService = new RestCountriesService();

        return restCountriesService;
    }

    public CountryDto[] getCountryByName(String name) {
        return
                new CommonService()
                        .getNoParams(String.format(URI.GET_BY_COUNTRY_NAME_URI, name))
                        .getBody().as(CountryDto[].class);
    }

    public CountryDto[] getCountryByCode(String code) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("codes", code);

        return
                new Gson().fromJson(
                        new CommonService()
                                .getWithParams(URI.GET_BY_COUNTRY_CODE_URI, params)
                                .getBody().asString(), CountryDto[].class);
    }
}
