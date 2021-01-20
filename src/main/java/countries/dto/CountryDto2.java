package countries.dto;

import countries.dto.subentity.Translations;

import java.util.List;
import java.util.Objects;

public class CountryDto2 {
	private String name;
	private String capital;
	private List<String> altSpellings;
	private String relevance;
	private String region;
	private String subregion;
	private Translations translations;
	private Long population;
	private List<Float> latlng;
	private String demonym;
	private Long area;
	private Float gini;
	private List<String> timezones;
	private List<String> callingCodes;
	private List<String> topLevelDomain;
	private List<String> currencies;
	private List<String> languages;
	private String field2;

	public CountryDto2(String name, String capital, List<String> altSpellings, String relevance, String region, String subregion, Translations translations, Long population, List<Float> latlng, String demonym, Long area, Float gini, List<String> timezones, List<String> callingCodes, List<String> topLevelDomain, List<String> currencies, List<String> languages) {
		this.name = name;
		this.capital = capital;
		this.altSpellings = altSpellings;
		this.relevance = relevance;
		this.region = region;
		this.subregion = subregion;
		this.translations = translations;
		this.population = population;
		this.latlng = latlng;
		this.demonym = demonym;
		this.area = area;
		this.gini = gini;
		this.timezones = timezones;
		this.callingCodes = callingCodes;
		this.topLevelDomain = topLevelDomain;
		this.currencies = currencies;
		this.languages = languages;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCapital() {
		return capital;
	}

	public void setCapital(String capital) {
		this.capital = capital;
	}

	public List<String> getAltSpellings() {
		return altSpellings;
	}

	public void setAltSpellings(List<String> altSpellings) {
		this.altSpellings = altSpellings;
	}

	public String getRelevance() {
		return relevance;
	}

	public void setRelevance(String relevance) {
		this.relevance = relevance;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getSubregion() {
		return subregion;
	}

	public void setSubregion(String subregion) {
		this.subregion = subregion;
	}

	public Translations getTranslations() {
		return translations;
	}

	public void setTranslations(Translations translations) {
		this.translations = translations;
	}

	public Long getPopulation() {
		return population;
	}

	public CountryDto2 setPopulation(Long population) {
		this.population = population;
		return this;
	}

	public List<Float> getLatlng() {
		return latlng;
	}

	public void setLatlng(List<Float> latlng) {
		this.latlng = latlng;
	}

	public String getDemonym() {
		return demonym;
	}

	public void setDemonym(String demonym) {
		this.demonym = demonym;
	}

	public Long getArea() {
		return area;
	}

	public void setArea(Long area) {
		this.area = area;
	}

	public Float getGini() {
		return gini;
	}

	public void setGini(Float gini) {
		this.gini = gini;
	}

	public List<String> getTimezones() {
		return timezones;
	}

	public void setTimezones(List<String> timezones) {
		this.timezones = timezones;
	}

	public List<String> getCallingCodes() {
		return callingCodes;
	}

	public void setCallingCodes(List<String> callingCodes) {
		this.callingCodes = callingCodes;
	}

	public List<String> getTopLevelDomain() {
		return topLevelDomain;
	}

	public void setTopLevelDomain(List<String> topLevelDomain) {
		this.topLevelDomain = topLevelDomain;
	}

	public List<String> getCurrencies() {
		return currencies;
	}

	public void setCurrencies(List<String> currencies) {
		this.currencies = currencies;
	}

	public List<String> getLanguages() {
		return languages;
	}

	public void setLanguages(List<String> languages) {
		this.languages = languages;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		CountryDto2 that = (CountryDto2) o;
		return Objects.equals(name, that.name) &&
				Objects.equals(capital, that.capital) &&
				Objects.equals(altSpellings, that.altSpellings) &&
				Objects.equals(relevance, that.relevance) &&
				Objects.equals(region, that.region) &&
				Objects.equals(subregion, that.subregion) &&
				Objects.equals(translations, that.translations) &&
				Objects.equals(population, that.population) &&
				Objects.equals(latlng, that.latlng) &&
				Objects.equals(demonym, that.demonym) &&
				Objects.equals(area, that.area) &&
				Objects.equals(gini, that.gini) &&
				Objects.equals(timezones, that.timezones) &&
				Objects.equals(callingCodes, that.callingCodes) &&
				Objects.equals(topLevelDomain, that.topLevelDomain) &&
				Objects.equals(currencies, that.currencies) &&
				Objects.equals(languages, that.languages);
	}

	@Override
	public int hashCode() {
		return Objects.hash(name, capital, altSpellings, relevance, region, subregion, translations, population, latlng, demonym, area, gini, timezones, callingCodes, topLevelDomain, currencies, languages);
	}
}
