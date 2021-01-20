package countries.functional_level;

import countries.dto.CountryDto;
import countries.service.RestCountriesService;
import org.assertj.core.api.Condition;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class GetCountriesCountByCodeAssertJ {

    @Test(description = "Get country by one Code")
    void getCountryByTwoCode2() {
        String countryCode = "rus;nor";

        CountryDto[] countries = new RestCountriesService()
                .getCountryByCode(countryCode);

        assertThat(10)
                .withFailMessage("jhgskljd")
                .isLessThan(13)
                .isGreaterThan(12);

        assertThat(countries)
                .as("what is happened there")
                .contains(new CountryDto().setName("Russia"))
                .isEmpty();

        org.assertj.core.api.Condition<CountryDto> russia = new Condition<>(
                m -> {
                    return m.getName().equals("Russia") && m.getCapital().equals("Moscow");
                }, "name equal Russia");

        org.assertj.core.api.Assertions.assertThat(countries[0]).is(russia);
    }
}
