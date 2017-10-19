package test;

import java.util.List;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Context;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.json.JsonObject;

public class ServerExample extends AbstractVerticle{



	@Override
	public void start(Future<Void> startFuture) throws Exception {
		// TODO Auto-generated method stub
		super.start(startFuture);
		 vertx.createHttpServer().requestHandler(new Handler<HttpServerRequest>() {
		      public void handle(HttpServerRequest req) {
		        System.out.println("Got request: " + req.uri());
		 req.response().headers().set("Content-Type", "text/html; charset=UTF-8");
		        req.response().end("<html><body><h1>Hello from vert.x!</h1></body></html>");
		      }
		    }).listen(8080); 
	}


	@Override
	public void stop(Future<Void> stopFuture) throws Exception {
		// TODO Auto-generated method stub
		super.stop(stopFuture);
		System.out.println("this a end!!! ");
	}
			
		  
		} 

