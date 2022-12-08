package api;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.annotation.Priority;
import javax.ws.rs.Priorities;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Jersey: Manage all validation exceptions that emerge from an API.
 */
@Provider
@Priority(Priorities.USER)
public class ApiExceptionHandler implements
		ExceptionMapper<ApiException> {

	private Logger logger = Logger.getLogger(ApiExceptionHandler.class.getName());

	@Override
	public Response toResponse(ApiException exception) {
		Response.Status status = Response.Status.INTERNAL_SERVER_ERROR;
		if (exception instanceof ApiException.InvalidParameter) {
			status = Response.Status.BAD_REQUEST;
		}
		return makeResponse(exception, status);
	}

	private Response makeResponse(Exception exception, Response.Status status) {
		try {
			Map<String, String> map = new HashMap<String, String>();
			map.put("status_reason_phrase", status.getReasonPhrase());
			map.put("error", exception.getMessage());

			return Response.status(status).entity(new ObjectMapper().writeValueAsString(map)).type(MediaType.APPLICATION_JSON).build();
		} catch (Exception e) {
			logger.log(Level.INFO, e, () -> "Problem attempting to map an Exception to a json response");
			logger.log(Level.INFO, exception, () -> "Original Exception");
			return Response.serverError().build();
		}
	}
}
