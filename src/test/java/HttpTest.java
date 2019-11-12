import com.ramesh7182.CommonMethods;
import org.testng.annotations.Test;
import java.net.MalformedURLException;

public class HttpTest {
	@Test
	public void requestAPIWithFileNotFound() throws MalformedURLException
	{
		String urlAPIFor404 = "dummy.restapiexample.com/api/v1/empl yeess";
		String expectedStatusCode = "404";
		CommonMethods cm = new CommonMethods();
		cm.initDriver();
		cm.enterURL(urlAPIFor404);
		cm.clickSend();
		cm.clickPreview();
		cm.verifyStatusCode(expectedStatusCode);
		cm.goBack();
		cm.verifyNoResourceError();
		cm.closeDriver();
	}
}
