package onlineroomrent.tenant;

import onlineroomrent.dto.TokenStatus;

public class TenantContext {
   static ThreadLocal<TokenStatus> threadLocal= new ThreadLocal<>();
    public static void setTenantContext(TokenStatus tokenStatus){
        threadLocal.set(tokenStatus);
    }

    public static TokenStatus getCurrentTenant(){
        return threadLocal.get();
    }

}
