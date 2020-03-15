package demo.bean;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class CustomAccessDecisionManager implements AccessDecisionManager {

    @Override
    public void decide(Authentication authentication, Object o, Collection<ConfigAttribute> collection) throws AccessDeniedException, InsufficientAuthenticationException {
        // 判断当前登录用户是否具有当前请求URL的角色。
        // 如果不具备，就抛出AccessDeniedException；否则啥也不做
        //三个参数：
        //  第一个参数含有当前登录用户的信息
        //  第二个参数是一个FilterInvocation对象，可以获取当前请求对象等
        //第三个参数就是FilterInvocationSecurityMetadataSource中的getAttributes方法的返回值，包含当前请求URL所需的角色信息
        Collection<? extends GrantedAuthority> auths = authentication.getAuthorities();
        for (ConfigAttribute configAttribute: collection) {
            if ("ROLE_LOGIN".equals(configAttribute.getAttribute())
                    && authentication instanceof UsernamePasswordAuthenticationToken) return;
            for (GrantedAuthority authority: auths) {
                if (configAttribute.getAttribute().equals(authority.getAuthority())) return;
            }
        }
        throw new AccessDeniedException("权限不足");
    }

    @Override
    public boolean supports(ConfigAttribute configAttribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
