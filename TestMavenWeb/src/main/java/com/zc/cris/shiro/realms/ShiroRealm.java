package com.zc.cris.shiro.realms;

import java.util.HashSet;
import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAccount;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthenticatingRealm;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

/**
 * 
 * @Description：TODO (使用了 MD5 算法的安全数据源)
 * @Project Name：TestMavenWeb
 * @Package Name: com.zc.cris.shiro.realms
 * @ClassName：SecondRealm.java
 * @Author：zc-cris
 * @Create Date：2018年4月4日下午5:27:36
 * @version: v1.0
 * @Copyright: zc-cris
 * @mail: 17623887386@163.com
 */
public class ShiroRealm extends AuthorizingRealm {

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		
		// 1. 先将token 强转为 UsernamePasswordToken ,专门用于用户信息验证
		UsernamePasswordToken upToken = (UsernamePasswordToken) token;
		
		// 2. 获取到前台传来的用户名(从 已经封装好的 UsernamePasswordToken 中获取)
		String username = upToken.getUsername();
		
		//3. 模拟根据前台传来的用户名从数据库查询对应的用户信息
		System.out.println("从数据库根据前台的用户名进行查询："+username+" 的信息");
		
		// 3.1 模拟从数据库没有查询到该用户名
		if("unknown".equals(username)) {
			throw new UnknownAccountException("没有该用户");
		}
		// 3.2 模拟该用户已经被锁定
		if("locked".equals(username)) {
			throw new LockedAccountException("该用户已经被锁定，暂时无法使用");
		}
		
		// 4. 如果该用户名对应的用户信息可用，那么构建 AuthenticationInfo 对象并返回，通常使用的实现类为：SimpleAuthenticationInfo
		// 以下为从数据库中获取到的信息
		// 1- principal: 认证的实体信息，可以是 username，也可以是数据表对应的用户的实体类对象
		String principal = username;
		// 2- credentials: 明文密码
//		String credentials = "123456";
		String credentials = null;
		// 3- realmName: 当前realm 对象的name，调用父类的getName() 方法即可
		
		if("user".equals(username)) {
			credentials = "098d2c478e9c11555ce2823231e02ec1";
		}else if("admin".equals(username)) {
			credentials = "038bdaf98f2037b31f1e75b5b4c9b26e";
		}
		
		// 当前 realm 的名字
		String realmName = getName();
		
		// 5. MD5盐值加密 用户密码（创建用户保存到数据库的是经过加密后的字符，用户登陆的时候，
//		将输入的密码经过加密后和数据库中的加密字符串进行比对，这个加密过程是不可逆的）
		ByteSource bytes = ByteSource.Util.bytes(username);
		
		// 6. 密码验证
		SimpleAuthenticationInfo info = null;
		// 1).principal:身份，即主体的标识属性，可以是任何东西，如用户名、邮箱等，唯一即可。
		//		一个主体可以有多个principals，但只有一个Primary principals，一般是用户名/密码/手机号
		// 2).credentials:证明/凭证，即只有主体知道的安全值，如密码/数字证书等
		// 3).bytes: 盐值，同样需要唯一标识，以保证不同用户的相同密码经过加密后也不同
		// 4).realmName：当前 reaml 的名字（realm 其实就是安全实体数据源，由我们用户自己提供，而 Shiro 的 Authenticator 验证器根据
		//  	我们提供的reaml 数据源的数据和前台经过加密后的数据进行比对，判断是否验证成功）
		info = new SimpleAuthenticationInfo(principal, credentials, bytes, realmName);
		System.out.println(info);
		
		return info;
	}

	/**
	 * 
	 * @MethodName: doGetAuthorizationInfo
	 * @Description: TODO (认证的时候就同时完成授权，需要继承 AuthorizingRealm)
	 * @see org.apache.shiro.realm.AuthorizingRealm#doGetAuthorizationInfo(org.apache.shiro.subject.PrincipalCollection)
	 * @Author：zc-cris
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		//1. PrincipalCollection 中获取用户信息
		Object primaryPrincipal = principals.getPrimaryPrincipal();
		
		//2. 根据用户信息来获取当前用户的角色或权限（如果当前用户信息不包括权限信息或者角色信息，那就还得查询一次数据库）
		Set<String> roles = new HashSet<>();
		// 添加用户权限
		roles.add("user");
		if("admin".equals(primaryPrincipal)) {
			roles.add("admin");
		}
		
		//3. 创建 SimpleAuthorizationInfo ，并设置其 roles 属性，最后返回SimpleAuthorizationInfo
		//（注意：如果有多个 AuthorizingRealm 的实现类，默认用户满足第一个 AuthorizingRealm 的认证就顺便实现授权，并跳出循环）
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo(roles);
		
		return info;
	}
	
	

}
