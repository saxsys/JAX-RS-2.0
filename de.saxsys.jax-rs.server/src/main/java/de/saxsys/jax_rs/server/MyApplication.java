package de.saxsys.jax_rs.server;

import org.glassfish.jersey.server.ResourceConfig;

/**
 * Oder Jersey-frei:
 * 
 * <pre>
 * public class MyApplication extends Application {
 * 	&#064;Override
 * 	public Set&lt;Class&lt;?&gt;&gt; getClasses() {
 * 		Set&lt;Class&lt;?&gt;&gt; s = new HashSet&lt;Class&lt;?&gt;&gt;();
 * 		s.add(HelloWorldResource.class);
 * 		return s;
 * 	}
 * }
 * </pre>
 */
public class MyApplication extends ResourceConfig {
	public MyApplication() {
		packages("de.saxsys.jax_rs.server");
	}
}
