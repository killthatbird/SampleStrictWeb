package fun.config;

import fun.util.ConstructorUtil;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.UrlBasedViewResolver;

/**
 * Configuration Class for Spring.
 *
 * @author I have no name
 *
 */
@Configuration
@ComponentScan("fun.web.mvc")
@EnableWebMvc
public class ServletConfig extends WebMvcConfigurerAdapter {

  /**
   * Default Constructor.
   */
  public ServletConfig() {
    super();
    ConstructorUtil.logInstanceCreation(ServletConfig.class);
  }

  /**
   * Registers Resources.
   *
   * @param registration
   *          resource
   */
  private void addResouceLocations(final ResourceHandlerRegistration registration) {
    registration.addResourceLocations("/common/");
  }

  /**
   * Let resource requests through spring.
   */
  @Override
  public void addResourceHandlers(final ResourceHandlerRegistry registry) {
    final ResourceHandlerRegistration registration = registry.addResourceHandler("/common/**");
    addResouceLocations(registration);
  }

  /**
   * View Resolver.
   *
   * @return resolver
   */
  @Bean
  public UrlBasedViewResolver setupViewResolver() {
    final UrlBasedViewResolver resolver = new UrlBasedViewResolver();
    resolver.setPrefix("/WEB-INF/jsp/");
    resolver.setSuffix(".jsp");
    resolver.setViewClass(JstlView.class);
    return resolver;
  }
}