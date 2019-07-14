package findbyquery;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Field;
import org.openqa.selenium.By;
import org.openqa.selenium.support.AbstractFindByBuilder;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactoryFinder;

/**
 *
 * @author Andrii Telenko
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.TYPE})
@PageFactoryFinder(FindByQuery.FindByBuilder.class)
public @interface FindByQuery {
 
  How how() default How.UNSET;

  String using() default "";

  String id() default "";

  String name() default "";

  String className() default "";

  String css() default "";

  String tagName() default "";

  String linkText() default "";

  String partialLinkText() default "";

  String xpath() default "";
  
  String xdompath() default "";

  public static class FindByBuilder extends AbstractFindByBuilder {
    @Override
    public By buildIt(Object annotation, Field field) {
      FindByQuery findBy = (FindByQuery) annotation;
      if (!"".equals(findBy.xdompath())) {
          return new ByXDomPath(findBy.xdompath());
      }
      FindBy annotationOrig = FindByAdapter.build(findBy);
      return new FindBy.FindByBuilder().buildIt(annotationOrig, field);
    }

  }

}