package com.ecp.core.utils;


import java.util.Date;

import com.alisoft.xplatform.asf.cache.ICacheManager;
import com.alisoft.xplatform.asf.cache.IMemcachedCache;
import com.alisoft.xplatform.asf.cache.memcached.CacheUtil;
import com.alisoft.xplatform.asf.cache.memcached.MemcachedCacheManager;


/** 
 * @author duyl 
 * @version 创建时间：2011-6-8 下午02:27:04 
 *
 * 类说明：
 */
public class MemcachedUtil {
	
	 private static ICacheManager<IMemcachedCache> manager;
		
		static
		{
			manager = CacheUtil.getCacheManager(IMemcachedCache.class,
					MemcachedCacheManager.class.getName());
				manager.setConfigFile("memcached.xml");
				manager.setResponseStatInterval(5*1000);
				manager.start();
		}

	public static IMemcachedCache  getMemcahcedClientInstance(String memcacheName)
	{
		if(manager==null)
		{
			manager = CacheUtil.getCacheManager(IMemcachedCache.class,
					MemcachedCacheManager.class.getName());
				manager.setConfigFile("memcached.xml");
				manager.setResponseStatInterval(5*1000);
				manager.start();
		}
		return manager.getCache(memcacheName);
	}

    
    public static void main(String[] args) throws Exception
    {
    	  final long EXP = System.currentTimeMillis()+10*1000; 
    	  //new Date(System.currentTimeMillis()+2*60*60*1000)
    	  IMemcachedCache m=MemcachedUtil.getMemcahcedClientInstance("commonclient");
    	  
    	  m.put("testkey", "hello memcache",new Date(EXP));
    	  int i = 0;
    	  while((i++) < 3){
    		  System.out.println(m.get("testkey"));
    		  Thread.sleep(5*1000);
    	  }
    	  Thread.sleep(15*1000);
    	  System.out.println(m.get("testkey"));
    }
}
