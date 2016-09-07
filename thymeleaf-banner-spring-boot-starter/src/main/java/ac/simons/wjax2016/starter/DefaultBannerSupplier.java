package ac.simons.wjax2016.starter;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;
import org.springframework.boot.Banner;
import org.springframework.core.env.Environment;
import org.thymeleaf.Arguments;
import org.thymeleaf.dom.Element;
import org.thymeleaf.dom.Node;
import org.thymeleaf.dom.Text;

public class DefaultBannerSupplier implements BannerSupplier {

    private final String banner;

    public DefaultBannerSupplier(final Environment environment, final Banner banner) {
        final String bannerCharset = environment.getProperty("banner.charset", "UTF-8");
        try (
                final ByteArrayOutputStream out = new ByteArrayOutputStream();
                final PrintStream printStream = new PrintStream(out, true, bannerCharset)
        ) {
            banner.printBanner(environment, DefaultBannerSupplier.class, printStream);
            this.banner = new String(out.toByteArray(), Charset.forName(bannerCharset));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Node> get(Arguments args) {
        final Element node = new Element("pre");
        node.setAttribute("class", "banner");
        node.addChild(new Text(banner));
        return Arrays.asList(node);
    }
}
