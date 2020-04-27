package countries.functional_level;

import countries.dto.CountryDto;
import countries.service.RestCountriesAssertions;
import countries.service.RestCountriesService;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class GetCountriesCountByCode {

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

		CountryDto[] countries = new RestCountriesService()
				.getCountryByCode(countryCode);

		new RestCountriesAssertions(countries)
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
				{"145","rus", "Russia"},
				{"4858","rus;nor", "Russia", "Norway"},
				{"6869","rus;nor", "Russia", "Norway"},
				{"9373","ua", "Ukraine"},
				{"4749","rus;nor", "Angola", "Norway"},
				{"5968","rus;nor", "Russia", "Norway"},
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
