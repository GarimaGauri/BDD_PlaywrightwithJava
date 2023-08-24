package Steps;

import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Page;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.HashMap;

@Getter
@Setter
public class Context {
    Page page;
    public BrowserContext browserContext;
    public final Logger logger;
    HashMap<String, String> uniqueAttributes;
    final String domain;
    public Context()
    {
        uniqueAttributes = new HashMap<>();
        logger = LoggerFactory.getLogger(Context.class);
        domain = System.getProperty("domain", "https://www.douglas.de/de");
    }
}

