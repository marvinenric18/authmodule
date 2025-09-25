package API;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

//FOR LOGGING..
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;
import org.glassfish.jersey.server.ResourceConfig;
import org.json.simple.JSONObject;

import Config.Config;
import Data.LoginModel;
import Token.JwToken;

@Path("v1")
public class APIService {
	public static Logger logger = null;
	
	public JSONObject result = null;
	
	public String username = "";
	public String password = "";
	public String token = "";
	
	public APIService() {
		logger = LogManager.getLogger(APIService.class);
	}
	

	@GET
	@Path("testlink")
	public Response testLink() {
		logger.info("Test");
		
		return Response.status(200).entity("test").build();
	}
	
	
	@SuppressWarnings("unchecked")
	@POST
    @Path("login")
	@Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response tokenJson(LoginModel login, @HeaderParam("X-Authentication-decrypted") String authUsername) {
        
		result = new JSONObject();
		
		logger.trace("Request - GrantType:{} Username:{} Password:{}", login.username, login.password);
		
		Config config = Config.getInstance();
		
		username = config.getString("test.username");
		password = config.getString("test.password");
		
		//VALIDATE IF USERNAME AND PASSWORD IS CORRECT..
		if(!username.equals(login.username) || !password.equals(login.password))
		{
			logger.info("=============================");
			result.put("message", "invalid_username_or_password");
			result.put("status", "failed");
			logger.info("RESPONSE: " + result);
			logger.info("=============================");
			
			return Response.status(Status.UNAUTHORIZED).entity(result).build();	
		}

		//GENERATE TOKEN HERE..
		  String token = JwToken.generateToken(login.username, login.password);
		
		logger.info("=============================");
		result.put("token",token);
		result.put("message", "login_successfully");
		result.put("status", "success");
		logger.info("RESPONSE: " + result);
		logger.info("=============================");
		
		return Response.status(Status.OK).entity(result).build();	
	}
	
}
