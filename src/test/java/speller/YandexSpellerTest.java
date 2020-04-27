package speller;

import com.google.gson.Gson;
import io.restassured.RestAssured;
import org.testng.annotations.Test;

public class YandexSpellerTest {

	@Test
	void yandexSpellerPositiveTest() {
		String body =
				"<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:spel=\"http://speller.yandex.net/services/spellservice\">\n" +
						"   <soapenv:Header/>\n" +
						"   <soapenv:Body>\n" +
						"      <spel:CheckTextRequest lang=\"?\" options=\"0\" format=\"\">\n" +
						"         <spel:text>lilt</spel:text>\n" +
						"      </spel:CheckTextRequest>\n" +
						"   </soapenv:Body>\n" +
						"</soapenv:Envelope>";

		RestAssured
				.given().body(body)
				.when().post("http://speller.yandex.net/services/spellservice")
				.then().statusCode(200);
	}
}
