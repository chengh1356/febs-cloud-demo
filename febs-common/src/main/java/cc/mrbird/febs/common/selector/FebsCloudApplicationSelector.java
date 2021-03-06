package cc.mrbird.febs.common.selector;

import cc.mrbird.febs.common.configure.FebsAuthExceptionConfigure;
import cc.mrbird.febs.common.configure.FebsOAuth2FeignConfigure;
import cc.mrbird.febs.common.configure.FebsServerProtectConfigure;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @Description
 * @Author 54045
 * @Date 2020-06-21 14:19
 */
public class FebsCloudApplicationSelector implements ImportSelector {
    @Override
    public String[] selectImports(AnnotationMetadata annotationMetadata) {
        return new String[]{
                FebsAuthExceptionConfigure.class.getName(),
                FebsOAuth2FeignConfigure.class.getName(),
                FebsServerProtectConfigure.class.getName()
        };
    }
}
