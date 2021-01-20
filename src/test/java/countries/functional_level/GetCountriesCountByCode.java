package countries.functional_level;

import countries.dto.CountryDto;
import countries.service.RestCountriesAssertions;
import countries.service.RestCountriesService;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class GetCountriesCountByCode {
//
//    @Test
//    void test1() {
//        CountryDto country = new CountryDto()
//                .name("Russia")
//                .capital("Moscow");
//    }

    //------------------------Simple assertion with testNG
    @Test(description = "Get country by one Code")
    public void getCountryByName() {
        String countryName = "Aruba";

        CountryDto[] country = new RestCountriesService().getCountryByName(countryName);

        assertEquals(country.length, 1, "Incorrect list of countries");
        assertEquals(country[0].getName(), countryName, "Incorrect county name");
    }

    @Test(description = "Get country by one Code")
    void getCountryByCode() {
        String countryCode = "rus";
        String countryName = "Russia";

        CountryDto[] country = new RestCountriesService().getCountryByCode(countryCode);

        assertEquals(country.length, 1, "Incorrect list of countries");
        assertEquals(country[0].getName(), countryName, "Incorrect county name");
    }

    //----------------------------------------Custom assertion
    @Test(description = "Get country by one Code")
    void getCountrytByCode2() {
        String countryCode = "rus";
        String countryName = "Russia";

		CountryDto[] countriesRussia = new CountryDto[]{
				new CountryDto().setName("Russia")
		};

		CountryDto[] countriesActual = new RestCountriesService()
                .getCountryByCode(countryCode);

        new RestCountriesAssertions(countriesActual)
				.verifyDataEqual(countriesRussia)
                .verifyCountry(countryName)
                .verifyCountries(countryName, countryName);
    }

    @Test(description = "Get country by one Code")
    void getCountryByTwoCode2() {
        String countryCode = "rus;nor";

        CountryDto[] countries = new RestCountriesService()
                .getCountryByCode(countryCode);

        new RestCountriesAssertions(countries)
                .verifyCountries("Russia", "Norway");
    }

    //-----------------------------------Data Provider

    @DataProvider
    public Object[][] countriesData() {
        return new Object[][]{
                {"PYATEROCHKA_1", "rus", "Russia"},
                {"PYATEROCHKA_6", "rus;nor", "Russia", "Norway"},
                {"PYATEROCHKA_89", "rus;nor", "Russia", "Norway"},
                {"PYATEROCHKA_76_1", "ua", "Ukraine"},
                {"PYATEROCHKA_76_2", "rus;nor", "Angola", "Norway"},
                {"PYATEROCHKA_76_3", "rus;nor", "Russia", "Norway"},
        };
    }

    @Test(description = "Get country by one Code", dataProvider = "countriesData")
    void getCountryByTwoCodeMultiple(String testId, String countryCodes, String... expectedCountriesNames) {

        CountryDto[] countries = new RestCountriesService()
                .getCountryByCode(countryCodes);

        new RestCountriesAssertions(countries)
                .verifyCountries(expectedCountriesNames);
    }
}
