package ac.simons.wjax2016.starter;

import org.springframework.boot.Banner;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.thymeleaf.ThymeleafAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.thymeleaf.spring4.SpringTemplateEngine;

@Configuration
@ConditionalOnClass({SpringTemplateEngine.class, ThymeleafAutoConfiguration.class})
@AutoConfigureBefore(ThymeleafAutoConfiguration.class)
class ThymeleafBannerAutoConfiguration {

    @Bean
    public BannerSupplier defaultBannerSupplier(final Environment environment, final Banner banner) {
        return new DefaultBannerSupplier(environment, banner);
    }

    @Bean
    public ThymeleafBannerDialect webBannerDialect(final BannerSupplier bannerSupplier) {
        return new ThymeleafBannerDialect(bannerSupplier);
    }
}
