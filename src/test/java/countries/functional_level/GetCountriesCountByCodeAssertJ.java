package countries.functional_level;

import countries.dto.CountryDto;
import countries.service.RestCountriesService;
import org.assertj.core.api.Condition;
import org.hamcrest.Matcher;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.anyOf;
import static org.assertj.core.api.Assertions.assertThat;

public class GetCountriesCountByCodeAssertJ {

    @Test(description = "Get country by one Code")
    void getCountryByTwoCode2() {
        String countryCode = "rus;nor";

        CountryDto[] countries = new RestCountriesService()
                .getCountryByCode(countryCode);

        assertThat(10)
                .isLessThan(13)
                .isGreaterThan(12);

        org.assertj.core.api.Condition<CountryDto> russia = new Condition<>(
                m -> m.getName().equals("Russia"), "name equal Russia");
        org.assertj.core.api.Assertions.assertThat(countries[0]).is(russia);

        assertThat(countries)
                .as("what is happened there")
                .isEmpty();

    }

}
