package com.hisign.sso.api.rest.providers.jackson;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.ext.Provider;

import org.codehaus.jackson.jaxrs.Annotations;
import org.codehaus.jackson.jaxrs.JacksonJsonProvider;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.jboss.resteasy.annotations.providers.NoJackson;
import org.jboss.resteasy.util.FindAnnotation;

/**
 * Only different from Jackson one is *+json in @Produces/@Consumes
 *
 * @author <a href="mailto:bill@burkecentral.com">Bill Burke</a>
 * @version $Revision: 1 $
 */
@Provider
@Consumes({"application/*+json", "text/json"})
@Produces({"application/*+json", "text/json"})
public class ResteasyJacksonProvider extends JacksonJsonProvider
{
	public ResteasyJacksonProvider() {
		super(Annotations.JACKSON, Annotations.JAXB);
		ObjectMapper mapper = this._mapperConfig.getDefaultMapper();
		mapper.setDateFormat(new SimpleDateFormat("yyyyMMddHHmmss"));
		this.setMapper(mapper);
//		this.configure(SerializationConfig.Feature.WRITE_NULL_PROPERTIES, false);
	}

   @Override
   public boolean isReadable(Class<?> aClass, Type type, Annotation[] annotations, MediaType mediaType)
   {
      if (FindAnnotation.findAnnotation(aClass, annotations, NoJackson.class) != null) return false;
      return super.isReadable(aClass, type, annotations, mediaType);
   }

   @Override
   public boolean isWriteable(Class<?> aClass, Type type, Annotation[] annotations, MediaType mediaType)
   {
      if (FindAnnotation.findAnnotation(aClass, annotations, NoJackson.class) != null) return false;
      return super.isWriteable(aClass, type, annotations, mediaType);
   }
}
