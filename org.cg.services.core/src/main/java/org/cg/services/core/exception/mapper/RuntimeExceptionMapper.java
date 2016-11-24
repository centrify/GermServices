package org.cg.services.core.exception.mapper;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.cg.services.core.exception.ServiceExceptionMessage;

/**
 * A ExceptionMapper that maps RuntimeException to 500 INTERNAL_SERVER_ERROR
 * @author WZ
 *
 */
public class RuntimeExceptionMapper implements ExceptionMapper<RuntimeException> {
	
	private static final Logger LOG = LoggerFactory.getLogger(RuntimeExceptionMapper.class);

	@Override
	public Response toResponse(RuntimeException exception) {
		LOG.error("Service Exception:", exception);

		ServiceExceptionMessage serviceExceptionDetails = new ServiceExceptionMessage(
				Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(),
				Response.Status.INTERNAL_SERVER_ERROR.toString(),
				exception.getMessage() !=null ? exception.getMessage() : "Interal Error");

		return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(serviceExceptionDetails).type(MediaType.APPLICATION_JSON).build();
	}
}
