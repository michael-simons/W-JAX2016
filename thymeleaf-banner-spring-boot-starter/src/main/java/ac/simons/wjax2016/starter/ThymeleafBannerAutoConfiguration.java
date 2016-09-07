package ac.simons.wjax2016.starter;

import java.util.ArrayList;
import org.springframework.boot.Banner;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
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
    @ConditionalOnProperty(name = "spring.main.banner-mode", havingValue = "off")
    public BannerSupplier emptyBannerSupplier() {
        return args -> new ArrayList<>();
    }

    @Bean
    @ConditionalOnBean(Banner.class)
    public BannerSupplier defaultBannerSupplier(final Environment environment, final Banner banner) {
        return new DefaultBannerSupplier(environment, banner);
    }

    @Bean
    public ThymeleafBannerDialect webBannerDialect(final BannerSupplier bannerSupplier) {
        return new ThymeleafBannerDialect(bannerSupplier);
    }
}
