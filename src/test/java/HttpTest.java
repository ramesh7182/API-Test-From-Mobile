import com.ramesh7182.BaseTest;
import com.ramesh7182.HttperHomePage;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.testng.annotations.Test;
import java.util.HashMap;

public class HttpTest extends BaseTest {

	public static Log LOG = LogFactory.getLog(HttpTest.class);

	@Test(description="Sending request to non exist page", dataProvider = "readTestData")
	public void requestAPIWithFileNotFound(HashMap<Object, Object> data)
	{
		LOG.info("Enter:requestAPIWithFileNotFound");
		String urlAPIFor404 = data.get("apiurl").toString();
		String expectedStatusCode = data.get("statuscode").toString();
		HttperHomePage httperHomePage = new HttperHomePage();
		httperHomePage
				.enterURL(urlAPIFor404)
				.clickSend()
				.clickPreview()
				.verifyStatusCode(expectedStatusCode)
				.goBack()
				.verifyNoResourceError();
		LOG.info("Exit:requestAPIWithFileNotFound");
	}
	@Test(description="Sending request to non exist page", dataProvider = "readTestData")
	public void requestAPIWithSuccess(HashMap<Object, Object> data)
	{
		LOG.info("Enter:requestAPIWithSuccess");
		String urlAPIForSuccess = data.get("apiurl").toString();
		String expectedStatusCode = data.get("statuscode").toString();
		HttperHomePage httperHomePage = new HttperHomePage();
		httperHomePage
				.enterURL(urlAPIForSuccess)
				.clickSend()
				.clickPreview()
				.verifyStatusCode(expectedStatusCode);
		LOG.info("Exit:requestAPIWithSuccess");
	}




}
