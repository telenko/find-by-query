package findbyquery;

import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 *
 * @author Andrii Telenko
 */
public class ByXDomPath extends By implements Serializable {

    private final String xpathExpression;
    private static boolean xdomPathEngineReady = false;

    public ByXDomPath(String xpathExpression) {
        if (xpathExpression == null) {
            throw new IllegalArgumentException(
                    "Cannot find elements when the XPath is null.");
        }

        this.xpathExpression = xpathExpression;
    }

    @Override
    public List<WebElement> findElements(SearchContext context) {
        checkAndPopulateXdomPathEngine((WebDriver) context);
        return findElementsByXDomPath(xpathExpression, (WebDriver) context);
    }

    @Override
    public WebElement findElement(SearchContext context) {
        checkAndPopulateXdomPathEngine((WebDriver) context);
        return findElementsByXDomPath(xpathExpression, (WebDriver) context).get(0);
    }

    @Override
    public String toString() {
        return "By.xpath: " + xpathExpression;
    }

    private void checkAndPopulateXdomPathEngine(WebDriver driver) {
        if (!xdomPathEngineReady) {
            try {
                String script = readXdomPathScript();
                JavascriptExecutor js = (JavascriptExecutor) driver;
                js.executeScript(script);
                xdomPathEngineReady = true;
            } catch (Exception ex) {
                Logger.getLogger(ByXDomPath.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private String readXdomPathScript() throws Exception {
        String value = "";        
        try {
            Scanner sc = new Scanner(new InputStreamReader(getClass().getClassLoader().getResourceAsStream("findbyquery/xdompath.global.min.js"), "UTF-8"));
            while (sc.hasNextLine()) {
                value += sc.nextLine();
            }
        } catch (Exception ex) {
            Logger.getLogger(ByXDomPath.class.getName()).log(Level.SEVERE, null, ex);
            throw new Exception("unable to read script");
        }
        return value;
    }

    private List<WebElement> findElementsByXDomPath(String xdompath, WebDriver context) {
        JavascriptExecutor js = (JavascriptExecutor) context;
        return (List<WebElement>) js.executeScript("return new XDomPath('" + xdompath + "').perform(document.body)");
    }
}
