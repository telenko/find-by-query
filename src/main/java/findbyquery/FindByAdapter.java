package findbyquery;

import java.lang.annotation.Annotation;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

/**
 *
 * @author Andrii Telenko
 */
public class FindByAdapter {
    
    static FindBy build(FindByQuery findByQuery) {
        return new FindBy() {
          @Override
          public How how() {
              return findByQuery.how();
          }

          @Override
          public String using() {
              return findByQuery.using();
          }

          @Override
          public String id() {
              return findByQuery.id();
          }

          @Override
          public String name() {
              return findByQuery.name();
          }

          @Override
          public String className() {
              return findByQuery.className();
          }

          @Override
          public String css() {
              return findByQuery.css();
          }

          @Override
          public String tagName() {
              return findByQuery.tagName();
          }

          @Override
          public String linkText() {
              return findByQuery.linkText();
          }

          @Override
          public String partialLinkText() {
              return findByQuery.partialLinkText();
          }

          @Override
          public String xpath() {
              return findByQuery.xpath();
          }

          @Override
          public Class<? extends Annotation> annotationType() {
              return findByQuery.annotationType();
          }
      };
    }
    
}
