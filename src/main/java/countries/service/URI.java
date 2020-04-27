package countries.service;

import lombok.AllArgsConstructor;

class URI {
    static final String GET_BY_COUNTRY_CODE_URI = "alpha/";
    static final String GET_BY_COUNTRY_NAME_URI = "name/%s";
}
/*
@AllArgsConstructor
private enum URI {
    GET_BY_COUNTRY_CODE_URI("alpha/"),
    GET_BY_COUNTRY_NAME_URI("name/%s");

    private String value;

    String getValue(Object... vals){
    	return String.format(this.value, vals);
	}
}*/
