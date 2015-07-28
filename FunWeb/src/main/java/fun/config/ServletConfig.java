package fun.config;

import fun.util.ConstructorUtil;
import fun.web.mvc.interceptor.ApplicationInterceptor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.UrlBasedViewResolver;

import java.util.List;

/**
 * Configuration Class for Spring.
 *
 * @author Open Source
 *
 */
@Configuration
@ComponentScan("fun.web.mvc")
@EnableWebMvc
public class ServletConfig extends WebMvcConfigurerAdapter {

  /**
   * Time to keep in cache.
   */
  private static final int CACHE_PERIOD = 31556926;

  /**
   * Default Constructor.
   */
  public ServletConfig() {
    super();
    ConstructorUtil.logInstanceCreation(ServletConfig.class);
  }

  /**
   * Adds intercepter.
   */
  @Override
  public void addInterceptors(final InterceptorRegistry registry) {
    registry.addInterceptor(new ApplicationInterceptor());
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
    setCachePeriod(registration);

  }

  /**
   * Enable configuration.
   */
  @Override
  public void configureDefaultServletHandling(final DefaultServletHandlerConfigurer configurer) {
    configurer.enable();
  }

  /**
   * Add JSON message converter.
   */
  @Override
  public void configureMessageConverters(final List<HttpMessageConverter<?>> converters) {
    converters.add(new MappingJackson2HttpMessageConverter());
    super.configureMessageConverters(converters);
  }

  /**
   * Registers Resources.
   *
   * @param registration
   *          resource
   */
  private void setCachePeriod(final ResourceHandlerRegistration registration) {
    registration.setCachePeriod(CACHE_PERIOD);
  }

  /**
   * View Resolver.
   *
   * @return resolver
   */
  @Bean
  public UrlBasedViewResolver setupHtmlViewResolver() {
    final UrlBasedViewResolver resolver = new UrlBasedViewResolver();
    resolver.setOrder(1);
    resolver.setViewNames("*.html");
    resolver.setPrefix("/common/app/");
    resolver.setSuffix("");
    resolver.setViewClass(JstlView.class);
    return resolver;
  }

  /**
   * Html View Resolver.
   *
   * @return resolver
   */
  @Bean
  public UrlBasedViewResolver setupViewResolver() {
    final UrlBasedViewResolver resolver = new UrlBasedViewResolver();
    resolver.setOrder(2);
    resolver.setViewNames("/*");
    resolver.setPrefix("/WEB-INF/jsp/");
    resolver.setSuffix(".jsp");
    resolver.setViewClass(JstlView.class);
    return resolver;
  }
}