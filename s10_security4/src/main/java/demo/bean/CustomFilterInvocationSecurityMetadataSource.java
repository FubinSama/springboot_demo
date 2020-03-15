package demo.bean;

import demo.dao.MenuMapper;
import demo.pojo.Menu;
import demo.pojo.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.util.AntPathMatcher;

import java.util.Collection;
import java.util.List;

public class CustomFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {
    AntPathMatcher antPathMatcher = new AntPathMatcher();

    @Autowired
    MenuMapper menuMapper;

    @Override
    public Collection<ConfigAttribute> getAttributes(Object o) throws IllegalArgumentException {
        // 参数是一个FilterInvocation类型对象，可以提取出请求的url；返回值是该请求URL所需的角色
        String requestUrl = ((FilterInvocation) o).getRequestUrl();
        List<Menu> allMenus = menuMapper.getAllMenus();
        for (Menu menu: allMenus) {
            if (antPathMatcher.match(menu.getPattern(), requestUrl)) {
                List<Role> roles = menu.getRoles();
                String[] roleArr = new String[roles.size()];
                for (int i=0; i<roleArr.length; ++i) {
                    roleArr[i] = roles.get(i).getName();
                }
                return SecurityConfig.createList(roleArr);
            }
        }
        return SecurityConfig.createList("ROLE_LOGIN");
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        //返回所有定义好的权限资源，Spring Security在启动时会校验相关配置是否正确，如果不需要，则返回null即可
        return null;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        //返回对象是否支持校验
        return FilterInvocation.class.isAssignableFrom(aClass);
    }
}
