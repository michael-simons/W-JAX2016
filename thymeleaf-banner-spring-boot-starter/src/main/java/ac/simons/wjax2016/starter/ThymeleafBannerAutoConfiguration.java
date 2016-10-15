package ac.simons.wjax2016.starter;

import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.thymeleaf.ThymeleafAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.spring4.SpringTemplateEngine;

@Configuration
@ConditionalOnClass({SpringTemplateEngine.class, ThymeleafAutoConfiguration.class})
@AutoConfigureBefore(ThymeleafAutoConfiguration.class)
class ThymeleafBannerAutoConfiguration {
}
